package com.hightech.expenses.service;

import com.hightech.expenses.domain.Category;
import com.hightech.expenses.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category create(Category c) {
        return categoryRepository.save(c);
    }

    @Transactional(readOnly = true)
    public List<Category> list() { return categoryRepository.findAll(); }

    @Transactional(readOnly = true)
    public Category get(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    public Category update(Long id, Category c) {
        Category existing = get(id);
        existing.setNome(c.getNome());
        existing.setDescricao(c.getDescricao());
        existing.setCor(c.getCor());
        existing.setIcone(c.getIcone());
        existing.setTipo(c.getTipo());
        existing.setUsuario(c.getUsuario());
        existing.setPadrao(c.isPadrao());
        return categoryRepository.save(existing);
    }

    public void delete(Long id) {
        Category c = get(id);
        categoryRepository.delete(c);
    }
}
