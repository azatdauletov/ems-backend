package kg.javaguides.ems.service.impl;

import kg.javaguides.ems.dto.ProductDto;
import kg.javaguides.ems.entity.Product;
import kg.javaguides.ems.exception.ResourceNotFoundException;
import kg.javaguides.ems.mapper.ProductMapper;
import kg.javaguides.ems.repository.ProductRepository;
import kg.javaguides.ems.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct =  productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new ResourceNotFoundException("Product with given id not found" + productId ));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> ProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->  new  ResourceNotFoundException("Product with given Id not found!!!" + productId));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setCount(updatedProduct.getCount());
        Product UpdateProductObj =  productRepository.save(product);
        return ProductMapper.mapToProductDto(UpdateProductObj);
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with given Id not found!!!"));
        productRepository.deleteById(productId);
    }
}
