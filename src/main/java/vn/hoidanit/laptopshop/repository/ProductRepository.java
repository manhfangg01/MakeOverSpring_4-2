package vn.hoidanit.laptopshop.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.hoidanit.laptopshop.domain.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findById(long id);
}
