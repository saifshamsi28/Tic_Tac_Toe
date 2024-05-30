package com.example.tic_tac_toe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.MyViewHolder> {

    private List<Player> players;
    private OnPlayerClickListener onPlayerClickListener;

    public interface OnPlayerClickListener {
        void onPlayerClick(Player player);
    }

    public PlayersAdapter(List<Player> players, OnPlayerClickListener onPlayerClickListener) {
        this.players = players;
        this.onPlayerClickListener = onPlayerClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView playerName;
        public TextView playerScore;

        public MyViewHolder(View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.player_name);
            playerScore = itemView.findViewById(R.id.player_score);
        }

        public void bind(Player player, OnPlayerClickListener onPlayerClickListener) {
            playerName.setText(player.getName());
            playerScore.setText(String.valueOf(MainActivity.sharedPreferences_score.getInt(player.getName(), 0)));
            playerName.setTextColor(player.getColor());
            playerScore.setTextColor(player.getColor());
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
