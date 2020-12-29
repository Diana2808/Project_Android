package com.example.project.claseBD;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tari")
public class TaraBD {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private long id;


    @ColumnInfo(name="continent")
    private String continent;

    @ColumnInfo(name="denumire_tara")
    private  String denumireTara;

    public TaraBD(long id, String continent, String denumireTara) {
        this.id = id;
        this.continent = continent;
        this.denumireTara = denumireTara;
    }

    @Ignore
    public TaraBD(String continent, String denumireTara) {
        this.continent = continent;
        this.denumireTara = denumireTara;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
                "idTara=" + id+
                ", continent='" + continent + '\'' +
                ", denumireTara='" + denumireTara + '\'' +
                '}';
    }



}
