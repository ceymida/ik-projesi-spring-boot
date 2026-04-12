package com.example.demo.service;

import com.example.demo.entity.Calisan;
import com.example.demo.repository.CalisanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring bu sınıfı uygulama başladığında otomatik olarak yaratır ve yönetir.
public class CalisanService {

    // Servisimizin çalışması için Repository'ye ihtiyacı var.
    private final CalisanRepository calisanRepository;

    // "Constructor Injection": Spring, CalisanRepository'yi buraya otomatik getirir.
    public CalisanService(CalisanRepository calisanRepository) {
        this.calisanRepository = calisanRepository;
    }

    // 1. Kural: Çalışan Ekleme (Maaş ve İsim Kontrolüyle)
    public Calisan calisanKaydet(Calisan calisan) {
        // İş kuralı: Maaş 17002 TL'den az olamaz
        if (calisan.getMaas() < 17002.0) {
            throw new RuntimeException("Hata: Maaş asgari ücretten düşük olamaz!");
        }

        // İş kuralı: İsim boş olamaz
        if (calisan.getAd() == null || calisan.getAd().isEmpty()) {
            throw new RuntimeException("Hata: Çalışan adı boş bırakılamaz!");
        }

        // Kurallar tamamsa veritabanına gönderiyoruz
        return calisanRepository.save(calisan);
    }

    // 2. Kural: Tüm Çalışanları Listeleme
    public List<Calisan> tumCalisanlariGetir() {
        return calisanRepository.findAll();
    }

    // 3. Kural: Çalışan Silme (Varlık Kontrolüyle)
    public void calisanSil(Long id) {
        // İş kuralı: Olmayan birini silemezsin
        if (!calisanRepository.existsById(id)) {
            throw new RuntimeException("Hata: ID'si " + id + " olan bir çalışan bulunamadı!");
        }
        calisanRepository.deleteById(id);
    }
    public Calisan calisanGuncelle(Long id, Calisan guncelBilgiler) {
        // 1. ADIM: Önce veritabanında bu ID'ye sahip biri var mı diye bak
        // Bulamazsa anında hata fırlatır ve işlemi keser.
        Calisan mevcutCalisan = calisanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hata: Güncellenecek çalışan (ID: " + id + ") bulunamadı!"));

        // 2. ADIM: Yeni gelen maaş kurallara (asgari ücrete) uygun mu kontrol et
        if (guncelBilgiler.getMaas() < 17002.0) {
            throw new RuntimeException("KURAL İHLALİ: Asgari ücretin altında maaş girilemez!");
        }

        // 3. ADIM: Eski çalışanın bilgilerini yeni gelen bilgilerle değiştir
        mevcutCalisan.setAd(guncelBilgiler.getAd());
        mevcutCalisan.setSoyad(guncelBilgiler.getSoyad());
        mevcutCalisan.setMaas(guncelBilgiler.getMaas());

        // 4. ADIM: Değişiklikleri veritabanına kaydet (ID'si olduğu için yeni kayıt açmaz, var olanı günceller)
        return calisanRepository.save(mevcutCalisan);
    }

}