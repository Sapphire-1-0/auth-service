package com.brihaspathee.sapphire.mapper.impl;

import com.brihaspathee.sapphire.domain.entity.Role;
import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.mapper.interfaces.RoleMapper;
import com.brihaspathee.sapphire.mapper.interfaces.UserMapper;
import com.brihaspathee.sapphire.model.RoleDto;
import com.brihaspathee.sapphire.model.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 6:59 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    /**
     * Role mapper instance to map the roles associated with the user
     */
    private final RoleMapper roleMapper;

    /**
     * Convert user entity to user dto
     * @param user - user entity
     * @return - the user dto object
     */
    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        if(user.getRoles() != null && !user.getRoles().isEmpty()) {
            Set<Role> roles = user.getRoles();
            Set<RoleDto> roleDtos = roleMapper.toDtoSet(roles);
            userDto.setRoles(roleDtos);
        }
        return userDto;
    }

    /**
     * Convert the user dto to user entity
     * @param userDto - the user dto
     * @return - the user entity
     */
    @Override
    public User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = User.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        if(userDto.getRoles() != null && !userDto.getRoles().isEmpty()) {
            Set<RoleDto> roleDtos = userDto.getRoles();
            Set<Role> roles = roleMapper.toEntitySet(roleDtos);
            user.setRoles(roles);
        }
        return user;
    }

    /**
     * Convert user entities to user dtos
     * @param users - the user entities
     * @return - the user dto set
     */
    @Override
    public Set<UserDto> toUserDtoSet(Set<User> users) {
        return users.stream().map(this::toUserDto).collect(Collectors.toSet());
    }

    /**
     * Convert user dtos to user entities
     * @param userDtos - the user dtos
     * @return - the user entities
     */
    @Override
    public Set<User> toUserSet(Set<UserDto> userDtos) {
        return userDtos.stream().map(this::toUser).collect(Collectors.toSet());
    }
}
