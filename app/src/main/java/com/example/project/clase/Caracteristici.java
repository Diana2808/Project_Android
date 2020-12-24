package com.example.project.clase;

import android.os.Parcel;
import android.os.Parcelable;

public class Caracteristici implements Parcelable {
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

    protected Caracteristici(Parcel in) {
        grosime = in.readString();
        diametru = in.readString();
        culoare = in.readString();
        material = in.readString();
    }

    public static final Creator<Caracteristici> CREATOR = new Creator<Caracteristici>() {
        @Override
        public Caracteristici createFromParcel(Parcel in) {
            return new Caracteristici(in);
        }

        @Override
        public Caracteristici[] newArray(int size) {
            return new Caracteristici[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(grosime);
        dest.writeString(diametru);
        dest.writeString(culoare);
        dest.writeString(material);
    }
}
