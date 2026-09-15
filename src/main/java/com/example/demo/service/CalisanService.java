package com.example.demo.service;

import com.example.demo.entity.Calisan;
import com.example.demo.entity.Izin;
import com.example.demo.repository.CalisanRepository;
import com.example.demo.repository.IzinRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Spring bu sınıfı uygulama başladığında otomatik olarak yaratır ve yönetir.
public class CalisanService {

    // Servisimizin çalışması için Repository'ye ihtiyacı var.
    private final CalisanRepository calisanRepository;
    private final IzinRepository izinRepository;

    // "Constructor Injection": Spring, CalisanRepository'yi buraya otomatik getirir.
    public CalisanService(CalisanRepository calisanRepository,  IzinRepository izinRepository) {
        this.calisanRepository = calisanRepository;
        this.izinRepository = izinRepository;
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
        mevcutCalisan.setDepartman(guncelBilgiler.getDepartman());
        mevcutCalisan.setUnvan(guncelBilgiler.getUnvan());
        mevcutCalisan.setTelefon(guncelBilgiler.getTelefon());
        mevcutCalisan.setOkul(guncelBilgiler.getOkul());
        mevcutCalisan.setIseGirisTarihi(guncelBilgiler.getIseGirisTarihi());
        mevcutCalisan.setIstenCikisTarihi(guncelBilgiler.getIstenCikisTarihi());
        mevcutCalisan.setMedeniHal(guncelBilgiler.getMedeniHal());
        mevcutCalisan.setEhliyet(guncelBilgiler.getEhliyet());

        // 4. ADIM: Değişiklikleri veritabanına kaydet (ID'si olduğu için yeni kayıt açmaz, var olanı günceller)
        return calisanRepository.save(mevcutCalisan);
    }
    // 4. Kural: Çalışana İzin Ekleme
    public Izin izinEkle(Long calisanId, Izin yeniIzin) {
        // Önce çalışanı bul
        Calisan calisan = calisanRepository.findById(calisanId)
                .orElseThrow(() -> new RuntimeException("Hata: İzin eklenecek çalışan bulunamadı!"));

        // İzni çalışana bağla
        yeniIzin.setCalisan(calisan);

        // Yeni eklenen bir iznin durumu varsayılan olarak onay bekler
        if (yeniIzin.getOnayDurumu() == null) {
            yeniIzin.setOnayDurumu("Onay Bekliyor");
        }

        // İzni veritabanına kaydet
        return izinRepository.save(yeniIzin);
    }
    // Tek bir çalışanı getirme metodu
    public Calisan calisanGetir(Long id) {
        return calisanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hata: Çalışan bulunamadı!"));
    }
}