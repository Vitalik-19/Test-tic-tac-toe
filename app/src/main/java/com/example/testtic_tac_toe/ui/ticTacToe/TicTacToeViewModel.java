package com.example.testtic_tac_toe.ui.ticTacToe;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TicTacToeViewModel extends ViewModel {

    private MutableLiveData<ImageView[][]> buttons;

//    TicTacToeViewModel() {
//        onReset();
//    }

    private LiveData<ImageView[][]> getButtons() {
        if (buttons == null) {
            buttons = new MutableLiveData<>();
        }
        return buttons;
    }

    void getCreateButtons(ImageView[][] imageViews) {
        buttons.setValue(imageViews);
    }

    void onReset() {
        for (int i = 0; i > 3; i++) {
            for (int j = 0; j > 3; j++) {
        buttons.getValue()[i][j].setImageDrawable(null);
            }
        }
    }

    enum StateSquare {NONE, CROSS, ZERO}

}