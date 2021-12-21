package com.arikanogluulku.springfirst.service.entityService;

import com.arikanogluulku.springfirst.dao.ProductCommentDao;
import com.arikanogluulku.springfirst.entity.ProductComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCommentEntityService {
    @Autowired
    private ProductCommentDao productCommentDao;

    public List<ProductComment> findAllByUserComment(Long id){
        return productCommentDao.findAllByUser_IdOrderById(id);
    }
    public List<ProductComment> findAllByProductComment(Long id){
        return productCommentDao.findAllByProduct_IdOrderById(id);
    }
    public ProductComment save(ProductComment productComment){
        productComment=productCommentDao.save(productComment);
        return productComment;
    }
    public void deleteById(Long id){
        productCommentDao.deleteById(id);
    }
}
