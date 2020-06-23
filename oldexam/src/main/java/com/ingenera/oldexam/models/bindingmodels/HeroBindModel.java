package com.ingenera.oldexam.models.bindingmodels;

import com.ingenera.oldexam.models.entities.HeroClass;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class HeroBindModel {

    private String heroName;
    private HeroClass heroClass;
    private int level;

    public HeroBindModel() {
    }

    @Length(min = 1, message = "Hero Name can not be empty!")
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @NotNull(message = "Hero Class can not be empty!")
    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    @NotNull(message = "Hero level can not be empty!")
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
