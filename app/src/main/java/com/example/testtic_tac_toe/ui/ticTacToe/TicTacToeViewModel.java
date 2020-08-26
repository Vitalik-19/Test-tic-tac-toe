package com.example.testtic_tac_toe.ui.ticTacToe;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testtic_tac_toe.logic.Game;
import com.example.testtic_tac_toe.logic.Square;

public class TicTacToeViewModel extends ViewModel {

    private MutableLiveData<Square[][]> field;

    LiveData<Square[][]> getField() {
        if (field == null) {
            field = new MutableLiveData<>();
        }
        return field;
    }
}