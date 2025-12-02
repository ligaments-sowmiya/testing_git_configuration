package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.dto.ProductDTO;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<ProductDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDTO> getById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    public ProductDTO create(ProductDTO dto) {
        Product entity = convertToEntity(dto);
        Product saved = repository.save(entity);
        return convertToDTO(saved);
    }

    public Optional<ProductDTO> update(Long id, ProductDTO dto) {
        return repository.findById(id).map(existing -> {
            updateEntityFromDTO(existing, dto);
            Product updated = repository.save(existing);
            return convertToDTO(updated);
        });
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    private ProductDTO convertToDTO(Product entity) {
        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setDescription(entity.getDescription());
        dto.setStock(entity.getStock());
        return dto;
    }

    private Product convertToEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setStock(dto.getStock());
        return entity;
    }

    private void updateEntityFromDTO(Product entity, ProductDTO dto) {
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
        entity.setStock(dto.getStock());
    }
}
