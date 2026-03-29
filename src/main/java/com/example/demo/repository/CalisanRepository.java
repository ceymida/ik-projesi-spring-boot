package com.example.demo.repository;

import com.example.demo.entity.Calisan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalisanRepository extends JpaRepository<Calisan,Long> {
    // JpaRepository sayesinde save(), findAll(), deleteById() gibi
    // tüm veritabanı metodları bize otomatik olarak arka planda hediye ediliyor.

}
