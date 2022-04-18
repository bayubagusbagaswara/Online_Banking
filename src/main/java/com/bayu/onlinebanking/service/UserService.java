package com.bayu.onlinebanking.service;

import com.bayu.onlinebanking.entity.User;
import com.bayu.onlinebanking.entity.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    // cari user berdasarkan username
    User findByUsername(String username);

    // cari user berdasarkan email
    User findByEmail(String email);

    // check apakah user sudah ada di database
    boolean checkUserExists(String username, String email);

    // check username apakah sudah ada
    boolean checkUsernameExists(String username);

    // check email apakah sudah ada
    boolean checkEmailExists(String email);

    // simpan user
    void save(User user);

    // buat user baru
    User createUser(User user, Set<UserRole> userRoles);

    // simpan user
    User saveUser(User user);

    // cari semua user
    List<User> findUserList();

    void enableUser(String username);

    void disableUser(String username);
}
