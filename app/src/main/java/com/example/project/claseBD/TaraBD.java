package com.example.project.claseBD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tari")
public class TaraBD {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id_tara")
    private long id_tara;


    @ColumnInfo(name="continent")
    private String continent;

    @ColumnInfo(name="denumire_tara")
    private  String denumireTara;

    public TaraBD(long id_tara, String continent, String denumireTara) {
        this.id_tara = id_tara;
        this.continent = continent;
        this.denumireTara = denumireTara;
    }

    @Ignore
    public TaraBD(String continent, String denumireTara) {
        this.continent = continent;
        this.denumireTara = denumireTara;
    }

    public long getId_tara() {
        return id_tara;
    }

    public void setId_tara(long id_tara) {
        this.id_tara = id_tara;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getDenumireTara() {
        return denumireTara;
    }

    public void setDenumireTara(String denumireTara) {
        this.denumireTara = denumireTara;
    }

    @Override
    public String toString() {
        return "TaraBD{" +
                "idTara=" + id_tara +
                ", continent='" + continent + '\'' +
                ", denumireTara='" + denumireTara + '\'' +
                '}';
    }



}
