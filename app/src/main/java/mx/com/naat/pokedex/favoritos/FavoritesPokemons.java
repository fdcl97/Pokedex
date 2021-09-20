package mx.com.naat.pokedex.favoritos;



import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mx.com.naat.pokedex.model.Pokemon;
import mx.com.naat.pokedex.model.PokemonDao;

@Database(
        entities = {Pokemon.class},
        version = 1
)
abstract class FavoritesPokemons extends RoomDatabase {

    public abstract PokemonDao pokemonDao();

    private static volatile FavoritesPokemons INSTACE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    static FavoritesPokemons getDatabase(final Context context) {
        if (INSTACE == null) {
            synchronized (FavoritesPokemons.class) {
                if (INSTACE == null) {
                    INSTACE = Room.databaseBuilder(context.getApplicationContext(),
                            FavoritesPokemons.class, "word_database")
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
                dao.getAll();

            });
        }
    };

}
