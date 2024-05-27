package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView playerX,playerO;
    public static String PLAYER_1,PLAYER_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Players Registration");

        Toast.makeText(this, "Welcome to the Saif's Tic-Tac-Toe game", Toast.LENGTH_LONG).show();
        playerX=findViewById(R.id.X);
        playerO=findViewById(R.id.O);
    }

    //function to start the game
    public void StartGame(View view){
        PLAYER_1=NameFormatter.formatName(playerX.getText().toString());
        PLAYER_2=NameFormatter.formatName(playerO.getText().toString());

        if(!PLAYER_1.isEmpty() && !PLAYER_2.isEmpty()){
            Intent intent =new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Please Enter player name first", Toast.LENGTH_SHORT).show();
        }

    }
}