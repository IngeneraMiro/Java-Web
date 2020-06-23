package com.ingenera.oldexam.services.serviceimpl;

import com.ingenera.oldexam.models.bindingmodels.HeroBindModel;
import com.ingenera.oldexam.models.entities.Hero;
import com.ingenera.oldexam.models.viewmodels.HeroViewModel;
import com.ingenera.oldexam.repositories.HeroRepository;
import com.ingenera.oldexam.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HeroServiceImpl implements HeroService {
    private final HeroRepository heroRepository;
    private final ModelMapper mapper;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository, ModelMapper mapper) {
        this.heroRepository = heroRepository;
        this.mapper = mapper;
    }


    @Override
    public Hero addHero(HeroBindModel hero) {
        return this.heroRepository.saveAndFlush(this.mapper.map(hero,Hero.class));
    }

    @Override
    public List<HeroViewModel> getAllHeroes() {
        return this.heroRepository.findAll().stream().map(h->{
           HeroViewModel model = this.mapper.map(h,HeroViewModel.class);
           model.setImgUrl(String.format("/img/%s.jpg",h.getHeroClass().name().toLowerCase()));
           return model;
        }).collect(Collectors.toList());
    }

    @Override
    public void removeHeroById(String id) {
        this.heroRepository.deleteById(id);
    }

    @Override
    public HeroViewModel getHeroById(String id) {
        return this.mapper.map(this.heroRepository.getById(id),HeroViewModel.class);
    }
}
