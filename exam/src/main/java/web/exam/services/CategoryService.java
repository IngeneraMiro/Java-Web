package web.exam.services;

import web.exam.models.entities.Category;

public interface CategoryService {

    Category findByName(String name);
}
