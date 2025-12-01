package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.dto.UserDTO;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<UserDTO> getAll() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getById(Long id) {
        return repository.findById(id).map(this::convertToDTO);
    }

    public UserDTO create(UserDTO dto) {
        User entity = convertToEntity(dto);
        User saved = repository.save(entity);
        return convertToDTO(saved);
    }

    public Optional<UserDTO> update(Long id, UserDTO dto) {
        return repository.findById(id).map(existing -> {
            updateEntityFromDTO(existing, dto);
            User updated = repository.save(existing);
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

    private UserDTO convertToDTO(User entity) {
        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setIsActive(entity.getIsActive());
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setIsActive(dto.getIsActive());
        return entity;
    }

    private void updateEntityFromDTO(User entity, UserDTO dto) {
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setIsActive(dto.getIsActive());
    }
}
