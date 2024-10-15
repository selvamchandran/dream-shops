package com.selvam.dreamshops.service.product;

import com.selvam.dreamshops.model.Product;
import com.selvam.dreamshops.request.AddProductRequest;
import com.selvam.dreamshops.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest request);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProductById(ProductUpdateRequest request, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getAllProductsByBrand(String brand);
    List<Product> getAllProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getAllProductsByBrandAndName(String brand, String name);
    Long countProductsByBrandAndName(String brand,String name);
}
