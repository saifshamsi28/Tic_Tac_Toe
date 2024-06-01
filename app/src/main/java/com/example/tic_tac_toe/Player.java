package com.example.tic_tac_toe;

public class Player {
    private String name;
    private int match_played,match_won,match_lost,match_drawn;
    private int color; // New field for storing the color

    public Player(String name, int match_played, int match_won,int match_lost, int match_drawn, int color) {
        this.name = name;
        this.match_played = match_played;
        this.match_won = match_won;
        this.match_lost = match_lost;
        this.match_drawn = match_drawn;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getMatch_played() {
        return match_played;
    }
    public int getMatch_won() {
        return match_won;
    }
    public int getMatch_lost() {
        return match_lost;
    }
    public int getMatch_drawn() {
        return match_drawn;
    }

    public int getColor() {
        return color;
    }
}
