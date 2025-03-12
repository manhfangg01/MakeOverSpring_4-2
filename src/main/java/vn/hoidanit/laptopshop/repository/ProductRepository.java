package vn.hoidanit.laptopshop.repository;

import org.springframework.stereotype.Repository;

import jakarta.annotation.Nullable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import vn.hoidanit.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @SuppressWarnings("null")
    Page<Product> findAll(Pageable pageable);

    @SuppressWarnings("null")
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
}
