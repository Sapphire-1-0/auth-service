package com.brihaspathee.sapphire.mapper.interfaces;

import com.brihaspathee.sapphire.domain.entity.Role;
import com.brihaspathee.sapphire.model.RoleDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 6:22 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleMapper {

    /**
     * Role entity to role dto
     * @param role - the role entity
     * @return - the role dto
     */
    RoleDto toDto(Role role);

    /**
     * Role dto to entity
     * @param roleDto - the role dto
     * @return - the role entity
     */
    Role toEntity(RoleDto roleDto);

    /**
     * Convert role entities to role dtos
     * @param roles - role entities
     * @return - the role dtos
     */
    Set<RoleDto> toDtoSet(Set<Role> roles);

    /**
     * Convert role dtos to role entities
     * @param roleDtos - role dtos
     * @return - the role entities
     */
    Set<Role> toEntitySet(Set<RoleDto> roleDtos);
}
