package com.example.elearning.service;

import com.example.elearning.dto.CategoryDTO;
import com.example.elearning.model.Category;
import com.example.elearning.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.findAll()
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));
        return mapToDTO(category);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO dto) {
        if (categoryRepo.existsByName(dto.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category name already exists");
        }
        Category category = modelMapper.map(dto, Category.class);
        return mapToDTO(categoryRepo.save(category));
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found"));

        modelMapper.map(dto, category);
        return mapToDTO(categoryRepo.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found");
        }
        categoryRepo.deleteById(id);
    }

    private CategoryDTO mapToDTO(Category c) {
        return modelMapper.map(c, CategoryDTO.class);
    }
}

