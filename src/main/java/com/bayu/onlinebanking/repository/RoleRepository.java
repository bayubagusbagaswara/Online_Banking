package com.bayu.onlinebanking.repository;

import com.bayu.onlinebanking.entity.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);
}
