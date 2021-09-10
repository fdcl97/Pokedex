package mx.com.naat.pokedex.pokemon;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.model.Pokemon;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;

    public PokemonListAdapter() {
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Pokemon pokemon = dataset.get(position);
        holder.pokemonName.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addPokemonList(ArrayList<Pokemon> pokemonList) {
        dataset.addAll(pokemonList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonName;

        public ViewHolder(View itemView) {
            super(itemView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokemonName = (TextView) itemView.findViewById(R.id.pokemon_name);
        }

    }


}
