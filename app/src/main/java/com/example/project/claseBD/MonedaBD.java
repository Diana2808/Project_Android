package com.example.project.claseBD;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.project.clase.Caracteristici;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "monede",
        foreignKeys = {
        @ForeignKey(entity = TaraBD.class,
                parentColumns = "id_tara",
                childColumns = "taraId" ,
                onDelete=CASCADE,
        onUpdate = CASCADE),
        @ForeignKey(entity = CaracteristiciBD.class,
        parentColumns = "id_caracteristici",
        childColumns = "caracteristiciId",
        onDelete=CASCADE,
        onUpdate = CASCADE)})

public class MonedaBD {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_moneda")
    private long idMoneda;

    @ColumnInfo(name="an")
    private int an;

    @ColumnInfo(name="valoare")
    private String valoare;

    @ColumnInfo(name="denumire_moneda")
    private String denumire;

    private long taraId;

    private long caracteristiciId;



    public long getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(long idMoneda) {
        this.idMoneda = idMoneda;
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

    public long getTaraId() {
        return taraId;
    }

    public void setTaraId(long taraId) {
        this.taraId = taraId;
    }

    public long getCaracteristiciId() {
        return caracteristiciId;
    }

    public void setCaracteristiciId(long caracteristiciId) {
        this.caracteristiciId = caracteristiciId;
    }

    public MonedaBD(long idMoneda, int an, String valoare, String denumire, long taraId, long caracteristiciId) {
        this.idMoneda = idMoneda;
        this.an = an;
        this.valoare = valoare;
        this.denumire = denumire;
        this.taraId = taraId;
        this.caracteristiciId = caracteristiciId;
    }

    @Override
    public String toString() {
        return "MonedaBD{" +
                "idMoneda=" + idMoneda +
                ", an=" + an +
                ", valoare='" + valoare + '\'' +
                ", denumire='" + denumire + '\'' +
                ", taraId=" + taraId +
                ", caracteristiciId=" + caracteristiciId +
                '}';
    }
}