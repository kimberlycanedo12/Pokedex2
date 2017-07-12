package com.example.kimberlycaedo.pokedex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Move extends AppCompatActivity {

    private ListView mGridViewMove;
    String JsonURLMove = "http://pokeapi.co/api/v2/move/";
    private ArrayAdapter<String> listAdapter;

    Button mpokemon, mitems, mmoves, mlocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);

        mpokemon = (Button) findViewById(R.id.btnPokemon);
        mitems = (Button) findViewById(R.id.btnItems);
        mlocation = (Button) findViewById(R.id.btnLocation);
        mmoves = (Button) findViewById(R.id.btnMove);

        mGridViewMove = (ListView) findViewById(R.id.gridView_move);
        ArrayList<String> moveList = new ArrayList<>();
        listAdapter = new ArrayAdapter<String>(this, R.layout.item, moveList);
        sendRequest();

        mpokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Move.this, MainActivity.class);
                Move.this.startActivity(intent);
            }
        });

        mmoves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Move.this, Move.class);
                Move.this.startActivity(intent);
            }
        });

        mitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Move.this, Items.class);
                Move.this.startActivity(intent);
            }
        });

        mlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Move.this, Location.class);
                Move.this.startActivity(intent);
            }
        });
    }

    private void sendRequest() {
        RequestQueueSingleton.getInstance(this).add(obreq);
        Log.d("Inside sendRequest", "true");
    }

    JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURLMove, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Log.d("Inside on response", "true");
                        JSONArray obj = response.getJSONArray("results");

                        for (int init = 0; init < obj.length(); init++) {
                            JSONObject tempObj = obj.getJSONObject(init);
//                            Move mMove = new Move();
//                            mMove.setmName(tempObj.getString("name"));
                            listAdapter.add(tempObj.getString("name"));
                            Toast.makeText(Move.this, tempObj.getString("name"), Toast.LENGTH_SHORT).show();
                        }

                        mGridViewMove.setAdapter(listAdapter);
                        listAdapter.notifyDataSetChanged();
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
