package com.example.tic_tac_toe;

public class Player {
    private String name;
    private int score;
    private int color; // New field for storing the color

    public Player(String name, int score, int color) {
        this.name = name;
        this.score = score;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getColor() {
        return color;
    }
}
