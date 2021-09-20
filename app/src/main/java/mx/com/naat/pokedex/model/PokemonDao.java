package mx.com.naat.pokedex.model;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public
interface PokemonDao {

    @Query("SELECT * FROM pokemon_table")
    public List<Pokemon> getAll();

    @Query("SELECT * FROM pokemon_table WHERE favorites = 'TRUE'")
    public List<Pokemon> getFavorites();

}
