package com.example.demo.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "calisanlar")


public class Calisan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ad;
    private String soyad;
    private double maas;
    private String departman;
    private String unvan;
    private String telefon;
    private String okul;
    private LocalDate iseGirisTarihi;
    private LocalDate istenCikisTarihi;
    private String medeniHal;
    private String ehliyet;

    public Calisan() {

    }
    public Calisan(String ad, String soyad, double maas) {
        this.ad = ad;
        this.soyad = soyad;
        this.maas = maas;
        this.departman = departman;
        this.unvan = unvan;
        this.telefon = telefon;
        this.okul = okul;
        this.iseGirisTarihi = iseGirisTarihi;
        this.istenCikisTarihi = istenCikisTarihi;
        this.medeniHal = medeniHal;
        this.ehliyet = ehliyet;

    }
    // bir calisanin birden fazla izni olabilir o yuzden onetomany kurduk.
    @OneToMany(mappedBy = "calisan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Izin> izinler;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }
    public String getSoyad() {
        return soyad;
    }
    public double getMaas() {
        return maas;
    }

    public void setMaas(double maas) {
        this.maas = maas;
    }
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getDepartman() { return departman; }
    public void setDepartman(String departman) { this.departman = departman; }

    public String getUnvan() { return unvan; }
    public void setUnvan(String unvan) { this.unvan = unvan; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public List<Izin> getIzinler() { return izinler; }
    public void setIzinler(List<Izin> izinler) { this.izinler = izinler; }
    public String getMedeniHal() { return medeniHal; }
    public void setMedeniHal(String medenihal) { this.medeniHal = medenihal; }
    public String getEhliyet() { return ehliyet; }
    public void setEhliyet(String ehliyet) { this.ehliyet = ehliyet; }
    public LocalDate getIseGirisTarihi() { return iseGirisTarihi; }
    public void setIseGirisTarihi(LocalDate iseGirisTarihi) {this.iseGirisTarihi = iseGirisTarihi;}

    public String getOkul() {
        return okul;
    }
    public void setOkul(String okul) { this.okul = okul; }

    public LocalDate getIstenCikisTarihi() {
        return istenCikisTarihi;
    }
    public void setIstenCikisTarihi(LocalDate istenCikisTarihi) {this.istenCikisTarihi = istenCikisTarihi; }

}
