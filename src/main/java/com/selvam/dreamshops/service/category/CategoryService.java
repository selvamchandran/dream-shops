package com.selvam.dreamshops.service.category;

import com.selvam.dreamshops.exceptions.AlreadyExistsException;
import com.selvam.dreamshops.exceptions.ResourceNotFoundException;
import com.selvam.dreamshops.model.Category;
import com.selvam.dreamshops.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService{
    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Category Not Found!") );
    }

    @Override
    public Category getCategoryName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category)
                .filter(c->!categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(()-> new AlreadyExistsException(category.getName()+" already exists"));
    }

    @Override
    public Category updateCategory(Category category,Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory->{
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,()->{
                    throw new ResourceNotFoundException("Category Not Found!");
                });
    }
}
