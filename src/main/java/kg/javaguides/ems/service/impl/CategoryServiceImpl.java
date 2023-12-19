package kg.javaguides.ems.service.impl;

import kg.javaguides.ems.dto.CategoryDto;
import kg.javaguides.ems.entity.Category;
import kg.javaguides.ems.exception.ResourceNotFoundException;
import kg.javaguides.ems.mapper.CategoryMapper;
import kg.javaguides.ems.repository.CategoryRepository;
import kg.javaguides.ems.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory =  categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Category is not exist with given Id: " + categoryId));
        return CategoryMapper.mapToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map((category) -> CategoryMapper.mapToCategoryDto(category))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long categoryId, CategoryDto updatedCategory) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category is not exist with given Id: " + categoryId));

        category.setCategory(updatedCategory.getCategory());
        category.setName(updatedCategory.getName());
        Category updatedCategoryObj = categoryRepository.save(category);

        return CategoryMapper.mapToCategoryDto(updatedCategoryObj);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category is not exist with given Id: " + categoryId));
        categoryRepository.deleteById(categoryId);
    }
}
