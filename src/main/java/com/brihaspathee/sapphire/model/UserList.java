package com.brihaspathee.sapphire.model;

import com.brihaspathee.sapphire.dto.auth.UserDto;
import lombok.*;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, January 2025
 * Time: 5:44 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.model
 * To change this template use File | Settings | File and Code Template
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserList {

    /**
     * Set of users
     */
    private Set<UserDto> users;
}
