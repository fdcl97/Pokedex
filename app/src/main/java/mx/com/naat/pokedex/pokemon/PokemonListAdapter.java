package mx.com.naat.pokedex.pokemon;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.annotation.GlideType;
import com.bumptech.glide.annotation.compiler.GlideAnnotationProcessor;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import mx.com.naat.pokedex.R;
import mx.com.naat.pokedex.model.Pokemon;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;

    private ArrayList<Pokemon> dataset;
    private Context context;

    public PokemonListAdapter(Context context) {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        view.setOnClickListener(this);//click method
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Pokemon pokemon = dataset.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonNumber.setText(pokemon.getNumber());


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

    public void addPokemonList(ArrayList<Pokemon> pokemonList) {
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


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView pokemonImage;
        private TextView pokemonName, pokemonNumber;

        public ViewHolder(View itemView) {
            super(itemView);
            pokemonImage = (ImageView) itemView.findViewById(R.id.pokemon_image);
            pokemonName = (TextView) itemView.findViewById(R.id.pokemon_name);
            pokemonNumber = (TextView) itemView.findViewById(R.id.pokemon_number);
        }

    }


}
