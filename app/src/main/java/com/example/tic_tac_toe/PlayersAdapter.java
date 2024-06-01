package com.example.tic_tac_toe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
//
//public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MyViewHolder> {
//    private final List<Player> players;
//    private final OnPlayerClickListener onPlayerClickListener;
//
//    public PlayersAdapter(List<Player> players, OnPlayerClickListener onPlayerClickListener) {
//        this.players = players;
//        this.onPlayerClickListener = onPlayerClickListener;
//    }
//
//    public interface OnPlayerClickListener {
//        void onPlayerClick(Player player);
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        private final TextView playerName;
//        private final TextView playerScore;
//        private final TextView matchPlayed;
//        private final TextView matchDrawn;
//        private final TextView matchLost;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            playerName = itemView.findViewById(R.id.player_name);
//            playerScore = itemView.findViewById(R.id.player_score);
//            matchPlayed = itemView.findViewById(R.id.match_played);
//            matchDrawn = itemView.findViewById(R.id.match_drawn);
//            matchLost=itemView.findViewById(R.id.match_lost);
//        }
//
//        public void bind(Player player, OnPlayerClickListener onPlayerClickListener) {
//            playerName.setText(player.getName());
//            playerScore.setText(String.valueOf(MainActivity.sharedPreferences_score.getInt(player.getName(), 0)));
//            matchPlayed.setText(String.valueOf(MainActivity.sharedPreferences_matchPlayed.getInt(player.getName(), 0)));
//            matchLost.setText(String.valueOf(MainActivity.sharedPreferences_matchLost.getInt(player.getName(), 0)));
//            matchDrawn.setText(String.valueOf(MainActivity.sharedPreferences_matchDrawn.getInt(player.getName(), 0)));
//            matchPlayed.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.GreenishBlue));
//            playerScore.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.green));
//            matchLost.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.red));
//            matchDrawn.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.yellow));
//            playerName.setTextColor(player.getColor());
//            itemView.setOnClickListener(v -> onPlayerClickListener.onPlayerClick(player));
//        }
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.player_item, parent, false);
//        return new MyViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Player player = players.get(position);
//        holder.bind(player, onPlayerClickListener);
//    }
//
//    @Override
//    public int getItemCount() {
//        return players.size();
//    }
//}

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MyViewHolder> {
    private final List<Player> players;
    private final OnPlayerClickListener onPlayerClickListener;

    public PlayersAdapter(List<Player> players, OnPlayerClickListener onPlayerClickListener) {
        this.players = players;
        this.onPlayerClickListener = onPlayerClickListener;
    }

    public interface OnPlayerClickListener {
        void onPlayerClick(Player player);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerName, playerScore, matchPlayed, matchDrawn, matchLost;

        public MyViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerScore = itemView.findViewById(R.id.player_score);
            matchPlayed = itemView.findViewById(R.id.match_played);
            matchDrawn = itemView.findViewById(R.id.match_drawn);
            matchLost = itemView.findViewById(R.id.match_lost);
        }

        public void bind(Player player, OnPlayerClickListener onPlayerClickListener) {
            playerName.setText(player.getName());
            playerScore.setText(String.valueOf(MainActivity.sharedPreferences.getInt(player.getName() + "_won", 0)));
            matchPlayed.setText(String.valueOf(MainActivity.sharedPreferences.getInt(player.getName() + "_played", 0)));
            matchDrawn.setText(String.valueOf(MainActivity.sharedPreferences.getInt(player.getName() + "_drawn", 0)));
            matchLost.setText(String.valueOf(MainActivity.sharedPreferences.getInt(player.getName() + "_lost", 0)));

            playerName.setTextColor(player.getColor());
            matchPlayed.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.Teal));
            playerScore.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.green));
            matchLost.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.red));
            matchDrawn.setTextColor(ContextCompat.getColor(itemView.getContext(),R.color.yellow));
            itemView.setOnClickListener(v -> onPlayerClickListener.onPlayerClick(player));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Player player = players.get(position);
        holder.bind(player, onPlayerClickListener);
    }

    @Override
    public int getItemCount() {
        return players.size();
    }
}
