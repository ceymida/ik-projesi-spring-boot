package com.example.demo;

import com.example.demo.entity.Calisan;
import com.example.demo.repository.CalisanRepository;
import com.example.demo.service.CalisanService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//    @Bean
//    public CommandLineRunner ilkCalisanlariEkle(CalisanRepository repository) {
//        return args -> {
//            Calisan calisan1 = new Calisan("Ahmet", "Yilmaz",45000.0);
//            repository.save(calisan1);
//
//            Calisan calisan2 = new Calisan("Asyse", " Demir", 52.500);
//            repository.save(calisan2);
//
//            System.out.println("--- ÇALIŞANLAR VERİTABANINA BAŞARIYLA EKLENDİ! ---");
//
//        };
//    }


    @Bean
    public CommandLineRunner servisTesti(CalisanService calisanService) {
        return args -> {
            System.out.println("--- SERVİS KATMANI TESTİ BAŞLIYOR ---");

//            try {
//                // TEST 1: Geçerli bir çalışan ekle
//                Calisan c1 = new Calisan("Mehmet", "Öz", 25000.0);
//                calisanService.calisanKaydet(c1);
//                System.out.println("Başarılı: Mehmet eklendi.");
//
//                // TEST 2: Hatalı (Düşük Maaşlı) bir çalışan ekle
//                Calisan c2 = new Calisan("Hatalı", "Veri", 5000.0);
//                calisanService.calisanKaydet(c2); // Burada program durup hataya düşecek
//
//            } catch (Exception e) {
//                System.out.println("Yakaladığımız Hata: " + e.getMessage());
//            }

            // TEST 3: Listeleme
            System.out.println("\nGüncel Çalışan Listesi:");
            calisanService.tumCalisanlariGetir().forEach(c ->
                    System.out.println(c.getAd() + " - Maaş: " + c.getMaas()));
        };
    }
}
