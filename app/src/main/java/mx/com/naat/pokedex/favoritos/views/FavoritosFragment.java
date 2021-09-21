package mx.com.naat.pokedex.favoritos.views;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.favoritos.FavoritesPokemons;
import mx.com.naat.pokedex.favoritos.PokemonApp;
import mx.com.naat.pokedex.favoritos.presenters.FavoritesListAdapter;
import mx.com.naat.pokedex.model.Api;
import mx.com.naat.pokedex.model.Pokemon;
import mx.com.naat.pokedex.model.PokemonResponse;
import mx.com.naat.pokedex.pokemon.PokemonListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoritosFragment extends Fragment {

    private static final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    ArrayList<Pokemon> pokemonList;
    private FavoritesListAdapter adapter;
    private Retrofit retrofit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        //generate the recycler view and show the pokemons
        recyclerView = view.findViewById(R.id.recycler_view_favorites);
        adapter = new FavoritesListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        pokemonList = new ArrayList<>();

        //Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getPokemon();

        return view;

    }

    private void getPokemon() {

        //Room
        FavoritesPokemons db = Room.databaseBuilder(getContext(), FavoritesPokemons.class, "pokemon")
                .allowMainThreadQueries()
                .build();

        Api service = retrofit.create(Api.class);
        Call<PokemonResponse> pokemonResponseCall =   service.getPokemonList();

        pokemonResponseCall.enqueue(new Callback<PokemonResponse>() {

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()){

                    adapter.addPokemonList(db.pokemonDao().getAll());//Set the favorits from the DB
                    adapter.notifyDataSetChanged();

                } else{
                    Log.i(TAG,"onResponse: "+response.errorBody());

                }

            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Nav controller
        final NavController navController = Navigation.findNavController(view);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tabLayout.selectTab(tab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        navController.navigate(R.id.pokemonFragment);
                        break;

                    case 2:
                        navController.navigate(R.id.bayasFragment);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}