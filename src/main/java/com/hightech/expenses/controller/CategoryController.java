package com.hightech.expenses.controller;

import com.hightech.expenses.domain.Category;
import com.hightech.expenses.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody @Valid Category c) {
        return categoryService.create(c);
    }

    @GetMapping
    public List<Category> list() { return categoryService.list(); }

    @GetMapping("/{id}")
    public Category get(@PathVariable Long id) { return categoryService.get(id); }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody @Valid Category c) {
        return categoryService.update(id, c);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { categoryService.delete(id); }
}
