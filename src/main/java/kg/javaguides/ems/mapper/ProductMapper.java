package kg.javaguides.ems.mapper;

import kg.javaguides.ems.dto.ProductDto;
import kg.javaguides.ems.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCount()
        );
    }
    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getName(),
                productDto.getDescription(),
                productDto.getCount()
        );
    }
}
