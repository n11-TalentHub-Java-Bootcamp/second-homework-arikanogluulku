package com.arikanogluulku.springfirst.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "PRODUCT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "category"})
@JsonFilter("ProductFilter")
public class Product implements Serializable {
    @SequenceGenerator(name = "generator", sequenceName = "PRODUCT_ID_SEQ")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "PRICE", precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "DATE_OF_REGISTRATION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfRegistration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CATEGORY",
            foreignKey = @ForeignKey(name = "FK_Product_Category_Id")
    )
    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long productId) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return id == null ? " " : id.toString();
    }
}
