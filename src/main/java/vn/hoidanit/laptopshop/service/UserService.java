package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;

import vn.hoidanit.laptopshop.repository.RoleRepositoty;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.domain.Role;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepositoty roleRepositoty;

    public UserService(UserRepository userRepository, RoleRepositoty roleRepositoty) {
        this.userRepository = userRepository;
        this.roleRepositoty = roleRepositoty;
    }

    public String handleHello() {
        return "fangg";
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }

    public void handleSaveUser(User hoidanit) {
        this.userRepository.save(hoidanit);
    }

    public User getUserById(long id) {
        return this.userRepository.findById(id).get();
    }

    public void deleteUserById(long id) {
        this.userRepository.deleteById(id);
    }

    public Role getRoleByName(String name) {
        return this.roleRepositoty.findByName(name);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO) {
        User newUser = new User();
        newUser.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        newUser.setEmail(registerDTO.getEmail());
        newUser.setPassword(registerDTO.getPassword());
        return newUser;
    }

    public boolean checkEmailExisted(String email) {
        // if( this.userRepository.findByEmail(email)==null){
        // return false;
        // }
        // return true;

        // To be faster
        return this.userRepository.existsByEmail(email);

    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

}
