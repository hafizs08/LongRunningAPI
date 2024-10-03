package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.HasilProses;

public interface HasilProsesRepo extends JpaRepository<HasilProses, Long> {
}
