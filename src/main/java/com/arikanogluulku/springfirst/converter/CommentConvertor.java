package com.arikanogluulku.springfirst.converter;

import com.arikanogluulku.springfirst.dto.ProductCommentDto;
import com.arikanogluulku.springfirst.entity.ProductComment;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CommentConvertor {

    CommentConvertor INSTANCE = Mappers.getMapper(CommentConvertor.class);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "userId", source = "user.id")
    ProductCommentDto convertProductCommentToProductCommentDto(ProductComment productComment);

    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "userId", source = "user.id")
    List<ProductCommentDto> convertAllProductCommentToProductCommentDto(List<ProductComment> productComments);

    // @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "user.id", source = "userId")
    ProductComment convertProductCommentDtoToProductComment(ProductCommentDto productCommentDto);


}
