package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class ProductController {
    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.productService = productService;
        this.uploadService = uploadService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = productService.fetchProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getProductCreatePage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product hoidanit,
            BindingResult newProductBidingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {

        List<FieldError> errors = newProductBidingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(error.getField() + " - " + error.getDefaultMessage());
        }

        // validate
        if (newProductBidingResult.hasErrors()) {
            return "/admin/product/create";
        }
        String imageProduct = this.uploadService.handleUploadSingleFileForProduct(file, "productImage");
        hoidanit.setImage(imageProduct);
        this.productService.handleSaveProduct(hoidanit);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}") // RequestMapping mặc định sử dụng method GET
    public String getProductDetailPage(Model model, @PathVariable long id) {

        model.addAttribute("id", id);
        Product product = this.productService.fetchProductById(id).get();
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateUserPage(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.fetchProductById(id).get();
        model.addAttribute("newProduct", currentProduct);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateUser(Model model, @ModelAttribute("newProduct") Product hoidanit) {
        Product currentProduct = this.productService.fetchProductById(hoidanit.getId()).get();
        if (currentProduct != null) {
            currentProduct.setName(hoidanit.getName());
            currentProduct.setPrice(hoidanit.getPrice());
            currentProduct.setDetailDesc(hoidanit.getDetailDesc());
            currentProduct.setShortDesc(hoidanit.getShortDesc());
            currentProduct.setQuantity(hoidanit.getQuantity());
            currentProduct.setFactory(hoidanit.getFactory());
            currentProduct.setTarget(hoidanit.getTarget());
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable long id) {
        Product product = this.productService.fetchProductById(id).get(); // Get dùng để lấy đối tượng từ optional nếu
                                                                          // nó không null
        if (product == null) {
            return "redirect:/admin/product";
        }
        model.addAttribute("newProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProductPage(Model model, @ModelAttribute("newProduct") Product hoidanit) {
        this.productService.deleteProductById(hoidanit.getId());
        return "redirect:/admin/product";
    }

}
