package mx.com.naat.pokedex.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    public List<Pokemon> getAll();

    /*@Query("SELECT * FROM pokemon_table WHERE favorites = 'TRUE'")
    public List<Pokemon> getFavorites();*/

    @Insert
    void insert(Pokemon pokemon);

    @Query("SELECT name FROM pokemon_table WHERE number = :id")
    String getbyId(int id);

    @Query("DELETE FROM pokemon_table WHERE number = :id")
    public void deleteById(int id);

    @Delete
    void delete(Pokemon pokemon);

    @Query("DELETE FROM pokemon_table")
    public void deleteAll();



}
