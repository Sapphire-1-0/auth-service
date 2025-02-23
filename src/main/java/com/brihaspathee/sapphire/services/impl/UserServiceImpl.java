package com.brihaspathee.sapphire.services.impl;

import com.brihaspathee.sapphire.domain.entity.User;
import com.brihaspathee.sapphire.domain.repository.UserRepository;
import com.brihaspathee.sapphire.mapper.interfaces.UserMapper;
import com.brihaspathee.sapphire.model.UserList;
import com.brihaspathee.sapphire.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 07, January 2025
 * Time: 2:02 PM
 * Project: sapphire
 * Package Name: com.brihaspathee.sapphire.services.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * The instance of the user repository to perform crud operations
     */
    private final UserRepository userRepository;

    /**
     * The user mapper instance
     */
    private final UserMapper userMapper;

    /**
     * Gwt all the users in the system
     */
    @Override
    public UserList getAllUsers() {
        log.info("Get all users inside the service method");
        UserList userList = UserList.builder().build();
        List<User> users = userRepository.findAll();
        if(!users.isEmpty()) {
            userList.setUsers(userMapper.toUserDtoSet(new HashSet<>(users)));
        }
//        for (User user : users) {
//            log.info(user.getUsername());
//            Set<Role> roles = user.getRoles();
//            for (Role role : roles) {
//                log.info(role.getRoleName());
//                Set<Authority> authorities = role.getAuthorities();
//                for (Authority authority : authorities) {
//                    log.info("Permission: {}", authority.getPermission());
//                }
//            }
//        }
        return userList;

    }
}
