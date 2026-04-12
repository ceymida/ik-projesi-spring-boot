package com.example.demo.controller;

import com.example.demo.entity.Calisan;
import com.example.demo.service.CalisanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}