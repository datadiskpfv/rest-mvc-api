package uk.co.datadisk.restmvcapi.services;

import uk.co.datadisk.restmvcapi.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
