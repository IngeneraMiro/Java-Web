package web.exam.services.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.exam.models.bindmodels.ProductBundModel;
import web.exam.models.entities.Category;
import web.exam.models.entities.Product;
import web.exam.repositories.CategoryRepository;
import web.exam.repositories.ProductRepository;
import web.exam.services.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Product addProduct(ProductBundModel model) {
        Product product = this.mapper.map(model,Product.class);
        product.setCategory(this.categoryRepository.getByName(model.getCategory().name()));
        return this.productRepository.saveAndFlush(product);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        Category cat = this.categoryRepository.getByName(category);
        return this.productRepository.getAllByCategory(cat);
    }

    @Override
    public Product getById(String id) {
        return this.productRepository.findById(id).orElse(null);
    }
}
