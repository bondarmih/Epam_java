package edu.bondarmih.memorygame.service;

import edu.bondarmih.memorygame.entity.Role;
import edu.bondarmih.memorygame.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bondarm on 09.08.16.
 */

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByName(String role) {
        return roleRepository.findByName(role);
    }
}
