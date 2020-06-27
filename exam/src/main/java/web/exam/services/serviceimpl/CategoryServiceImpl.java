package web.exam.services.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.exam.models.entities.Category;
import web.exam.models.entities.CategoryType;
import web.exam.repositories.CategoryRepository;
import web.exam.services.CategoryService;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }
    @PostConstruct
    private void construct(){
        if(this.categoryRepository.count()==0){
            Arrays.stream(CategoryType.values()).forEach(c->this.categoryRepository.save(new Category(c.name())));
        }
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.getByName(name);
    }
}
