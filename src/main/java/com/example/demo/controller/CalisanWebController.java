package com.example.demo.controller;

import com.example.demo.service.CalisanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // DİKKAT: @RestController DEĞİL! Çünkü bu sınıf JSON değil, web sayfası döndürecek.
@RequestMapping("/calisanlar") // Tarayıcıdan girilecek adres
public class CalisanWebController {

    private final CalisanService calisanService;

    public CalisanWebController(CalisanService calisanService) {
        this.calisanService = calisanService;
    }

    @GetMapping
    public String calisanSayfasiniGoster(Model model) {
        // 1. Service katmanından tüm çalışan listesini alıyoruz
        var calisanListesi = calisanService.tumCalisanlariGetir();

        // 2. Bu listeyi HTML sayfasının içine "calisanlar" adıyla paketliyoruz (Kargo yapıyoruz)
        model.addAttribute("calisanlar", calisanListesi);

        // 3. Ekrana basılacak olan HTML dosyasının adını (uzantısız) söylüyoruz.
        return "calisan-page.html";
    }
}