package com.example.barangay_cleaning.ui.activities;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActivitiesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ActivitiesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is activities fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}