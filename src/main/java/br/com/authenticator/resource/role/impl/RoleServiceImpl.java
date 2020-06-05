package br.com.authenticator.resource.role.impl;

import br.com.authenticator.resource.role.Role;
import br.com.authenticator.resource.role.RoleRepository;
import br.com.authenticator.resource.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

}