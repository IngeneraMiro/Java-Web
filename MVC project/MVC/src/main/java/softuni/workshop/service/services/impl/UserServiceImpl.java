package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.workshop.data.entities.Role;
import softuni.workshop.data.entities.User;
import softuni.workshop.data.repositories.UserRepository;
import softuni.workshop.service.models.UserServiceModel;
import softuni.workshop.service.services.RoleService;
import softuni.workshop.service.services.UserService;
import softuni.workshop.web.models.UserRegisterModel;

import java.util.HashSet;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder encoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UserServiceModel registerUser(UserRegisterModel userRegisterModel) {
        User user = mapper.map(userRegisterModel, User.class);
        if (userRepository.count() == 0) {
            roleService.seedRolesInDb();
            user.setAuthorities(roleService.findAllRoles().stream().map(r -> mapper.map(r, Role.class)).collect(Collectors.toSet()));
        } else {
            user.setAuthorities(new HashSet<>());
            user.getAuthorities().add(mapper.map(roleService.findByAuthority("USER"), Role.class));
        }
        user.setPassword(encoder.encode(userRegisterModel.getPassword()));
        return mapper.map(userRepository.save(user), UserServiceModel.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
