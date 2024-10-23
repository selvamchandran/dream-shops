package com.selvam.dreamshops.service.product;

import com.selvam.dreamshops.dto.ImageDto;
import com.selvam.dreamshops.dto.ProductDto;
import com.selvam.dreamshops.exceptions.AlreadyExistsException;
import com.selvam.dreamshops.exceptions.ResourceNotFoundException;
import com.selvam.dreamshops.model.Category;
import com.selvam.dreamshops.model.Image;
import com.selvam.dreamshops.model.Product;
import com.selvam.dreamshops.repository.CategoryRepository;
import com.selvam.dreamshops.repository.ImageRepository;
import com.selvam.dreamshops.repository.ProductRepository;
import com.selvam.dreamshops.request.AddProductRequest;
import com.selvam.dreamshops.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final ImageRepository imageRepository;

    @Override
    public Product addProduct(AddProductRequest request) {
        //Check if the category is found in the DB
        //If Yes, set it as the new product category
        //If No, then save it as a new category
        //Then set as the new product category
        if(productExists(request.getName(),request.getBrand()))
        {
            throw new AlreadyExistsException(request.getBrand()+ " " + request.getName()+" already exists, you may update this product instead" );
        }
        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);

                });
        request.setCategory(category);
        return productRepository.save(createProduct(request,category));

    }

    private boolean productExists(String name, String brand) {
        return productRepository.existsByNameAndBrand(name,brand);
    }

    //user defined method
    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Product Not Found"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, ()->{
                    throw new ResourceNotFoundException("Product Not Found!");});
    }

    @Override
    public Product updateProductById(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct->updateExistingProduct(existingProduct,request))
                .map(productRepository::save)
                .orElseThrow(()->new ResourceNotFoundException("Product Not Found!"));
    }
    //User Defined Method
    public Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request)
    {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setPrice(request.getPrice());
        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getAllProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getAllProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand,name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand,name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products)
    {
        return products.stream()
                .map(this::convertToDto).toList();
    }
    @Override
    public ProductDto convertToDto(Product product)
    {
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
        List<Image> images = imageRepository.findByProductId(product.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDtos);
        return productDto;
    }
}
