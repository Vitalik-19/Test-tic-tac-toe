package com.example.testtic_tac_toe.ui.clicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class ClickerViewModel extends ViewModel {
    private Random random = new Random();

    private MutableLiveData<Integer> score;
    private MutableLiveData<Integer> idImage;

    public LiveData<Integer> getScore() {
        if (score == null)
            score = new MutableLiveData<>();
        return score;
    }

    public LiveData<Integer> getIdImage() {
        if (idImage == null)
            idImage = new MutableLiveData<>();
        return idImage;
    }

    public void onClickYes() {
        if (score.getValue() == null)
            score.setValue(0);
        if (idImage.getValue() == null)
            idImage.setValue(0);

        if (getIdImage().getValue() == 1) {
            score.setValue(getScore().getValue() + 10);
        } else {
            gameOver();
        }
        idImage.setValue(random.nextInt(0 + 2));
    }

    public void onClickNo() {
        if (score.getValue() == null)
            score.setValue(0);
        if (idImage.getValue() == null)
            idImage.setValue(0);

        if (getIdImage().getValue() == 0) {
            score.setValue(getScore().getValue() + 5);
        } else {
            gameOver();
        }
        idImage.setValue(random.nextInt(0 + 2));
    }

    private void gameOver() {
        score.setValue(getScore().getValue() - 20);
    }
}