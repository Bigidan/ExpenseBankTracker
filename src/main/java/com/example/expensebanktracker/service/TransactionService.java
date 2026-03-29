package com.example.expensebanktracker.service;

import com.example.expensebanktracker.dto.TransactionDTO;
import com.example.expensebanktracker.entity.Category;
import com.example.expensebanktracker.entity.User;
import com.example.expensebanktracker.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import com.example.expensebanktracker.entity.Transaction;
import com.example.expensebanktracker.repository.TransactionRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<TransactionDTO> getAll(User user) {
        return repository.findAll().stream()
                .filter(t -> t.getUser() != null && t.getUser().getId().equals(user.getId()))
                .map(TransactionMapper::toDTO)
                .toList();
    }

    public TransactionDTO create(TransactionDTO dto, User user) {
        Category category = null;

        if (dto.getCategoryId() != null) {
            category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
        }

        Transaction t = TransactionMapper.toEntity(dto, category);

        t.setUser(user);

        return TransactionMapper.toDTO(repository.save(t));
    }

    public TransactionDTO update(Long id, TransactionDTO dto) {
        Transaction t = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        t.setAmount(dto.getAmount());
        t.setDescription(dto.getDescription());
        t.setDate(dto.getDate());

        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId()).orElse(null);
            t.setCategory(category);
        }

        return TransactionMapper.toDTO(repository.save(t));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<TransactionDTO> filterByDate(LocalDate from, LocalDate to) {
        return repository.findAll().stream()
                .filter(t -> !t.getDate().isBefore(from) && !t.getDate().isAfter(to))
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void importCsv(MultipartFile file) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            Transaction t = new Transaction();
            t.setDate(LocalDate.parse(parts[0]));
            t.setAmount(Double.parseDouble(parts[1]));
            t.setDescription(parts[2]);

            repository.save(t);
        }
    }

    public TransactionDTO getById(Long id) {
        return repository.findById(id)
                .map(TransactionMapper::toDTO)
                .orElseThrow();
    }
}
