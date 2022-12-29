package com.example.barangay_cleaning.ui.areas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AreasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AreasViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}