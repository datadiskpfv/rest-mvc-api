package uk.co.datadisk.restmvcapi.services;

import org.springframework.stereotype.Service;
import uk.co.datadisk.restmvcapi.api.v1.mapper.CategoryMapper;
import uk.co.datadisk.restmvcapi.api.v1.model.CategoryDTO;
import uk.co.datadisk.restmvcapi.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategoriesExceptName(String name) {

        return categoryRepository.findAllByNameNotIn(name)
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> getAllCategoriesExceptId(Long Id) {

        return categoryRepository.findAllByIdNotIn(Id)
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }


    @Override
    public CategoryDTO getCategoryByName(String name) {

        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
