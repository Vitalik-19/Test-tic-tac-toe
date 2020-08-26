package com.example.testtic_tac_toe.ui.ticTacToe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testtic_tac_toe.R;

class TicTacToeAdapter extends RecyclerView.Adapter<TicTacToeAdapter.ViewHolder> {
    Integer[][] squarePosition = new Integer[3][3];

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public TicTacToeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View imageView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_button, viewGroup,false);
        ViewHolder holder = new ViewHolder(imageView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TicTacToeAdapter.ViewHolder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return 9;
    }
}
