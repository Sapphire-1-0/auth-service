package com.brihaspathee.sapphire.services.interfaces;

import com.brihaspathee.sapphire.dto.resource.DomainRegistrationRequest;
import com.brihaspathee.sapphire.dto.resource.ResourceRegistrationRequest;
import com.brihaspathee.sapphire.model.ResourceDto;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 24, February 2025
 * Time: 4:54 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.services.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface ResourceManagementService {

    /**
     * Creates and registers a new domain in the system based on the provided domain registration request.
     * The domain includes details such as its name, description, and associated resources.
     *
     * @param domainRegistrationRequest the details of the domain to be registered, including its name,
     *                                   description, and a list of resources associated with the domain.
     */
    void createDomain(DomainRegistrationRequest domainRegistrationRequest);

    /**
     * Registers a new resource in the system based on the provided resource
     * registration request.
     *
     * @param registrationRequest the details of the resource to be registered,
     *                             including its URI, name, description, type,
     *                             owner, and associated permission.
     */
    void createResource(ResourceRegistrationRequest registrationRequest);

    /**
     * Retrieves the details of a resource based on the provided resource URI.
     *
     * @param resourceUri the URI of the resource to be retrieved
     * @return a ResourceDto object containing the details of the requested resource
     */
    ResourceDto getResource(String resourceUri);
}
