package com.example.tic_tac_toe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    EditText playerX, playerO;
    public static String PLAYER_1, PLAYER_2;
    public static SharedPreferences sharedPreferences_score,sharedPreferences_matchPlayed,
    sharedPreferences_matchDrawn;
    private RecyclerView playersRecyclerView;
    private PlayersAdapter adapter;
    private ArrayList<Player> playersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Players Registration");

        Toast.makeText(this, "Welcome to Saif's Tic-Tac-Toe game", Toast.LENGTH_LONG).show();
        playerX = findViewById(R.id.X);
        playerO = findViewById(R.id.O);
        playersRecyclerView = findViewById(R.id.players_recycler_view);

        sharedPreferences_score = getSharedPreferences("tic_tac_toe", Context.MODE_PRIVATE);
        sharedPreferences_matchPlayed = getSharedPreferences("tic_tac_toe", Context.MODE_PRIVATE);
        sharedPreferences_matchDrawn = getSharedPreferences("tic_tac_toe", Context.MODE_PRIVATE);

            loadPlayers();

        playersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PlayersAdapter(playersList, this::onPlayerClick);
        playersRecyclerView.setAdapter(adapter);
        Drawable dividerDrawable = ContextCompat.getDrawable(this, R.drawable.custom_divider);
// Set the divider with the custom drawable
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(playersRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(dividerDrawable);
// Add the decoration to the RecyclerView
        playersRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void loadPlayers() {
        Set<String> playersSet = sharedPreferences_score.getStringSet("players", new HashSet<>());
        playersList = new ArrayList<>();
        for (String player : playersSet) {
            int color = getRandomColor();
            playersList.add(new Player(player, sharedPreferences_score.getInt(player,0), color)); // Assumed initial score is 0
        }
        Collections.sort(playersList, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                // Compare players based on their scores in descending order
                return Integer.compare(p2.getScore(), p1.getScore());
            }
        });
    }

    private int getRandomColor() {
        Random rnd = new Random();
//        return 0xff000000 | rnd.nextInt(0xffffff)
//                ;
        int[] playerColors = getResources().getIntArray(R.array.player_colors);
        int colorIndex = rnd.nextInt(playerColors.length);
        int playerColor = playerColors[colorIndex];
        return playerColor;
    }

    private void savePlayer(String playerName) {
        Set<String> playersSet = sharedPreferences_score.getStringSet("players", new HashSet<>());
        playersSet.add(playerName);
        sharedPreferences_score.edit().putStringSet("players", playersSet).apply();

        int[] playerColors = getResources().getIntArray(R.array.player_colors);
        int colorIndex = playersSet.size() % playerColors.length;
        int playerColor = playerColors[colorIndex];

        // Create the Player object with the assigned color
        Player newPlayer = new Player(playerName, 0, playerColor);
        playersList.add(newPlayer);
        adapter.notifyDataSetChanged();
    }

    public void StartGame(View view) {
        PLAYER_1 = NameFormatter.formatName(playerX.getText().toString().trim());
        PLAYER_2 = NameFormatter.formatName(playerO.getText().toString().trim());

        if (!PLAYER_1.isEmpty() && !PLAYER_2.isEmpty()) {
            savePlayer(PLAYER_1);
            savePlayer(PLAYER_2);
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please enter player names first", Toast.LENGTH_SHORT).show();
        }
    }

    private void onPlayerClick(Player player) {
        if (playerX.getText().toString().trim().isEmpty()) {
            playerX.setText(player.getName());
        } else if (playerO.getText().toString().trim().isEmpty()) {
            if(!player.getName().equals(playerX.getText().toString()))
                playerO.setText(player.getName());
            else{
                Toast.makeText(this, "This player \""+player.getName()+"\" is already selected Please choose different player", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Both players are already selected", Toast.LENGTH_SHORT).show();
        }
    }
}
