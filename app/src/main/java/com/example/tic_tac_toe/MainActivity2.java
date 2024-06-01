package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private CustomDialog customDialog;
    TextView statusBar, winner;

    int activePlayer = 0;
    int count = 0;
    boolean winOnLastMove = false;
    boolean isGameActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private int winningCellBorderColor;

    private int[] winningLineCells = new int[3];

    public void swapPlayers(View view) {
        String player = MainActivity.PLAYER_1;
        MainActivity.PLAYER_1 = MainActivity.PLAYER_2;
        MainActivity.PLAYER_2 = player;
        restartGame(view);
    }

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        TextView textView = findViewById(R.id.status);
        winner = findViewById(R.id.winner);
        int tappedImage = Integer.parseInt(img.getTag().toString());

        if (!isGameActive) {
            restartGame(view);
        } else {
            if (gameState[tappedImage] == 2) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-500f);

                if (activePlayer == 0) {
                    textView.setText(MainActivity.PLAYER_2 + " :- your turns now ");
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                } else {
                    textView.setText(MainActivity.PLAYER_1 + " :- your turns now ");
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                }
                img.animate().translationYBy(500f).setDuration(100);

                count++;
            }

//            boolean matchDrawn = (count == 9) && !winOnLastMove;
            boolean gameWon = false;

            for (int[] winPosition : winningPositions) {
                if (gameState[winPosition[0]] != 2 && gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]) {
                    gameWon = true;
                    isGameActive = false;
                    winningLineCells = winPosition;
                    if (count == 9) {
                        winOnLastMove = true;
                    }
                    showWinnerPopup(gameState[winPosition[0]] == 0 ? MainActivity.PLAYER_1 : MainActivity.PLAYER_2, false);
                    break;
                }
            }

            if (!gameWon && count==9) {
                isGameActive = false;
                showWinnerPopup("The match is drawn!!", true);
            }
        }
    }

