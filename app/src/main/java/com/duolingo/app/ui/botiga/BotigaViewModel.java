package com.duolingo.app.ui.botiga;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BotigaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BotigaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is botiga fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}