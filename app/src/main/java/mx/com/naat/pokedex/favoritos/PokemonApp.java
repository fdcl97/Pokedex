package mx.com.naat.pokedex.favoritos;

import android.app.Application;

import androidx.room.Room;

public class PokemonApp  extends Application {
    FavoritesPokemons room = Room.databaseBuilder(this, FavoritesPokemons.class, "pokemon")
            .build();

}

