package vn.hoidanit.laptopshop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import java.util.List;

//crud: create, read, update, delete
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @SuppressWarnings({ "null", "unchecked" })
    User save(User eric);

    void deleteById(long id);

    List<User> findOneByEmail(String email);

    @SuppressWarnings("null")
    List<User> findAll();

    User findById(long id); // null

    boolean existsByEmail(String email);

    User findByEmail(String email);

    @SuppressWarnings("null")
    Page<User> findAll(Pageable pageable); // Pagination

}
