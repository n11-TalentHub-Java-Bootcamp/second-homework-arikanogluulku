package com.arikanogluulku.springfirst.converter;

import com.arikanogluulku.springfirst.dto.CategoryDto;
import com.arikanogluulku.springfirst.entity.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryConvertor {

    CategoryConvertor INSTANCE = Mappers.getMapper(CategoryConvertor.class);

    @Mapping(target = "topCategoryId", source = "topCategory.id")
    CategoryDto convertCategoryToCategoryDto(Category category);

    @Mapping(target = "topCategoryId", source = "topCategory.id")
    List<CategoryDto> convertAllCategoryToCategoryDtoList(List<Category> categoryList);

    @Mapping(target = "topCategory.id", source = "topCategoryId")
    Category convertCategoryDtoToCategory(CategoryDto categoryDto);

    @AfterMapping
    default void setNulls(@MappingTarget final Category category, CategoryDto categoryDto) {
        if (categoryDto.getTopCategoryId() == null) {
            category.setTopCategory(null);
        }
    }

}
