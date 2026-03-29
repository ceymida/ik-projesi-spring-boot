package com.example.demo;

import com.example.demo.entity.Calisan;
import com.example.demo.repository.CalisanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Bean
    public CommandLineRunner ilkCalisanlariEkle(CalisanRepository repository) {
        return args -> {
            Calisan calisan1 = new Calisan("Ahmet", "Yilmaz",45000.0);
            repository.save(calisan1);

            Calisan calisan2 = new Calisan("Asyse", " Demir", 52.500);
            repository.save(calisan2);

            System.out.println("--- ÇALIŞANLAR VERİTABANINA BAŞARIYLA EKLENDİ! ---");

        };
    }
}
