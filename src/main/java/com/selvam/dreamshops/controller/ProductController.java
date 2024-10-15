package com.selvam.dreamshops.controller;

import com.selvam.dreamshops.exceptions.ProductNotFoundException;
import com.selvam.dreamshops.exceptions.ResourceNotFoundException;
import com.selvam.dreamshops.model.Category;
import com.selvam.dreamshops.model.Product;
import com.selvam.dreamshops.request.AddProductRequest;
import com.selvam.dreamshops.request.ProductUpdateRequest;
import com.selvam.dreamshops.response.ApiResponse;
import com.selvam.dreamshops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts()
    {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("Success!",products));
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId)
    {
        try {
            Product product=productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Success!",product));
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest product)
    {
        try {
            Product theProduct = productService.addProduct(product);
            return ResponseEntity.ok(new ApiResponse("Add Product Success!",theProduct));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/product/{productId}/update")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody ProductUpdateRequest product,@PathVariable Long productId)
    {
        try {
            Product updatedProduct = productService.updateProductById(product,productId);
            return ResponseEntity.ok(new ApiResponse("Update success!",updatedProduct));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/product/{id}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId)
    {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Delete product success!", productId));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/by/brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@PathVariable String brandName, @PathVariable String productName)
    {
        try {
            List<Product> products = productService.getAllProductsByBrandAndName(brandName,productName);
            if(products.isEmpty())
            {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
           return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                   .body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/products/by/category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@PathVariable String categoryName, @PathVariable String brandName)
    {
        try {
            List<Product> products = productService.getAllProductsByCategoryAndBrand(categoryName,brandName);
            if(products.isEmpty())
            {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("error",e.getMessage()));
        }
    }

    @GetMapping("/products/{name}/products")
    public ResponseEntity<ApiResponse> getProductByName(@PathVariable String name)
    {
        try {
            List<Product> products = productService.getProductsByName(name);
            if(products.isEmpty())
            {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("error",e.getMessage()));
        }
    }

    @GetMapping("/products/by-brand")
    public ResponseEntity<ApiResponse> findProductByBrand(@RequestParam String brand)
    {
        try {
            List<Product> products = productService.getAllProductsByBrand(brand);
            if(products.isEmpty())
            {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/{category}/all/products")
    public ResponseEntity<ApiResponse> findProductByCategory(@PathVariable String category)
    {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty())
            {
                return ResponseEntity.status(NOT_FOUND)
                        .body(new ApiResponse("No products found",null));
            }
            return ResponseEntity.ok(new ApiResponse("success",products));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/product/count/by-brand/and-name")
    public ResponseEntity<ApiResponse> countProductsByBrandAndName(@RequestParam String brand,@RequestParam String name)
    {
        try {
            var productCount = productService.countProductsByBrandAndName(brand,name);
            return ResponseEntity.ok(new ApiResponse("Product Count!", productCount));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse(e.getMessage(), null));
        }
    }


}
