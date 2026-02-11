package com.example.ecom_project.service;

import com.example.ecom_project.Exception.ProductNotFound;
import com.example.ecom_project.model.Product;
import com.example.ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {
     @Autowired
     public ProductRepo repo;
    public List<Product> getAllProducts() {
        if(repo.findAll()==null){
            throw new ProductNotFound("not available");
        }
        return  repo.findAll();
    }

    public  Product getProductById(int id) {

        return repo.findById(id).orElseThrow(()-> new IllegalArgumentException("user with id does not exist"));

    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
        return repo.save(product);
    }


    public Product updateProduct(int id, Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());
       return  repo.save(product);

    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword) {
        return repo.searchProduct(keyword);
    }
}
