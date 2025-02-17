package com.example.Ecomproject.controller;

import com.example.Ecomproject.model.Product;
import com.example.Ecomproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
  private ProductService service;



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){
        return  new ResponseEntity<>(service.getAllProduct(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product=service.getProductById(id);

        if (product!=null){
            return  new ResponseEntity<>(product,HttpStatus.OK);
        }else {
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
       //return new ResponseEntity<>(service.getProductById(id),HttpStatus.OK);
    }


    @PostMapping("/product")
     public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
            Product product1=service.addProduct(product,imageFile);
            return new ResponseEntity<>(product1,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }

     @GetMapping("/product/{productId}/image")
     public ResponseEntity<byte[]>getImageByProductId(@PathVariable int productId){

        Product product=service.getProductById(productId);
        byte[]imageFile=product.getImageDate();

        return  ResponseEntity.ok()
                .contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);

     }



}
