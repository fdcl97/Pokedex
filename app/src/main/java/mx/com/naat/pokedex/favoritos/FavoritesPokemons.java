package mx.com.naat.pokedex.favoritos;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mx.com.naat.pokedex.model.Pokemon;
import mx.com.naat.pokedex.model.PokemonDao;

@Database(
        entities = {Pokemon.class},
        version = 1
)
public abstract class FavoritesPokemons extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

    private static volatile FavoritesPokemons INSTACE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static FavoritesPokemons getDatabase(final Context context) {
        if (INSTACE == null) {
            synchronized (FavoritesPokemons.class) {
                if (INSTACE == null) {
                    INSTACE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoritesPokemons.class, "pokemon_database.db")
                            .addCallback(sRoomDatabaseCallBack)
                            .build();
                }
            }
        }
        return INSTACE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallBack = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //restart the db
            databaseWriteExecutor.execute(() -> {
                PokemonDao dao = INSTACE.pokemonDao();
                Pokemon pokemon = new Pokemon();
                dao.insert(pokemon);
                dao.getAll();
                List<Pokemon> pokemons = dao.getAll();
                Log.i("POKEMON LIST", pokemons.toString());



            });
        }
    };

}
