package com.metsoft.categoryservice.api;

import com.metsoft.categoryservice.model.Category;
import com.metsoft.categoryservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") String id) throws Exception {
        return categoryRepository.findById(id).orElseThrow(()->new Exception("not found"));
    }
    @PostMapping("")
    public Category save(@RequestBody Category category){
        return categoryRepository.save(category);
    }
}
