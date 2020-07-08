package presentation.demo.services.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import presentation.demo.models.bindmodels.UserBindModel;
import presentation.demo.models.bindmodels.UserLogBindModel;
import presentation.demo.models.entities.Authority;
import presentation.demo.models.entities.User;
import presentation.demo.models.entities.UserAuthorities;
import presentation.demo.models.viewmodels.UserLogViewModel;
import presentation.demo.repositories.AuthorityRepository;
import presentation.demo.repositories.UserRepository;
import presentation.demo.services.PracticeService;
import presentation.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PracticeService practiceService;
    private final PasswordEncoder encoder;
    private final ModelMapper mapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, AuthorityRepository authorityRepository, PracticeService practiceService, PasswordEncoder encoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.practiceService = practiceService;
        this.encoder = encoder;
        this.mapper = mapper;
    }

   @PostConstruct
    private void init(){
        if(this.authorityRepository.count()==0){
            for (UserAuthorities a: UserAuthorities.values()){
                this.authorityRepository.save(new Authority(String.format("ROLE_%s",a.name())));
            }


            User user = new User("miro","Stefanov","A888888",encoder.encode("password"));
            user.setRegisteredOn(LocalDateTime.now());
            this.userRepository.save(user);
            user.addAuthority(this.authorityRepository.getByAuthority("ROLE_ADMIN"));
            this.userRepository.save(user);
        }
   }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username "+username+"not found!") );
        return user;
    }

    @Override
    public User addUser(UserBindModel model) {
        User user = this.mapper.map(model,User.class);
         user.addAuthority(this.authorityRepository.getByAuthority("ROLE_USER"));

        while (true){
            String regNum = "P" + regNumberGeneration();
            if(!this.userRepository.existsByUsername(regNum)){
                user.setUsername(regNum);
                break;
            }
        }
        if(model.getPractice()!=null){
            user.setPractice(this.practiceService.getByName(model.getPractice()));
        }
        user.setRegisteredOn(LocalDateTime.now());
        user.setEnabled(true);

        return this.userRepository.saveAndFlush(user);
    }

    @Override
    public UserLogViewModel logUser(UserLogBindModel model) {
        User user = this.userRepository.findByUsernameAndPassword(model.getUsername(),model.getPassword()).orElse(null);
        if(user==null){
            return null;
        }
        return this.mapper.map(user, UserLogViewModel.class);
    }

    @Override
    public Boolean begin() {
        return this.userRepository.count()==0;
    }


    private String regNumberGeneration(){
        String pass = "";
        for (int i = 0; i < 6; i++) {
            int digit =  (int) (Math.random() * 9);
            pass+=digit;
        }
        return pass;
    }
}
