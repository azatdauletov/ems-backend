package kg.javaguides.ems.controller;

import kg.javaguides.ems.dto.ProductDto;
import kg.javaguides.ems.entity.Product;
import kg.javaguides.ems.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto savedProduct =  productService.createProduct(productDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId){
        ProductDto product = productService.getProductById(productId);
        return  ResponseEntity.ok(product);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId, @RequestBody ProductDto updatedProduct){
        ProductDto productDto =  productService.updateProduct(productId, updatedProduct);
        return ResponseEntity.ok(productDto);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully!!!");
    }
}
