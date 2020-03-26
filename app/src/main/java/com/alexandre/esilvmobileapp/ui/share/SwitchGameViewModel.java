package com.alexandre.esilvmobileapp.ui.share;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SwitchGameViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SwitchGameViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Switch the game here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}