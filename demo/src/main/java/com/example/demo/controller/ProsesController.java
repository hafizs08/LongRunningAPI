package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ProsesLamaService;
import com.example.demo.entity.HasilProses;
import com.example.demo.repository.HasilProsesRepo;

@RestController
public class ProsesController {

    @Autowired
    private ProsesLamaService prosesLamaService;

    @Autowired
    private HasilProsesRepo hasilProsesRepo;

    @PostMapping("/postProses")
    public ResponseEntity<String> mulaiProses() {
        HasilProses hasil = new HasilProses();
        hasil.setHasil("sedang diproses");
        hasil.setSelesai(false);
        hasil = hasilProsesRepo.save(hasil);

        try {
            prosesLamaService.jalankanProses(hasil.getId());
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Gagal memulai proses");
        }

        return ResponseEntity.ok("Cek status dengan ID: " + hasil.getId()+" http://localhost:8080/proses/id");
    }

    @GetMapping("/proses/{id}")
    public ResponseEntity<HasilProses> cekStatusProses(@PathVariable Long id) {
        HasilProses hasil = hasilProsesRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("proses tidak ada"));
        return ResponseEntity.ok(hasil);
    }
    @GetMapping("/proses")
    public ResponseEntity<List<HasilProses>> getAllProses() {
        List<HasilProses> hasilProsesList = hasilProsesRepo.findAll();
        return ResponseEntity.ok(hasilProsesList);
    }
}

