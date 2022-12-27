package com.example.barangay_cleaning.ui.residents;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResidentsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ResidentsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is residents fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}