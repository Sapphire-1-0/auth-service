package com.brihaspathee.sapphire.mapper.interfaces;

import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.model.UserDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 6:09 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserMapper {

    /**
     * Convert user entity to user dto
     * @param user - user entity
     * @return - the user dto object
     */
    UserDto toUserDto(User user);

    /**
     * Convert the user dto to user entity
     * @param userDto - the user dto
     * @return - the user entity
     */
    User toUser(UserDto userDto);

    /**
     * Convert user entities to user dtos
     * @param users - the user entities
     * @return - the user dto set
     */
    Set<UserDto> toUserDtoSet(Set<User> users);

    /**
     * Convert user dtos to user entities
     * @param userDtos - the user dtos
     * @return - the user entities
     */
    Set<User> toUserSet(Set<UserDto> userDtos);
}
