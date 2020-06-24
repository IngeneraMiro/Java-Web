package web.exam.services.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.exam.repositories.UserRepository;
import web.exam.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final ModelMapper mapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper mapper, UserRepository userRepository) {
        this.mapper = mapper;
        this.userRepository = userRepository;
    }
}
