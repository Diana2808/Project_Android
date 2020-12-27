package com.example.project.clase;

import android.os.Parcel;
import android.os.Parcelable;

public class Moneda implements Parcelable {
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

    protected Moneda(Parcel in) {
        an = in.readInt();
        valoare = in.readString();
        denumire = in.readString();
        caracteristici = in.readParcelable(Caracteristici.class.getClassLoader());
    }

    public static final Creator<Moneda> CREATOR = new Creator<Moneda>() {
        @Override
        public Moneda createFromParcel(Parcel in) {
            return new Moneda(in);
        }

        @Override
        public Moneda[] newArray(int size) {
            return new Moneda[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(an);
        dest.writeString(valoare);
        dest.writeString(denumire);
        dest.writeParcelable(caracteristici, flags);
    }
}
