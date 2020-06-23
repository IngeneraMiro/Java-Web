package com.ingenera.oldexam.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "hero")
public class Hero extends BaseEntity {

    private String heroName;
    private HeroClass heroClass;
    private int level;

    public Hero() {
    }

    @Column(name = "hero_name",nullable = false)
    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @Column(name = "hero_class",nullable = false)
    @Enumerated(EnumType.STRING)
    public HeroClass getHeroClass() {
        return heroClass;
    }

    public void setHeroClass(HeroClass heroClass) {
        this.heroClass = heroClass;
    }

    @Column(name = "level",nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
