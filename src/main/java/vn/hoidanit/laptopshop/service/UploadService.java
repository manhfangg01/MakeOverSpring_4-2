package vn.hoidanit.laptopshop.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
public class UploadService {
    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadSingleFile(MultipartFile file, String targetFolder) {
        String finalName = "";
        try {
            byte[] bytes = file.getBytes();

            String rootPath = this.servletContext.getRealPath("/resources/images"); // Đây là chuỗi chứa địa chỉ
                                                                                    // tuyệt đối của file cần lưu
                                                                                    // vào
            // Ở đây thông thường thì method getRealPath("") sẽ trả về địa chỉ tuyệt đối đến
            // thư mục webapp trên máy của người dùng -> .getRealPath("/resources/images")
            // --> ./webapp/resources/images

            File dir = new File(rootPath + File.separator + targetFolder); // ./webapp/resources/images+ "avatar"
            // =./webapp/resources/images/avatar (Nơi
            // chúng ta cần lưu uploaded file)
            // Lý do sử dụng rootPath vì trên mỗi máy tính đường absolute link dẫn tới
            // webapp khác nhau -> Dùng method này để lấy được khúc đầu của link, do khúc
            // sau thì giống nhau giữa các người dùng nên thao tác tĩnh được

            if (!dir.exists()) // Nếu mà nó không tồn tại
                dir.mkdirs(); // thì tạo mới

            // Create the file on server
            finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);
            // file được lưu có tên là mili giây hiện tại + tên file
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return finalName;

    }

    public void handleUploadMultiFile(MultipartFile[] files, String targetFolder) {
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            try {
                byte[] bytes = file.getBytes();

                String rootPath = this.servletContext.getRealPath("/resources/images"); // Đây là chuỗi chứa địa chỉ
                                                                                        // tuyệt đối của file cần lưu
                                                                                        // vào
                // Ở đây thông thường thì method getRealPath("") sẽ trả về địa chỉ tuyệt đối đến
                // thư mục webapp trên máy của người dùng -> .getRealPath("/resources/images")
                // --> ./webapp/resources/images

                File dir = new File(rootPath + File.separator + targetFolder); // ./webapp/resources/images+ "avatar"
                // =./webapp/resources/images/avatar (Nơi
                // chúng ta cần lưu uploaded file)
                // Lý do sử dụng rootPath vì trên mỗi máy tính đường absolute link dẫn tới
                // webapp khác nhau -> Dùng method này để lấy được khúc đầu của link, do khúc
                // sau thì giống nhau giữa các người dùng nên thao tác tĩnh được

                if (!dir.exists()) // Nếu mà nó không tồn tại
                    dir.mkdirs(); // thì tạo mới

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath() + File.separator +
                        +System.currentTimeMillis() + "-" + file.getOriginalFilename());
                // file được lưu có tên là mili giây hiện tại + tên file
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
