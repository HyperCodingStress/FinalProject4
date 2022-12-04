package com.example.finalproject4;

public class DataModel {
    public String nama,type,rating,estimasi;
    public Integer harga,rate,hours;

    public Integer getRate() {
        return rate;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getRating() {return rating;}

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
}
