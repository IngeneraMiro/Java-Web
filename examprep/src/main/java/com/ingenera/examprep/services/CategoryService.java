package com.ingenera.examprep.services;

import com.ingenera.examprep.models.entities.Category;

import java.util.List;

public interface CategoryService {

    List<String> getCategoriesList();

    Category getCategoryByName(String name);
}
