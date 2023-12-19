package kg.javaguides.ems.repository;

import kg.javaguides.ems.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
