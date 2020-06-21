package com.ingenera.examprep.repositories;

import com.ingenera.examprep.models.entities.Category;
import com.ingenera.examprep.models.entities.ItemCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {

      Category getByCategory(ItemCategories category);

}
