package com.example.project.clase;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Tara implements Parcelable {
    private String denumire;
    private String continent;
    private List<Moneda> monede;


    public Tara(String denumire_tara, String continent, List<Moneda> monede) {
        this.denumire = denumire_tara;
        this.continent = continent;
        this.monede = monede;
    }

    public String getDenumire_tara() {
        return denumire;
    }

    public void setDenumire_tara(String denumire_tara) {
        this.denumire = denumire_tara;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public List<Moneda> getMonede() {
        return monede;
    }

    public void setMonede(List<Moneda> monede) {
        this.monede = monede;
    }



    @Override
    public String toString() {
        return "Tara{" +
                "denumire_tara='" + denumire + '\'' +
                ", continent='" + continent + '\'' +
                ", monede=" + monede +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(denumire);
        dest.writeString(continent);
        for(int i=0;i<monede.size();i++){
            dest.writeString(
                    new Moneda(monede.get(i).getAn(),
                            monede.get(i).getValoare(),
                            monede.get(i).getDenumire(),
                    new Caracteristici(monede.get(i).getCaracteristici().getGrosime(),
                            monede.get(i).getCaracteristici().getDiametru(),
                            monede.get(i).getCaracteristici().getCuloare(),
                            monede.get(i).getCaracteristici().getMaterial())).toString());

        }


    }
}

