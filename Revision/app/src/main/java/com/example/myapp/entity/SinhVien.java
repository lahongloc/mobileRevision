package com.example.myapp.entity;

public class SinhVien {
    private int id;
    private String name;

    public SinhVien() {};

    public SinhVien(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
