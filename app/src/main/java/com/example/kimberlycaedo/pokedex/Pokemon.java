package com.example.kimberlycaedo.pokedex;

/**
 * Created by Kimberly Cañedo on 06/07/2017.
 */

public class Pokemon {

    private String pokemonName;
    private String pokemonImageURL;

    public Pokemon() {

    }

    public String getPokemonImageURL() {
        return pokemonImageURL;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonImageURL(String pokemonImageURL) {
        this.pokemonImageURL = pokemonImageURL;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

}
