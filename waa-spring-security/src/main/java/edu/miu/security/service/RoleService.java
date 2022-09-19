package edu.miu.security.service;

import edu.miu.security.entity.Role;

public interface RoleService {

    Role getByName(String name);
}
