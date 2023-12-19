package kg.javaguides.ems.mapper;

import kg.javaguides.ems.dto.CategoryDto;
import kg.javaguides.ems.entity.Category;

public class CategoryMapper {
    public static CategoryDto mapToCategoryDto(Category category){
        return new CategoryDto(
                category.getId(),
                category.getCategory(),
                category.getName()
        );
    }

    public static Category mapToCategory(CategoryDto categoryDto){
        return new Category(
                categoryDto.getId(),
                categoryDto.getCategory(),
                categoryDto.getName()
        );
    }
}
