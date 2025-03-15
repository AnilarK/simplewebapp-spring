package com.abhay.ecom_project.controller;

import com.abhay.ecom_project.model.Product;
import com.abhay.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping("/")
    public String greet(){
        return "hello samosa";
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts() , HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
        Product prod= service.getProductById(id);

        if(prod != null){
            return new ResponseEntity<>(prod, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<?> uploadProduct(
            @RequestPart Product prod,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            Product prod1 = service.addProduct(prod, imageFile);
            return new ResponseEntity<>(prod1, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR );
        }
    }

    @GetMapping("/products/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id){
        Product prod= service.getProductById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(prod.getImageType()))
                .body(prod.getImageData());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id ,
                                                 @RequestPart Product prod ,
                                                 @RequestPart MultipartFile imageFile ) {
        try{
            Product prod1 = service.updateProduct(id,prod,imageFile);
            return new ResponseEntity<>(prod1, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        try{
            service.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<?> searchProduct(@RequestParam String keyword){
        try {
            List<Product> ans = service.searchProduct(keyword);
            return new ResponseEntity<>(ans,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
