package com.example.project.claseBD;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.project.clase.Moneda;

@Entity(tableName = "caracteristici")
public class CaracteristiciBD {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "grosime")
    private String grosime;

    @ColumnInfo(name = "diametru")
    private String diametru;

    @ColumnInfo(name = "culoare")
    private String culoare;

    @ColumnInfo(name = "material")
    private String material;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "CaracteristiciBD{" +
                "id_caracteristici=" + id +
                ", grosime='" + grosime + '\'' +
                ", diametru='" + diametru + '\'' +
                ", culoare='" + culoare + '\'' +
                ", material='" + material + '\'' +
                '}';
    }


    public CaracteristiciBD(long id, String grosime, String diametru, String culoare, String material) {
        this.id = id;
        this.grosime = grosime;
        this.diametru = diametru;
        this.culoare = culoare;
        this.material = material;
    }


    @Ignore
    public CaracteristiciBD(String grosime, String diametru, String culoare, String material) {
        this.grosime = grosime;
        this.diametru = diametru;
        this.culoare = culoare;
        this.material = material;
    }
}
