package web.exam.services.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.exam.models.bindmodels.UserBindModel;
import web.exam.models.entities.Product;
import web.exam.models.entities.User;
import web.exam.repositories.UserRepository;
import web.exam.services.ProductService;
import web.exam.services.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductService productService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductService productService, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.mapper = mapper;
    }

    @PostConstruct
    private void list(){

    }

    @Override
    public User addUser(UserBindModel model) {
        User user = this.mapper.map(model,User.class);
        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public User logUser(String username, String password) {
        return this.userRepository.findByUsernameAndPassword(username,password).orElse(null);
    }


}
