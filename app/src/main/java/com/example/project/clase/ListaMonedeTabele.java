package com.example.project.clase;

public class ListaMonedeTabele {
    String denumire_tara;
    String continent;
    String dneumire_moneda;
    String valoare;
    int an;
    String grosime;
    String diametru;
    String culoare;
    String material;


    public String getDenumire_tara() {
        return denumire_tara;
    }

    public void setDenumire_tara(String denumire_tara) {
        this.denumire_tara = denumire_tara;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getDneumire_moneda() {
        return dneumire_moneda;
    }

    public void setDneumire_moneda(String dneumire_moneda) {
        this.dneumire_moneda = dneumire_moneda;
    }

    public String getValoare() {
        return valoare;
    }

    public void setValoare(String valoare) {
        this.valoare = valoare;
    }

    public int getAn() {
        return an;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public String getGrosime() {
        return grosime;
    }

    public void setGrosime(String grosime) {
        this.grosime = grosime;
    }

    public String getDiametru() {
        return diametru;
    }

    public void setDiametru(String diametru) {
        this.diametru = diametru;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ListaMonedeTabele(String denumire_tara, String continent,
                             String dneumire_moneda, String valoare, int an, String grosime,
                             String diametru, String culoare, String material) {
        this.denumire_tara = denumire_tara;
        this.continent = continent;
        this.dneumire_moneda = dneumire_moneda;
        this.valoare = valoare;
        this.an = an;
        this.grosime = grosime;
        this.diametru = diametru;
        this.culoare = culoare;
        this.material = material;
    }

    @Override
    public String toString() {
        return "ListaMonedeTabele{" +
                "denumire_tara='" + denumire_tara + '\'' +
                ", continent='" + continent + '\'' +
                ", dneumire_moneda='" + dneumire_moneda + '\'' +
                ", valoare='" + valoare + '\'' +
                ", an=" + an +
                ", grosime='" + grosime + '\'' +
                ", diametru='" + diametru + '\'' +
                ", culoare='" + culoare + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
