package com.arikanogluulku.springfirst.converter;

import com.arikanogluulku.springfirst.dto.ProductDetailDTO;
import com.arikanogluulku.springfirst.dto.ProductDto;
import com.arikanogluulku.springfirst.entity.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface ProductConverter {

   ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

   @Mapping(source = "categoryId", target = "category.id")
   Product convertProductDtoToProduct(ProductDto productDto);

   @Mapping(source = "category.id", target = "categoryId")
   ProductDto converProductToProductDto(Product product);

   @Mapping(source ="price" , target ="productPrice")
   @Mapping(source ="name" , target ="productName")
   @Mapping(source ="category.name" , target ="categoryName")
   ProductDetailDTO convertAllProductToProductDetailDto(Product product);

   @Mapping(source ="price" , target ="productPrice")
   @Mapping(source ="name" , target ="productName")
   @Mapping(source ="category.name" , target ="categoryName")
   List<ProductDetailDTO> convertAllProductToProductDetailDto(List<Product> productList);

   @AfterMapping
   default void setNulls(@MappingTarget final Product product, ProductDto productDto){
      if (productDto.getCategoryId() == null){
         product.setCategory(null);
      }
   }
}


