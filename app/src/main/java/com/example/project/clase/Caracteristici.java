package com.example.project.clase;

public class Caracteristici {
    private String grosime;
    private String diametru;
    private String culoare;
    private String material;

    public Caracteristici(String grosime, String diametru, String culoare, String material) {
        this.grosime = grosime;
        this.diametru = diametru;
        this.culoare = culoare;
        this.material = material;
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




    @Override
    public String toString() {
        return "Caracteristici{" +
                "grosime='" + grosime + '\'' +
                ", diametru='" + diametru + '\'' +
                ", culoare='" + culoare + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
