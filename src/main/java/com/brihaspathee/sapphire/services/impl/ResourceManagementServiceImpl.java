package com.brihaspathee.sapphire.services.impl;

import com.brihaspathee.sapphire.domain.entity.Resource;
import com.brihaspathee.sapphire.domain.repository.ResourceRepository;
import com.brihaspathee.sapphire.dto.auth.AuthorityDto;
import com.brihaspathee.sapphire.dto.resource.ResourceRegistrationRequest;
import com.brihaspathee.sapphire.exception.ResourceNotRegisteredException;
import com.brihaspathee.sapphire.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.sapphire.model.ResourceDto;
import com.brihaspathee.sapphire.services.interfaces.ResourceManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 4:56 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.services.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ResourceManagementServiceImpl implements ResourceManagementService {

    /**
     * The instance of ResourceRepository used to perform CRUD operations
     * on resource entities in the Neo4j database.
     */
    private final ResourceRepository resourceRepository;

    private final AuthorityMapper authorityMapper;

    /**
     * Creates a new resource based on the provided registration request.
     *
     * @param registrationRequest the details of the resource to be created
     */
    @Override
    public void createResource(ResourceRegistrationRequest registrationRequest) {

    }

    /**
     * Retrieves a resource based on its URI.
     *
     * @param resourceUri the URI of the resource to retrieve
     * @return the ResourceDto containing the details of the resource, or null if no resource is found
     */
    @Override
    public ResourceDto getResource(String resourceUri) {
//        Optional<Resource> optionalResource = resourceRepository.findByResourceURIWithAuthority(resourceUri);
        log.info("Resource URI:{}", resourceUri);
        Optional<Resource> optionalResource = resourceRepository.findByResourceURIWithAuthority(resourceUri);
        log.info("Resource:{}", optionalResource);
        if(optionalResource.isPresent()) {
            Resource resource = optionalResource.get();
            log.info("Authorities:{}", resource.getAuthorities());
            ResourceDto resourceDto = ResourceDto.builder()
                    .resourceId(resource.getResourceId())
                    .resourceName(resource.getResourceName())
                    .resourceDescription(resource.getResourceDescription())
                    .resourceType(resource.getResourceType())
                    .resourceOwner(resource.getResourceOwner())
                    .resourceUri(resourceUri)
                    .build();
            resourceDto.setAuthorities(resource.getAuthorities().stream().map(authorityMapper::toDto).toList());
            return resourceDto;
        }else{
            log.info("Resource with URI {} not found", resourceUri);
            throw new ResourceNotRegisteredException("Resource with URI " + resourceUri + " not registered.");
        }
    }
}
