package com.duolingo.app.ui.lliga;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LligaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LligaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lliga fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}