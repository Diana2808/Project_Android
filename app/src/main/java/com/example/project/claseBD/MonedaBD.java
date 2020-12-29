package com.example.project.claseBD;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.project.clase.Caracteristici;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;


@Entity(tableName = "monede",
        foreignKeys = {
                @ForeignKey(entity = TaraBD.class,
                        parentColumns = "id",
                        childColumns = "id_tara" ,
                        onDelete=CASCADE,
                        onUpdate = CASCADE),
                @ForeignKey(entity = CaracteristiciBD.class,
                        parentColumns = "id",
                        childColumns = "id_caracteristici",
                        onDelete=CASCADE,
                        onUpdate = CASCADE)},
        indices ={
                @Index(name="taraId_index",value = {"id_tara"}),
                @Index(name="caracteristiciId_index",value = {"id_caracteristici"})
        } )

public class MonedaBD implements Serializable {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;

    @ColumnInfo(name="an")
    private int an;

    @ColumnInfo(name="valoare")
    private String valoare;

    @ColumnInfo(name="denumire_moneda")
    private String denumire;

    @ColumnInfo(name = "id_tara")
    private long id_tara;

    @ColumnInfo(name = "id_caracteristici")
    private long id_caracteristici;




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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_tara() {
        return id_tara;
    }

    public void setId_tara(long id_tara) {
        this.id_tara = id_tara;
    }

    public long getId_caracteristici() {
        return id_caracteristici;
    }

    public void setId_caracteristici(long id_caracteristici) {
        this.id_caracteristici = id_caracteristici;
    }

    public MonedaBD(long id, int an, String valoare, String denumire, long id_tara, long id_caracteristici) {
        this.id = id;
        this.an = an;
        this.valoare = valoare;
        this.denumire = denumire;
        this.id_tara = id_tara;
        this.id_caracteristici = id_caracteristici;
    }

    @Ignore
    public MonedaBD(int an, String valoare, String denumire, long taraId, long caracteristiciId) {
        this.an = an;
        this.valoare = valoare;
        this.denumire = denumire;
        this.id_tara = taraId;
        this.id_caracteristici = caracteristiciId;
    }

    @Override
    public String toString() {
        return "MonedaBD{" +
                "idMoneda=" + id +
                ", an=" + an +
                ", valoare='" + valoare + '\'' +
                ", denumire='" + denumire + '\'' +
                ", taraId=" + id_tara +
                ", caracteristiciId=" + id_caracteristici +
                '}';
    }
}