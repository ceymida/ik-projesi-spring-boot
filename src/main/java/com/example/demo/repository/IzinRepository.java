package com.example.demo.repository;

import com.example.demo.entity.Izin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IzinRepository extends JpaRepository<Izin, Long> {
    // JpaRepository sayesinde izin ekleme ve silme metotları otomatik gelir.
}