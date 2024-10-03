package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.entity.HasilProses;
import com.example.demo.repository.HasilProsesRepo;

@Service
public class ProsesLamaService {
    @Autowired
    private HasilProsesRepo hasilProsesRepo;

    @Async
    public void jalankanProses(Long id) throws InterruptedException {
        
        Thread.sleep(120000); 

        HasilProses hasil = hasilProsesRepo.findById(id).orElseThrow();
        hasil.setHasil("proses selesai");
        hasil.setSelesai(true);
        hasilProsesRepo.save(hasil);
    }
}
