package web.exam.services;

import web.exam.models.bindmodels.ProductBundModel;
import web.exam.models.entities.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductBundModel model);

    List<Product> getProductByCategory(String category);

    Product getById(String id);

}
