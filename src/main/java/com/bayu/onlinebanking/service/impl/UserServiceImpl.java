package com.bayu.onlinebanking.service.impl;

import com.bayu.onlinebanking.entity.User;
import com.bayu.onlinebanking.entity.security.UserRole;
import com.bayu.onlinebanking.repository.RoleRepository;
import com.bayu.onlinebanking.repository.UserRepository;
import com.bayu.onlinebanking.service.AccountService;
import com.bayu.onlinebanking.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AccountService accountService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, AccountService accountService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean checkUserExists(String username, String email) {
        return checkUsernameExists(username) || checkEmailExists(username);
    }

    @Override
    public boolean checkUsernameExists(String username) {
        return null != findByUsername(username);
    }

    @Override
    public boolean checkEmailExists(String email) {
        return null != findByEmail(email);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public List<User> findUserList() {
        return null;
    }

    @Override
    public void enableUser(String username) {

    }

    @Override
    public void disableUser(String username) {

    }
}
