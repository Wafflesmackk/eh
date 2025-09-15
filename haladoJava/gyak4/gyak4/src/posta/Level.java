package posta;

import java.util.ArrayList;

public class Level {
    private int iranyitoszam;

    private String celAllomas;
    private WeekDay feladasiNap;

    ArrayList<String> allomasok;

    public Level(int iranyitoszam, WeekDay feladasiNap){
        this.iranyitoszam = iranyitoszam;
        this.feladasiNap = feladasiNap;
        this.allomasok = new ArrayList<String>();
    }

    public Level(String celAllomas, WeekDay feladasiNap,int iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
        this.celAllomas = celAllomas;
        this.feladasiNap = feladasiNap;
        this.allomasok = new ArrayList<String>();
    }

    public Level(City city, WeekDay feladasiNap){
        this.iranyitoszam = city.getZipCode();
        this.feladasiNap = feladasiNap;
        this.allomasok = new ArrayList<String>();
    }

    public int getIranyitoszam() {
        return iranyitoszam;
    }

    public void setIranyitoszam(int iranyitoszam) {
        this.iranyitoszam = iranyitoszam;
    }

    public String getCelAllomas() {
        return celAllomas;
    }

    public void setCelAllomas(String celAllomas) {
        this.celAllomas = celAllomas;
    }

    public WeekDay getFeladasiNap() {
        return feladasiNap;
    }

    public ArrayList<String> getAllomasok() {
        return allomasok;
    }

    public void utazas(Posta posta){
        feladasiNap = feladasiNap.nextDay();
        allomasok.add(posta.getCim());
    }



}
