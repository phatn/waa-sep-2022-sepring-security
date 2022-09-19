package edu.miu.security.service.impl;

import edu.miu.security.entity.Role;
import edu.miu.security.repository.RoleRepository;
import edu.miu.security.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getByName(String name) {
        return roleRepository.findByRole(name);
    }
}
