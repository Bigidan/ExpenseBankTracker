package com.example.expensebanktracker.repository;

import com.example.expensebanktracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