//    private void showWinnerPopup(String winner_title, boolean isDraw) {
//        if (customDialog != null) {
//            customDialog.show(winner_title);
//        }
//        if (isDraw) {
//            // Increment matches drawn for both players
////            incrementMatchDrawn(MainActivity.PLAYER_1);
////            incrementMatchDrawn(MainActivity.PLAYER_2);
////            incrementMatchPlayed(MainActivity.PLAYER_1);
////            incrementMatchPlayed(MainActivity.PLAYER_2);
//            Toast.makeText(this, winner_title, Toast.LENGTH_SHORT).show();
//        } else {
//            for (int cellIndex : winningLineCells) {
//                if (cellIndex >= 0 && cellIndex < 9) {
//                    @SuppressLint("DiscouragedApi") int cellId = getResources().getIdentifier("imageView" + (cellIndex + 1), "id", getPackageName());
//                    ImageView cell = findViewById(cellId);
//                    cell.setColorFilter(winningCellBorderColor);
//                }
//            }
//            // Increment matches won and matches played
////            incrementMatchWon(winner_title);
////            incrementMatchPlayed(MainActivity.PLAYER_1);
////            incrementMatchPlayed(MainActivity.PLAYER_2);
//            Toast.makeText(this, winner_title + " won the match!", Toast.LENGTH_LONG).show();
//        }
////        logCurrentStats();
//    }

    private void showWinnerPopup(String winner, boolean isDraw) {
        if (customDialog != null) {
            customDialog.show(winner);
        }

        // Update stats
        incrementMatchPlayed(MainActivity.PLAYER_1);
        incrementMatchPlayed(MainActivity.PLAYER_2);

        if (isDraw) {
            incrementMatchDrawn(MainActivity.PLAYER_1);
            incrementMatchDrawn(MainActivity.PLAYER_2);
//            Toast.makeText(this, winner_title, Toast.LENGTH_SHORT).show();
        }
        else {
            if (winner.equals(MainActivity.PLAYER_1)) {
                incrementMatchWon(MainActivity.PLAYER_1);
                incrementMatchLost(MainActivity.PLAYER_2);
            } else {
                incrementMatchWon(MainActivity.PLAYER_2);
                incrementMatchLost(MainActivity.PLAYER_1);
            }
            for (int cellIndex : winningLineCells) {
                if (cellIndex >= 0 && cellIndex < 9) {
                    @SuppressLint("DiscouragedApi") int cellId = getResources().getIdentifier("imageView" + (cellIndex + 1), "id", getPackageName());
                    ImageView cell = findViewById(cellId);
                    cell.setColorFilter(winningCellBorderColor);
                }
            }
//            Toast.makeText(this, winner_title + " won the match!", Toast.LENGTH_LONG).show();
        }
        logCurrentStats();
    }


    private void logCurrentStats() {
        Log.d("Log of logCurrentStats p1", MainActivity.PLAYER_1 + " matches played: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_1+"_played", 0));
        Log.d("Log of logCurrentStats p1", MainActivity.PLAYER_1 + " matches won: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_1+"_won", 0));
        Log.d("Log of logCurrentStats p1", MainActivity.PLAYER_1 + " matches lost: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_1+"_lost", 0));
        Log.d("Log of logCurrentStats p1", MainActivity.PLAYER_1 + " matches drawn: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_1+"_drawn", 0));

        Log.d("Log of logCurrentStats p2", MainActivity.PLAYER_2 + " matches played: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_2+"_played", 0));
        Log.d("Log of logCurrentStats p2", MainActivity.PLAYER_2 + " matches won: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_2+"_won", 0));
        Log.d("Log of logCurrentStats p2", MainActivity.PLAYER_2 + " matches lost: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_2+"_lost", 0));
        Log.d("Log of logCurrentStats p2", MainActivity.PLAYER_2 + " matches drawn: " + MainActivity.sharedPreferences.getInt(MainActivity.PLAYER_2+"_drawn", 0));
    }
    private void incrementMatchPlayed(String player) {
//        int current = ;
        MainActivity.sharedPreferences.edit().putInt(player + "_played",
                MainActivity.sharedPreferences.getInt(player + "_played", 0)+1).commit();
        Log.d("match played",MainActivity.sharedPreferences.getInt(player + "_played", 0)+" match played");
    }

    private void incrementMatchWon(String player) {
//        int current = MainActivity.sharedPreferences.getInt(player + "_won", 0);
        MainActivity.sharedPreferences.edit().putInt(player + "_won",
                MainActivity.sharedPreferences.getInt(player + "_won", 0)+1).commit();
        Log.d("match won",MainActivity.sharedPreferences.getInt(player + "_won", 0)+" match played");
    }

    private void incrementMatchLost(String player) {
//        int current = MainActivity.sharedPreferences.getInt(player + "_lost", 0);
        MainActivity.sharedPreferences.edit().putInt(player + "_lost",
                MainActivity.sharedPreferences.getInt(player + "_lost", 0)+1 ).commit();
        Log.d("match lost",MainActivity.sharedPreferences.getInt(player + "_lost", 0)+" match played");
    }

    private void incrementMatchDrawn(String player) {
//        int current = MainActivity.sharedPreferences.getInt(player + "_drawn", 0);
        MainActivity.sharedPreferences.edit().putInt(player + "_drawn",
                MainActivity.sharedPreferences.getInt(player + "_drawn", 0)+1).commit();
        Log.d("match drawn", MainActivity.sharedPreferences.getInt(player + "_drawn", 0)+" match played");
    }

    public void restartGame(View view) {
        count = 0;
        isGameActive = true;
        winOnLastMove = false;
        activePlayer = 0;
        statusBar = findViewById(R.id.status);
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        statusBar.setText(MainActivity.PLAYER_1 + " :- your turns now ");
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        for (int i = 1; i <= 9; i++) {
            int cellId = getResources().getIdentifier("imageView" + i, "id", getPackageName());
            ImageView cell = findViewById(cellId);
            cell.setColorFilter(null);
        }
        winningLineCells = new int[3];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        customDialog = new CustomDialog(this, this);
        winningCellBorderColor = getResources().getColor(R.color.Yellow);
        statusBar = findViewById(R.id.status);
        statusBar.setText(MainActivity.PLAYER_1 + " :- your turns now ");
    }
}
