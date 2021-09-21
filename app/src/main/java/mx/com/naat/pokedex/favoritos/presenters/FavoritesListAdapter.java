package mx.com.naat.pokedex.favoritos.presenters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.favoritos.FavoritesPokemons;
import mx.com.naat.pokedex.model.Pokemon;
import mx.com.naat.pokedex.pokemon.PokemonListAdapter;

public class FavoritesListAdapter extends RecyclerView.Adapter<FavoritesListAdapter.ViewHolder> {

    private List<Pokemon> dataset;
    private final Context context;
    FavoritesPokemons db;

    public FavoritesListAdapter(Context context) {
        //Room
        db = Room.databaseBuilder(context, FavoritesPokemons.class, "pokemon")
                .allowMainThreadQueries()
                .build();

        this.context = context;
        dataset = new ArrayList<>();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView pokemonImage;
        private final TextView pokemonName;
        private final TextView pokemonNumber;
        private final CheckBox favorites;


        public ViewHolder(View itemView) {
            super(itemView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokemonName = (TextView) itemView.findViewById(R.id.pokemon_name);
            pokemonNumber = (TextView) itemView.findViewById(R.id.pokemon_number);
            favorites = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pokemon pokemon = dataset.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonNumber.setText(pokemon.getNumber());

        //Checkbox persistance
        holder.favorites.setOnCheckedChangeListener(null);
        holder.favorites.setChecked(pokemon.isFavorites());

        holder.favorites.setOnCheckedChangeListener((buttonView, isChecked) -> {
            pokemon.setFavorites(isChecked);

            if (!isChecked) {
                db.pokemonDao().delete(pokemon);
            }

        });

        //Get image with glide
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokemonImage);
    }


    @Override
    public int getItemCount() {
        return dataset.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addPokemonList(List<Pokemon> pokemonList) {
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }



}
