package com.example.Ecomproject.service;

import com.example.Ecomproject.model.Product;
import com.example.Ecomproject.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProduct() {

        return repo.findAll();

    }





    public Product getProductById(int id){
        return  repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
           product.setImageName(imageFile.getOriginalFilename());
           product.setImageType(imageFile.getContentType());
           product.setImageDate(imageFile.getBytes());

      return repo.save(product);

    }
}