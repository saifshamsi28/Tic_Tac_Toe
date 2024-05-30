package com.example.tic_tac_toe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private CustomDialog customDialog; // Use CustomDialog for the winner popup
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

            boolean matchDrawn = (count == 9) && !winOnLastMove;
            boolean gameWon = false;

            for (int[] winPosition : winningPositions) {
                if (gameState[winPosition[0]] != 2 && gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]) {
                    gameWon = true;
                    isGameActive = false;
                    winningLineCells = winPosition;
                    if (count == 9) {
                        winOnLastMove = true;
                    }
                    showWinnerPopup(gameState[winPosition[0]] == 0 ? MainActivity.PLAYER_1 : MainActivity.PLAYER_2);
                    break;
                }
            }

            if (matchDrawn && !gameWon) {
                isGameActive = false;
                showWinnerPopup("The match is drawn!!");
            }
        }
    }

    private void showWinnerPopup(String winner) {
        if (customDialog != null) {
            customDialog.show(winner);
        }
        if (count == 9 && !winOnLastMove) {
            customDialog.dismiss();
            String message = "The match is drawn!!";
            customDialog.show(message);
        } else {
            customDialog.dismiss();
            customDialog.show(winner);
            for (int cellIndex : winningLineCells) {
                if (cellIndex >= 0 && cellIndex < 9) {
                    @SuppressLint("DiscouragedApi") int cellId = getResources().getIdentifier("imageView" + (cellIndex + 1), "id", getPackageName());
                    ImageView cell = findViewById(cellId);
                    cell.setColorFilter(winningCellBorderColor);
                }
            }
        }
        if(!winner.contains("match")) {
            int currentScore = MainActivity.sharedPreferences_score.getInt(winner, 0);
            MainActivity.sharedPreferences_score.edit().putInt(winner, currentScore + 1).apply();
        }
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
        customDialog = new CustomDialog(this,this);
        winningCellBorderColor = getResources().getColor(R.color.Yellow);
        statusBar = findViewById(R.id.status);
        statusBar.setText(MainActivity.PLAYER_1 + " :- your turns now ");
    }
}
