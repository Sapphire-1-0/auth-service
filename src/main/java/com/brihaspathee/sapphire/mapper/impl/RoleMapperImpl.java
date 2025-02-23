package com.brihaspathee.sapphire.mapper.impl;

import com.brihaspathee.sapphire.domain.entity.Authority;
import com.brihaspathee.sapphire.domain.entity.Role;
import com.brihaspathee.sapphire.dto.auth.AuthorityDto;
import com.brihaspathee.sapphire.dto.auth.RoleDto;
import com.brihaspathee.sapphire.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.sapphire.mapper.interfaces.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 6:27 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {

    /**
     * Authority mapper to map the authorities linked with the role
     */
    private final AuthorityMapper authorityMapper;

    /**
     * Role entity to role dto
     * @param role - the role entity
     * @return - the role dto
     */
    @Override
    public RoleDto toDto(Role role) {
        if (role == null) {
            return null;
        }
        RoleDto roleDto = RoleDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
        if(role.getAuthorities() != null && !role.getAuthorities().isEmpty()) {
            Set<Authority> authorities = role.getAuthorities();
            Set<AuthorityDto> authoritiesDto = authorityMapper.toDtoSet(authorities);
            roleDto.setAuthorities(authoritiesDto);
        }
        return roleDto;
    }


    /**
     * Role dto to entity
     * @param roleDto - the role dto
     * @return - the role entity
     */
    @Override
    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null) {
            return null;
        }
        Role role = Role.builder()
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .build();
        if(roleDto.getAuthorities() != null && !roleDto.getAuthorities().isEmpty()) {
            Set<AuthorityDto> authorityDtos = roleDto.getAuthorities();
            Set<Authority> authorities = authorityMapper.toEntitySet(authorityDtos);
            role.setAuthorities(authorities);
        }
        return role;
    }

    /**
     * Convert role entities to role dtos
     * @param roles - role entities
     * @return - the role dtos
     */
    @Override
    public Set<RoleDto> toDtoSet(Set<Role> roles) {
        return roles.stream().map(this::toDto).collect(Collectors.toSet());
    }

    /**
     * Convert role dtos to role entities
     * @param roleDtos - role dtos
     * @return - the role entities
     */
    @Override
    public Set<Role> toEntitySet(Set<RoleDto> roleDtos) {
        return roleDtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
