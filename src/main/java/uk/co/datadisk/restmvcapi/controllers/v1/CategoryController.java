package uk.co.datadisk.restmvcapi.controllers.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.datadisk.restmvcapi.CategoryService;
import uk.co.datadisk.restmvcapi.api.v1.model.CategoryDTO;
import uk.co.datadisk.restmvcapi.api.v1.model.CategoryListDTO;

@Controller
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDTO> getallCatetories(){

        return new ResponseEntity<>(
                new CategoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        return new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }
}