package com.abhay.ecom_project.service;


import com.abhay.ecom_project.model.Product;
import com.abhay.ecom_project.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts(){

        return repo.findAll();
    }

    public Product getProductById(int id){

        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product prod, MultipartFile imageFile) throws IOException {
        prod.setImageName(imageFile.getName());
        prod.setImageType(imageFile.getContentType());
        prod.setImageData(imageFile.getBytes());
        return repo.save(prod);
    }

    public Product updateProduct(int id, Product prod,MultipartFile imageFile) throws IOException {
        Product prod1 = repo.findById(id).orElse(null);

        prod1.setName(prod.getName());
        prod1.setDesc(prod.getDesc());
        prod1.setBrand(prod.getBrand());
        prod1.setPrice(prod.getPrice());
        prod1.setCategory(prod.getCategory());
        prod1.setReleaseDate(prod.getReleaseDate());
        prod1.setAvailable(prod.isAvailable());
        prod1.setQuantity(prod.getQuantity());

        prod1.setImageName(imageFile.getName());
        prod1.setImageType(imageFile.getContentType());
        prod1.setImageData(imageFile.getBytes());
        return repo.save(prod1);
    }

    public void deleteProduct(int id){
       repo.deleteById(id);
    }

    public List<Product> searchProduct(String keyword){
        List<Product> ans = repo.searchProducts(keyword);
        return ans;
    }

}
