package com.example.project.clase;

public class Moneda {
    private int an;
    private String valoare;
    private String denumire;
    private Caracteristici caracteristici;


    public Moneda(int an, String valoare, String denumire, Caracteristici caracteristici) {
        this.an = an;
        this.valoare = valoare;
        this.denumire = denumire;
        this.caracteristici = caracteristici;
    }

    public int getAn() {

        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getValoare() {
        return valoare;
    }

    public void setValoare(String valoare) {
        this.valoare = valoare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Caracteristici getCaracteristici() {
        return caracteristici;
    }

    public void setCaracteristici(Caracteristici caracteristici) {
        this.caracteristici = caracteristici;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "an=" + an +
                ", valoare='" + valoare + '\'' +
                ", denumire='" + denumire + '\'' +
                ", caracteristici=" + caracteristici +
                '}';
    }
}
