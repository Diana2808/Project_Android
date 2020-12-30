package com.example.project.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.project.clase.ListaMonedeTabele;
import com.example.project.claseBD.MonedaBD;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface MonedaDao {

    @Insert
    long insert(MonedaBD moneda);

    @Update
    int update(MonedaBD moneda);

    @Delete
    int delete(MonedaBD moneda);

    @Query("SELECT * FROM monede")
    List<MonedaBD> getToateMonedele();

    @Query("SELECT t.denumire_tara,t.continent,m.valoare,m.denumire_moneda," +
            "m.an,c.diametru,c.grosime,c.material,c.culoare FROM monede m,tari t," +
            "caracteristici c WHERE m.id_caracteristici=c.id AND m.id_tara=t.id")
    List<ListaMonedeTabele> getAllImpreuna();


    @Query("SELECT t.denumire_tara,t.continent,m.valoare,m.denumire_moneda," +
               "m.an,c.diametru,c.grosime,c.material,c.culoare FROM monede m,tari t," +
            "caracteristici c WHERE m.id_caracteristici=c.id AND m.id_tara=t.id AND m.an>2000")
    List<ListaMonedeTabele> getMonedePeste2000();

    @Query("SELECT t.denumire_tara,t.continent,m.valoare,m.denumire_moneda," +
            "m.an,c.diametru,c.grosime,c.material,c.culoare FROM tari t,monede m, caracteristici c WHERE " +
            "m.id_caracteristici=c.id AND m.id_tara=t.id AND continent LIKE 'Europa' AND t.denumire_tara LIKE 'Romania'")
    List<ListaMonedeTabele> getMonedeDupaTara();

}
