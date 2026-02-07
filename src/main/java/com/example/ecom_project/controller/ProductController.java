package com.example.ecom_project.controller;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.service.ProductService;
import jakarta.annotation.Resource;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLOutput;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;

    @RequestMapping("/ ")
    public String greet(){
        return "Hello world";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){

        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }
     @GetMapping("/product/{id}")
    public  ResponseEntity<Product> getProductById(@PathVariable int id){
        Product product = service.getProductById(id);
        if(product!=null){
         return  new  ResponseEntity<>(product,HttpStatus.OK);
    }
    else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
     }
// without image
//      @PostMapping("/product")
//     public ResponseEntity<?> addProduct(@RequestBody Product product){
//        service.addProduct();
//     }

    @PostMapping("/product")   // with image
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try {
            Product product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProdcutId(@PathVariable("id")int id){
       Product pr= service.getProductById(id);
       byte[] image = pr.getImageData();
       return ResponseEntity.ok().contentType(MediaType.valueOf(pr.getImageType()))
               .body(pr.getImageData());
    }
   @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
       Product pr;
       try {
           pr = service.updateProduct(id, product, imageFile);
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
          if(pr!=null){
              return new ResponseEntity<>("updated",HttpStatus.OK);
          }
          else{
              return new ResponseEntity<>("failed to update ",HttpStatus.BAD_REQUEST);
          }
   }

   @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
       Product pr= service.getProductById(id);
       if(pr!=null){
           service.delete(id);
           return new ResponseEntity<>("deleted",HttpStatus.OK);

       }
       else{
           return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
       }


   }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam("keyword") String keyword){
        List<Product> products =service.searchProduct(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);

    }





















}
