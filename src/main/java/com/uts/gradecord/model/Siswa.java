package com.uts.gradecord.model;

public class Siswa {
    private String nama;
    private String nim;
    private double matematika;
    private double ipa;
    private double indonesia;

    public Siswa(String nim, String nama, double matematika, double ipa, double indonesia) {
        this.nim = nim;
        this.nama = nama;
        this.matematika = matematika;
        this.ipa = ipa;
        this.indonesia = indonesia;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public double getMatematika() {
        return matematika;
    }

    public double getIpa() {
        return ipa;
    }

    public double getIndonesia() {
        return indonesia;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setMatematika(double matematika) {
        this.matematika = matematika;
    }

    public void setIpa(double ipa) {
        this.ipa = ipa;
    }

    public void setIndonesia(double indonesia) {
        this.indonesia = indonesia;
    }

    public double getRataRata() {
        return Math.round((matematika + ipa + indonesia) / 3.0 * 100.0) / 100.0;
    }
}
