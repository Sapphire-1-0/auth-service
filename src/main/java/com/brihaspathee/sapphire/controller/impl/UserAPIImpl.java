package com.brihaspathee.sapphire.controller.impl;

import com.brihaspathee.sapphire.controller.interfaces.UserAPI;
import com.brihaspathee.sapphire.model.UserList;
import com.brihaspathee.sapphire.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, January 2025
 * Time: 5:59 AM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.controller.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserAPIImpl implements UserAPI {

    /**
     * The instance of the user service
     */
    private final UserService userService;

    /**
     * Get all the users that are present in the system
     * @return - return the list of all users
     */
    @Override
    public ResponseEntity<UserList> getAllUsers() {
        log.info("Get all users");
        UserList userList = userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }
}
