package com.brihaspathee.sapphire.mapper.interfaces;

import com.brihaspathee.sapphire.domain.entity.Authority;
import com.brihaspathee.sapphire.model.AuthorityDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 6:20 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthorityMapper {

    /**
     * Authority entity to dto
     * @param authority - authority entity
     * @return - authority dto
     */
    AuthorityDto toDto(Authority authority);

    /**
     * Authority dto to entity
     * @param authorityDto - authority dto
     * @return - the authority entity
     */
    Authority toEntity(AuthorityDto authorityDto);

    /**
     * Convert authority entities to authority dto
     * @param authorities - entities to be converted
     * @return - the authority dtos
     */
    Set<AuthorityDto> toDtoSet(Set<Authority> authorities);

    /**
     * Convert authority dtos to authority entities
     * @param authorityDtos - the authority dtos
     * @return - the authority entities
     */
    Set<Authority> toEntitySet(Set<AuthorityDto> authorityDtos);
}
