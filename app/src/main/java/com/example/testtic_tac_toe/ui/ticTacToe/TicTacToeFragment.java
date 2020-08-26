package com.example.testtic_tac_toe.ui.ticTacToe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtic_tac_toe.R;
import com.example.testtic_tac_toe.databinding.TicTacToeFragmentBinding;

public class TicTacToeFragment extends Fragment {

    private TicTacToeViewModel viewModel;
    private TicTacToeFragmentBinding binding;
    private TicTacToeAdapter adapter;

    public static TicTacToeFragment newInstance() {
        return new TicTacToeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tic_tac_toe_fragment, container, false);
        viewModel = ViewModelProviders.of(this).get(TicTacToeViewModel.class);
        adapter = new TicTacToeAdapter();

        Button[][] buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this.getContext());

                buttons[i][j].setBackgroundColor(Color.LTGRAY);
                buttons[i][j].setHeight(160);
                buttons[i][j].setWidth(80);
//                binding.gridLayout.addView(buttons[i][j]);
            }
        }
        binding.ticTacToeRecyclerView.setAdapter(adapter);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}