package com.ingenera.oldexam.services;

import com.ingenera.oldexam.models.bindingmodels.HeroBindModel;
import com.ingenera.oldexam.models.entities.Hero;
import com.ingenera.oldexam.models.viewmodels.HeroViewModel;

import java.util.List;

public interface HeroService {

    Hero addHero(HeroBindModel hero);
    List<HeroViewModel> getAllHeroes();
    void removeHeroById(String id);
    HeroViewModel getHeroById(String id);

}
