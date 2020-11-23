package com.duolingo.app.ui.curs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CursViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CursViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is curs fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}