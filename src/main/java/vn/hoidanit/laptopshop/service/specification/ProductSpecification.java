package vn.hoidanit.laptopshop.service.specification;

import org.springframework.data.jpa.domain.Specification;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;

public class ProductSpecification {
    public static Specification<Product> nameLike(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(Product_.NAME), "%" + name + "%");
        // "%" + name + "%" --> tìm kiếm tất cả các tên có chữ "name"
    }

    // public Specification<Product> queryByName(String name) {
    // return (root, query, builder) -> builder.like(root.get(Product_.NAME), "%" +
    // name + "%");
    // }
}
