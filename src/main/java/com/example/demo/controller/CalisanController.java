package com.example.demo.controller;

import com.example.demo.entity.Calisan;
import com.example.demo.service.CalisanService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Izin;


import java.util.Arrays;
import java.util.List;
import org.springframework.web.client.RestTemplate;

@RestController // Spring'e mesaj: "Bu sınıf dış dünyaya JSON formatında veri sunan bir API'dir."
@RequestMapping("/api/calisanlar") // Tarayıcıdan hangi adrese gidileceğini belirliyoruz.
public class CalisanController {

    private final CalisanService calisanService;

    // Bağımlılık Enjeksiyonu: Garsonun aşçıyı (Service'i) tanıması lazım.
    public CalisanController(CalisanService calisanService) {
        this.calisanService = calisanService;
    }

    // --- DIŞARIYA VERİ SUNMA (Okuma - GET İşlemi) ---
    @GetMapping
    public List<Calisan> calisanlariListele() {
        // Gelen isteği alıp Service katmanındaki metoda yönlendiriyoruz
        return calisanService.tumCalisanlariGetir();
    }

    // --- DIŞARIDAN VERİ ALMA (Yazma - POST İşlemi) ---
    @PostMapping
    public Calisan yeniCalisanEkle(@RequestBody Calisan calisan) {
        // Dışarıdan gelen JSON verisini Java nesnesine (Calisan) çevirip Service'e gönderiyoruz
        return calisanService.calisanKaydet(calisan);
    }
    @PutMapping("/{id}") // Burada başında / olduğuna ve süslü paranteze dikkat!
    public Calisan calisanGuncelle(@PathVariable Long id, @RequestBody Calisan guncelBilgiler) {
        return calisanService.calisanGuncelle(id, guncelBilgiler);
    }

    @DeleteMapping("/{id}") // Burada da aynı şekilde
    public void calisanSil(@PathVariable Long id) { calisanService.calisanSil(id); }
    // --- ÇALIŞANA İZİN EKLEME (POST İşlemi) ---
    // Adres: /api/calisanlar/{id}/izinler
    @PostMapping("/{id}/izinler")
    public Izin calisanaIzinEkle(@PathVariable Long id, @RequestBody Izin izin) {
        return calisanService.izinEkle(id, izin);
    }
    // --- EHLİYET SINIFLARI API'Sİ (GET İşlemi) ---
    // Adres: /api/calisanlar/ehliyet-siniflari
    @GetMapping("/ehliyet-siniflari")
    public List<String> ehliyetSiniflariniGetir() {
        return Arrays.asList("Yok", "M", "A1", "A2", "A", "B1", "B", "BE", "C1", "C1E", "C", "CE", "D1", "D1E", "D", "DE", "F", "G");
    }

    // --- TÜRKİYE ÜNİVERSİTELERİ GLOBAL API KÖPRÜSÜ ---

    @GetMapping("/universiteler")
    public Object universiteleriGetir() {
        try {
            // Türkiye'deki şehirler ve okullar için açık kaynak, stabil bir API
            String apiUrl = "https://turkiyeapi.dev/api/v1/universities";
            RestTemplate restTemplate = new RestTemplate();
            return restTemplate.getForObject(apiUrl, Object.class);

        } catch (Exception e) {
            System.out.println("Bağlantı Hatası: " + e.getMessage());
            return null;
        }
    }
}