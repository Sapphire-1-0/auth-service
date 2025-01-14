package com.brihaspathee.sapphire.mapper.impl;

import com.brihaspathee.sapphire.domain.entity.Authority;
import com.brihaspathee.sapphire.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.sapphire.model.AuthorityDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 1/8/25
 * Time: 6:25 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    /**
     * Authority entity to dto
     * @param authority - authority entity
     * @return - authority dto
     */
    @Override
    public AuthorityDto toDto(Authority authority) {
        if(authority == null) {
            return null;
        }
        return AuthorityDto.builder()
                .authorityId(authority.getAuthorityId())
                .permission(authority.getPermission())
                .build();
    }

    /**
     * Authority dto to entity
     * @param authorityDto - authority dto
     * @return - the authority entity
     */
    @Override
    public Authority toEntity(AuthorityDto authorityDto) {
        if(authorityDto == null) {
            return null;
        }
        return Authority.builder()
                .authorityId(authorityDto.getAuthorityId())
                .permission(authorityDto.getPermission())
                .build();
    }

    /**
     * Convert authority entities to authority dto
     * @param authorities - entities to be converted
     * @return - the authority dtos
     */
    @Override
    public Set<AuthorityDto> toDtoSet(Set<Authority> authorities) {
        return authorities.stream().map(this::toDto).collect(Collectors.toSet());
    }

    /**
     * Convert authority dtos to authority entities
     * @param authorityDtos - the authority dtos
     * @return - the authority entities
     */
    @Override
    public Set<Authority> toEntitySet(Set<AuthorityDto> authorityDtos) {
        return authorityDtos.stream().map(this::toEntity).collect(Collectors.toSet());
    }
}
