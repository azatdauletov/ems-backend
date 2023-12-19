package kg.javaguides.ems.controller;

import kg.javaguides.ems.dto.CategoryDto;
import kg.javaguides.ems.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryService categoryService;

    //  Category Rest Api
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    // Get Category ById Rest Api
    @GetMapping("{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long categoryId){
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    // Get All Category Rest Api
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> category = categoryService.getAllCategory();
        return ResponseEntity.ok(category);
    }

    // Update Category
    @PutMapping("{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId, @RequestBody CategoryDto updatedCategory){
        CategoryDto categoryDto = categoryService.updateCategory(categoryId, updatedCategory);
        return ResponseEntity.ok(categoryDto);
    }


    // Delete Category ById
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok("Category deleted successfully!");
    }

}
