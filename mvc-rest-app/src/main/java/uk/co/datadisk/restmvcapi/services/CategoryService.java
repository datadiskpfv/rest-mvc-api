package uk.co.datadisk.restmvcapi.services;

import uk.co.datadisk.restmvcapi.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    List<CategoryDTO> getAllCategoriesExceptName(String name);

    List<CategoryDTO> getAllCategoriesExceptId(Long Id);

    CategoryDTO getCategoryByName(String name);

}
