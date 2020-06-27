package web.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.exam.models.entities.Category;
import web.exam.models.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    List<Product> getAllByCategory(Category category);

}
