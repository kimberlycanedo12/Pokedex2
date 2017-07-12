package com.example.kimberlycaedo.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String JsonURLPokemon = "http://pokeapi.co/api/v2/pokemon/";

    Button mpokemon, mitems, mmoves, mlocation;



    private GridView mGridViewPokemons;
    private PokemonListAdapter pokemonListAdapter;
    private List<Pokemon> pokemonList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridViewPokemons = (GridView) findViewById(R.id.gridView_pokemons);
        pokemonList = new ArrayList<Pokemon>();
        pokemonListAdapter = new PokemonListAdapter(MainActivity.this, pokemonList);
        mGridViewPokemons.setAdapter(pokemonListAdapter);

        mpokemon = (Button) findViewById(R.id.btnPokemon);
        mitems = (Button) findViewById(R.id.btnItems);
        mlocation = (Button) findViewById(R.id.btnLocation);
        mmoves = (Button) findViewById(R.id.btnMove);

        sendRequest();

        mpokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mmoves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Move.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Items.class);
                MainActivity.this.startActivity(intent);
            }
        });

        mlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Location.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void sendRequest() {
        RequestQueueSingleton.getInstance(this).add(obreq);
    }

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLPokemon, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
                            Pokemon poke = new Pokemon();
                            poke.setPokemonName(tempObj.getString("name"));
                            poke.setPokemonImageURL(tempObj.getString("url"));
                            pokemonList.add(poke);
                        }

                        pokemonListAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }
    );

}
