package kg.javaguides.ems.service;

import kg.javaguides.ems.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto updateCategory(Long categoryId, CategoryDto updatedCategory);
    void deleteCategory(Long categoryId);
}
