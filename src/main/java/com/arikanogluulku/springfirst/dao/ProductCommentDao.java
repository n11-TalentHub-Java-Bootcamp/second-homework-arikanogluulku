package com.arikanogluulku.springfirst.dao;

import com.arikanogluulku.springfirst.entity.ProductComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentDao extends JpaRepository<ProductComment, Long> {
    List<ProductComment> findAllByUser_IdOrderById(Long userId);

    List<ProductComment> findAllByProduct_IdOrderById(Long productId);

}
