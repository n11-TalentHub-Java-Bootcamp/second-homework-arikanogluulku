package com.arikanogluulku.springfirst.controller;

import com.arikanogluulku.springfirst.converter.CommentConvertor;
import com.arikanogluulku.springfirst.dto.ProductCommentDto;
import com.arikanogluulku.springfirst.entity.ProductComment;
import com.arikanogluulku.springfirst.exception.ProductCommentsNotFoundException;
import com.arikanogluulku.springfirst.exception.UsersCommentsNotFoundException;
import com.arikanogluulku.springfirst.service.entityService.ProductCommentEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class ProductCommentController {

    @Autowired
    ProductCommentEntityService productCommentEntityService;

    @GetMapping("/user/{userId}")
    public List<ProductCommentDto> findAllByUserComment(@PathVariable Long userId) {
        List<ProductComment> commentList = productCommentEntityService.findAllByUserComment(userId);
        if (commentList.size() == 0) {
            throw new UsersCommentsNotFoundException("Id : " + userId + " The user has not written any comments yet..");
        }
        List<ProductCommentDto> commentDtoList = CommentConvertor.INSTANCE.convertAllProductCommentToProductCommentDto(commentList);
        return commentDtoList;
    }

    @GetMapping("/product/{productId}")
    public List<ProductCommentDto> findAllByProductComment(@PathVariable Long productId) {
        List<ProductComment> commentList = productCommentEntityService.findAllByProductComment(productId);
        if (commentList.size() == 0) {
            throw new ProductCommentsNotFoundException("Id : " + productId + " There are no comments for the product yet.");
        }
        List<ProductCommentDto> commentDtoList = CommentConvertor.INSTANCE.convertAllProductCommentToProductCommentDto(commentList);
        return commentDtoList;
    }

    @PostMapping("")
    public ResponseEntity<Object> saveProductComment(@RequestBody ProductCommentDto productCommentDto) {
        ProductComment productComment = CommentConvertor.INSTANCE.convertProductCommentDtoToProductComment(productCommentDto);

        productComment = productCommentEntityService.save(productComment);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productComment.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public void deleteUserComment(@PathVariable Long id) {
        productCommentEntityService.deleteById(id);
    }
}

