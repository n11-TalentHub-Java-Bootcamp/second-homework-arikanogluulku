package com.arikanogluulku.springfirst.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PRODUCT_COMMENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "user", "product"})

public class ProductComment {
    @SequenceGenerator(name = "generator", sequenceName = "PRODUCT_COMMENT_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name="ID" , nullable = false)
    private  Long id;

    @Column(name="COMMENT", length = 500)
    private String comment;

    @Column(name = "DATE_OF_COMMENT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfComment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRODUCT",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_COMMENT_PRODUCT_ID")
    )
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USER",
            foreignKey = @ForeignKey(name = "FK_PRODUCT_COMMENT_USER_ID")
    )

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(Date dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
