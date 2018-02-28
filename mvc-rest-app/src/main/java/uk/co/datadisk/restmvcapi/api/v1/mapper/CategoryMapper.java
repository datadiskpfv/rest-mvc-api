package uk.co.datadisk.restmvcapi.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import uk.co.datadisk.restmvcapi.api.v1.model.CategoryDTO;
import uk.co.datadisk.restmvcapi.domain.Category;

@Mapper(componentModel="spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO categoryToCategoryDTO(Category category);
}