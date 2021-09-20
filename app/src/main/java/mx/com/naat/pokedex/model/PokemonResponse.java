package mx.com.naat.pokedex.model;

import java.util.ArrayList;



public class PokemonResponse {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getPokemons() {
        return results;
    }

    public void setPokemons(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
