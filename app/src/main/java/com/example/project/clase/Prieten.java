package com.example.project.clase;

public class Prieten {

    private String id;
    private String nume;
    private String prenume;
    private int numarMonede;

    public Prieten(String id,String nume, String prenume, int numarMonede) {
        this.id=id;
        this.nume = nume;
        this.prenume = prenume;
        this.numarMonede = numarMonede;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getNumarMonede() {
        return numarMonede;
    }

    public void setNumarMonede(int numarMonede) {
        this.numarMonede = numarMonede;
    }

    @Override
    public String toString() {
        return "Prieten{" +
                "nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", numarMonede=" + numarMonede +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    //pentru data.getValue(Prieten.class) -> firebase
    public Prieten(){

    }
}
