package com.ingenera.lab1.services.serviceimpl;

import com.ingenera.lab1.models.entities.*;
import com.ingenera.lab1.models.servicemodels.RoleServiceModel;
import com.ingenera.lab1.models.servicemodels.UserServiceModel;
import com.ingenera.lab1.repositories.*;
import com.ingenera.lab1.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BrandRepository brandRepository, ModelRepository modelRepository, OfferRepository offerRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    @Transactional
    public void RegisterFirstUser() {
        if (this.userRepository.count() == 0) {
            UserServiceModel userServiceModel = new UserServiceModel();
            userServiceModel.setActive(Boolean.TRUE);
            userServiceModel.setUsername("BigBoss");
            userServiceModel.setFirstName("Ivan");
            userServiceModel.setLastName("Ivanov");
            userServiceModel.setCreated(LocalDateTime.now());
            userServiceModel.setImageUrl("http://mysite.com/myPicture.jpg");
            userServiceModel.setModified(LocalDateTime.now());
            for (Roles r : Roles.values()) {
                RoleServiceModel model = new RoleServiceModel(r);
                this.roleRepository.saveAndFlush(this.mapper.map(model, Role.class));
            }
            User user = this.mapper.map(userServiceModel, User.class);
            user.setRole(this.roleRepository.getAllRoles());
            this.userRepository.saveAndFlush(user);

            Offer offer = new Offer();
            offer.setCreated(LocalDateTime.now());
            offer.setDescription("This is the very first offer");
            offer.setImageUrl("http://mySite/offer.jpg");
            offer.setMileage(2000);
            offer.setModified(LocalDateTime.now());
            offer.setPrice(3000.0);
            offer.setEngine(Engins.DIESEL);
            offer.setTransmission(Transmission.MANUAL);
            offer.setSeller(user);
            offer.setYear(2011);

            Brand brand = new Brand();
            brand.setName("BMW");
            brand.setCreated(LocalDateTime.of(1963, 6, 15, 13, 30));
            this.brandRepository.saveAndFlush(brand);

            Model model = new Model();
            model.setBrand(brand);
            model.setName("323");
            model.setCreated(LocalDateTime.of(2010, 9, 17, 14, 00));
            model.setImageUrl("http://mySite/galery/1.jpg");
            model.setModified(LocalDateTime.of(2015, 8, 20, 9, 00));
            this.modelRepository.saveAndFlush(model);

            offer.setModel(model);
            this.offerRepository.saveAndFlush(offer);
        }
    }
}
