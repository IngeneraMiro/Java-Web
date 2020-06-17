package com.ingenera.springworkshop.repositories;

import com.ingenera.springworkshop.models.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {



}
