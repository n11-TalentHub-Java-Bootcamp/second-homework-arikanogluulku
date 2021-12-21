package com.arikanogluulku.springfirst.controller;

import com.arikanogluulku.springfirst.converter.ProductConverter;
import com.arikanogluulku.springfirst.dto.ProductDetailDTO;
import com.arikanogluulku.springfirst.dto.ProductDto;
import com.arikanogluulku.springfirst.entity.Product;
import com.arikanogluulku.springfirst.exception.ProductNotFoundException;
import com.arikanogluulku.springfirst.service.entityService.CategoryEntityService;
import com.arikanogluulku.springfirst.service.entityService.ProductEntityService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductController {

    @Autowired
    private ProductEntityService productEntityService;

    @GetMapping("")
    public MappingJacksonValue findAllProductList() {
        List<Product> productList = productEntityService.findAll();

        String filterName = "ProductFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        MappingJacksonValue mapping = new MappingJacksonValue(productList);

        mapping.setFilters(filters);

        return mapping;
    }


    @GetMapping("{id}")
    public MappingJacksonValue findProductById(@PathVariable Long id) {

        Product product = productEntityService.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found : " + id);
        }
        WebMvcLinkBuilder linkToProduct = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(this.getClass())
                        .findAllProductList()
        );

        ProductDto productDto = ProductConverter.INSTANCE.converProductToProductDto(product);

        String filterName = "ProductDtoFilter";

        SimpleFilterProvider filters = getProductFilterProvider(filterName);

        EntityModel entityModel = EntityModel.of(productDto);

        entityModel.add(linkToProduct.withRel("all-product"));

        MappingJacksonValue mapping = new MappingJacksonValue(entityModel);

        mapping.setFilters(filters);

        return mapping;
    }


    @GetMapping("detail/{id}")
    public ProductDetailDTO findProductDtoById(@PathVariable Long id) {
        Product product = productEntityService.findById(id);

        if (product == null) {
            throw new ProductNotFoundException("Product not found : " + id);
        }

        ProductDetailDTO productDetailDTO = ProductConverter.INSTANCE.convertAllProductToProductDetailDto(product);
        return productDetailDTO;
    }

    @GetMapping("/category/{categoryId}")
    public List<ProductDetailDTO> findAllByCategoryId(@PathVariable Long categoryId) {
        List<Product> productList = productEntityService.findAllByCategoryOrderByIdDesc(categoryId);

        List<ProductDetailDTO> productDetailDtoList = ProductConverter.INSTANCE.convertAllProductToProductDetailDto(productList);

        return productDetailDtoList;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveProduct(@RequestBody ProductDto productDto) {
        Product product = ProductConverter.INSTANCE.convertProductDtoToProduct(productDto);

        product = productEntityService.save(product);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable Long id) {
        productEntityService.deleteById(id);
    }

    private SimpleBeanPropertyFilter getProductFilter() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "price", "dateOfRegistration");
    }

    private SimpleFilterProvider getProductFilterProvider(String filterName) {
        SimpleBeanPropertyFilter filter = getProductFilter();
        return new SimpleFilterProvider().addFilter(filterName, filter);
    }
}
