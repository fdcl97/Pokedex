package mx.com.naat.pokedex.pokemon;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.annotation.compiler.GlideAnnotationProcessor;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.favoritos.FavoritesPokemons;
import mx.com.naat.pokedex.model.Pokemon;
import mx.com.naat.pokedex.model.PokemonDao;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;

    private final ArrayList<Pokemon> dataset;
    private final Context context;

    public PokemonListAdapter(Context context) {
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        view.setOnClickListener(this);//click method
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {

        //boolean cheked = false;
        Pokemon pokemon = dataset.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonNumber.setText(pokemon.getNumber());

        //Checkbox
        holder.favorites.setOnCheckedChangeListener(null);
        holder.favorites.setChecked(pokemon.isFavorites());
        holder.favorites.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                pokemon.setFavorites(isChecked);
            }
        });


        /*if (holder.favorites.isChecked()) {
            cheked = true;

        }*/
        //holder.favorites.setChecked(pokemon.isFavorites(cheked));
        /*FavoritesPokemons db = Room.databaseBuilder(context, FavoritesPokemons.class, "pokemon")
                .allowMainThreadQueries()
                .build();
        db.pokemonDao().insert(pokemon);*/

        //Get image with glide
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.pokemonImage);
        //notifyDataSetChanged();

    }

    @Override
    public long getItemId(int position) {
        return position;
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

    //Click methods

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }



}
