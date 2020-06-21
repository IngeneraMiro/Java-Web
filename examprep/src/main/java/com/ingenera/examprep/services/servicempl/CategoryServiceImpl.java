package com.ingenera.examprep.services.servicempl;

import com.ingenera.examprep.models.entities.Category;
import com.ingenera.examprep.models.entities.ItemCategories;
import com.ingenera.examprep.repositories.CategoryRepository;
import com.ingenera.examprep.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    private void init(){
        if(this.categoryRepository.count()==0){
           Arrays.stream(ItemCategories.values()).forEach(c-> {this.categoryRepository.save(new Category(c));});
        }
    }

    @Override
    public List<String> getCategoriesList() {
         return this.categoryRepository.findAll().stream().map(c -> c.getCategory().name()).collect(Collectors.toList());
    }

    @Override
    public Category getCategoryByName(String name) {
        return this.categoryRepository.getByCategory(ItemCategories.valueOf(name));
    }
}
