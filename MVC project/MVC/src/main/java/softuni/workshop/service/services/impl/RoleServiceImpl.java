package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.data.entities.Role;
import softuni.workshop.data.repositories.RoleRepository;
import softuni.workshop.service.models.RoleServiceModel;
import softuni.workshop.service.services.RoleService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public void seedRolesInDb() {
        Role admin = new Role("ADMIN");
        Role user = new Role("USER");
        this.roleRepository.save(admin);
        this.roleRepository.save(user);
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return roleRepository.findAll().stream().map(r -> mapper.map(r, RoleServiceModel.class)).collect(Collectors.toSet());

    }

    @Override
    public RoleServiceModel findByAuthority(String role) {
        return mapper.map(roleRepository.findByAuthority(role), RoleServiceModel.class);
    }
}
