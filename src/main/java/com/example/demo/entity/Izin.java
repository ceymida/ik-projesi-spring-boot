package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "izinler")
public class Izin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String izinTuru; // Yıllık, Mazeret vs.
    private LocalDate baslangicTarihi;
    private LocalDate bitisTarihi;
    private String onayDurumu; // Onay Bekliyor, Onaylandı vs.

    // İznin kime ait olduğunu belirtiyoruz (ManyToOne = Çoktan Bire ilişki)
    @ManyToOne
    @JoinColumn(name = "calisan_id")
    @JsonIgnore // API'den veri çekerken sonsuz döngüye girmeyi engeller
    private Calisan calisan;

    public Izin() {}

    // --- Getter ve Setter Metotları ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIzinTuru() { return izinTuru; }
    public void setIzinTuru(String izinTuru) { this.izinTuru = izinTuru; }

    public LocalDate getBaslangicTarihi() { return baslangicTarihi; }
    public void setBaslangicTarihi(LocalDate baslangicTarihi) { this.baslangicTarihi = baslangicTarihi; }

    public LocalDate getBitisTarihi() { return bitisTarihi; }
    public void setBitisTarihi(LocalDate bitisTarihi) { this.bitisTarihi = bitisTarihi; }

    public String getOnayDurumu() { return onayDurumu; }
    public void setOnayDurumu(String onayDurumu) { this.onayDurumu = onayDurumu; }

    public Calisan getCalisan() { return calisan; }
    public void setCalisan(Calisan calisan) { this.calisan = calisan; }
}