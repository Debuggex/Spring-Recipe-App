package spring.framework.app.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface ImageService {

    void saveImageFile(Long id, MultipartFile file);
}
