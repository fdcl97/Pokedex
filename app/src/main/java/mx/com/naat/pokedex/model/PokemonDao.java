package mx.com.naat.pokedex.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    public List<Pokemon> getAll();

    @Query("SELECT * FROM pokemon_table WHERE favorites = 'TRUE'")
    public List<Pokemon> getFavorites();

    @Insert
    void insert(Pokemon pokemon);

    @Query("SELECT name FROM pokemon_table WHERE NUMBER = :id")
    String getbyId(int id);

}
