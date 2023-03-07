package com.metsoft.productservice.api;

import com.metsoft.productservice.model.Category;
import com.metsoft.productservice.model.GetProductDetail;
import com.metsoft.productservice.model.Product;
import com.metsoft.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository;

    private final WebClient webClient;
    @Autowired
    public ProductController(ProductRepository productRepository, WebClient webClient) {
        this.productRepository = productRepository;
        this.webClient = webClient;
    }
    @GetMapping("")
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @GetMapping("/{id}")
    public GetProductDetail findById(@PathVariable("id")int id) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(()->new Exception("Not found"));
        Category category=webClient.get().uri("http://localhost:9091/api/categories/"+product.getCategoryId()).retrieve().bodyToMono(Category.class).block();
        GetProductDetail productDetail=new GetProductDetail();
        productDetail.setCategory(category);
        productDetail.setId(id);
        productDetail.setName(product.getName());
        productDetail.setDescription(product.getDescription());
        productDetail.setPrice(product.getPrice());
        return productDetail;
    }
    @PostMapping("")
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
}
