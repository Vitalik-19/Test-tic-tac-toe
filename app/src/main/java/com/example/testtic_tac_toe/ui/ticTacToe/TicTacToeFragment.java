package com.example.testtic_tac_toe.ui.ticTacToe;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.testtic_tac_toe.R;
import com.example.testtic_tac_toe.databinding.TicTacToeFragmentBinding;
import com.example.testtic_tac_toe.logic.Game;
import com.example.testtic_tac_toe.logic.Player;
import com.example.testtic_tac_toe.logic.Square;

public class TicTacToeFragment extends Fragment {

    private TicTacToeViewModel viewModel;
    private TicTacToeFragmentBinding binding;
    private Game game;
    private Button[][] buttons = new Button[3][3];


    public static TicTacToeFragment newInstance() {
        return new TicTacToeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tic_tac_toe_fragment, container, false);
        viewModel = ViewModelProviders.of(this).get(TicTacToeViewModel.class);

        game = new Game();
        buildGameField();
        game.start();

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    public void buildGameField() {
        Square[][] field = game.getField();
        int dip = 80;
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.width = (int) px;
        params.height = (int) px;
        params.setMargins(2, 2, 2, 2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button(this.getContext());

                buttons[i][j].setBackgroundColor(Color.argb(255, 240, 240, 240));
                buttons[i][j].setLayoutParams(params);
                buttons[i][j].setOnClickListener(new Listener(i, j));

                binding.gridLayout.addView(buttons[i][j]);
            }
        }
    }

    public class Listener implements View.OnClickListener {
        private int x;
        private int y;

        Listener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void onClick(View view) {
            Button button = (Button) view;
            Game g = game;
            Player player = g.getCurrentActivePlayer();
            if (makeTurn(x, y)) {
                button.setText(player.getName());
            }
            Player winner = g.checkWinner();
            if (winner != null) {
                gameOver(winner);
            }
            if (g.isFieldFilled()) {  // в случае, если поле заполнено
                gameOver();
            }
        }
    }

    private void gameOver(Player player) {
        CharSequence text = "Player \"" + player.getName() + "\" won!";
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private void gameOver() {
        CharSequence text = "Draw";
        Toast.makeText(this.getContext(), text, Toast.LENGTH_SHORT).show();
        game.reset();
        refresh();
    }

    private boolean makeTurn(int x, int y) {
        return game.makeTurn(x, y);
    }

    private void refresh() {
        Square[][] field = game.getField();

        for (int i = 0, len = field.length; i < len; i++) {
            for (int j = 0, len2 = field[i].length; j < len2; j++) {
                if (field[i][j].getPlayer() == null) {
                    buttons[i][j].setText("");
                } else {
                    buttons[i][j].setText(field[i][j].getPlayer().getName());
                }
            }
        }
    }
}