package com.arikanogluulku.springfirst.controller;

import com.arikanogluulku.springfirst.converter.CategoryConvertor;
import com.arikanogluulku.springfirst.converter.ProductConverter;
import com.arikanogluulku.springfirst.dto.CategoryDto;
import com.arikanogluulku.springfirst.dto.ProductDetailDTO;
import com.arikanogluulku.springfirst.entity.Category;
import com.arikanogluulku.springfirst.entity.Product;
import com.arikanogluulku.springfirst.service.entityService.CategoryEntityService;
import com.arikanogluulku.springfirst.service.entityService.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryEntityService categoryEntityService;

    @Autowired
    private ProductEntityService productEntityService;

    @GetMapping("")
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryEntityService.findAll();

        List<CategoryDto> categoryDtoList = CategoryConvertor.INSTANCE.convertAllCategoryToCategoryDtoList(categories);
        return categoryDtoList;
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable Long id) {
        Category category = categoryEntityService.findById(id);
        CategoryDto categoryDto = CategoryConvertor.INSTANCE.convertCategoryToCategoryDto(category);
        return categoryDto;
    }

    @PostMapping("")
    public ResponseEntity<Object> save(@RequestBody CategoryDto categoryDto) {
        Category category = CategoryConvertor.INSTANCE.convertCategoryDtoToCategory(categoryDto);

        category = categoryEntityService.save(category);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("")
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        Category category = CategoryConvertor.INSTANCE.convertCategoryDtoToCategory(categoryDto);

        if (category.getTopCategory() != null && category.getTopCategory().getTopCategory().getId() == null) {
            category.setTopCategory(null);
        }
        category = categoryEntityService.save(category);
        CategoryDto categoryDtoResult = CategoryConvertor.INSTANCE.convertCategoryToCategoryDto(category);
        return categoryDtoResult;
    }

    @DeleteMapping("/{id}")
    public void delete(Long id) {
        categoryEntityService.deleteById(id);
    }

    @GetMapping("/{id}/products")
    public List<ProductDetailDTO> findAllProductByCategory(@PathVariable Long id) {
        List<Product> productList = productEntityService.findAllByCategoryOrderByIdDesc(id);
        List<ProductDetailDTO> productDetailDTOList = ProductConverter.INSTANCE.convertAllProductToProductDetailDto(productList);
        return productDetailDTOList;
    }

}
