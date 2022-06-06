package com.example.diplom.repository;


import com.example.diplom.entity.MetalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetalCategoryRepository extends JpaRepository<MetalCategory, Long> {
}
