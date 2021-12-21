package com.arikanogluulku.springfirst.service.entityService;

import com.arikanogluulku.springfirst.dao.ProductDao;
import com.arikanogluulku.springfirst.entity.Category;
import com.arikanogluulku.springfirst.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductEntityService {

    @Autowired
    private ProductDao productDao;

    public List<Product> findAll() {
        return (List<Product>) productDao.findAll();
    }

    public Product findById(Long id) {
        Optional<Product> optionalCategory = productDao.findById(id);
        Product product = null;
        if (optionalCategory.isPresent()) {
            product = optionalCategory.get();
        }
        return product;
    }

    public Product save(Product product) {
        product = productDao.save(product);
        return product;
    }

    public void delete(Product product) {
        productDao.delete(product);
    }

    public void deleteById(Long id) {
        productDao.deleteById(id);
    }

    public long count() {
        return productDao.count();
    }
    public List<Product> findAllByCategoryOrderByIdDesc(Long categoryId){
        return productDao.findAllByCategoryOrderByIdDesc(categoryId);
    }
}
