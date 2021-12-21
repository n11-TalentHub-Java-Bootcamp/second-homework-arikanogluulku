package com.arikanogluulku.springfirst;

import com.arikanogluulku.springfirst.entity.Category;
import com.arikanogluulku.springfirst.service.entityService.CategoryEntityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringBootTwoWeekApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootTwoWeekApplication.class, args);
        CategoryEntityService service = applicationContext.getBean(CategoryEntityService.class);
        List<Category> categoryList = service.findAllByNameEndsWith("f");
        for (Category category : categoryList) {
            System.out.println(category.getName());
        }
    }

}
