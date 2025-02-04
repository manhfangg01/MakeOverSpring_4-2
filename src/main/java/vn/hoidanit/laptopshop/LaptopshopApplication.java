package vn.hoidanit.laptopshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

// @SpringBootApplication
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class) // <=>
																															// disable
																															// spring
																															// security
// exclude triển khai hết trừ mấy thằng được exclude, dĩ nhiên để tiện cho việc
// thao tác phát triển thôi chứ bình thường đừng có bỏ bảo mật của spring
// security đi nhé
// . class được dùng để đại diện cho các lớp của các thư viện trên

public class LaptopshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaptopshopApplication.class, args);
		// ApplicationContext abc = SpringApplication.run(LaptopshopApplication.class,
		// args);
		// for (String s : abc.getBeanDefinitionNames()) {
		// System.out.println(s);
		// }
	}

}
