package com.example.kimberlycaedo.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kimberly Cañedo on 06/07/2017.
 */

public class PokemonListAdapter extends BaseAdapter {

    private Context context;
    private List<Pokemon> pokemonList;
    private LayoutInflater mInflater;

    public PokemonListAdapter(Context context, List<Pokemon> pokemonList) {
        this.context = context;
        this.pokemonList = pokemonList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return pokemonList.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemonList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.pokemon_item, null);

        TextView textView = (TextView) convertView.findViewById(R.id.textView_pokemonName);
        textView.setText(pokemonList.get(position).getPokemonName());

        ImageView imgView = (ImageView) convertView.findViewById(R.id.imageView_pokemon);
        ImageRequester.requestImage(context, imgView, pokemonList.get(position).getPokemonImageURL());

        return convertView;
    }


}
