package com.example.barangay_cleaning.ui.residents;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.databinding.FragmentResidentsBinding;
import com.example.barangay_cleaning.models.Resident;
import com.example.barangay_cleaning.adapters.ResidentsAdapter;

import java.util.ArrayList;
import java.util.Locale;

public class ResidentsFragment extends Fragment {

    private FragmentResidentsBinding binding;

    ArrayList<Resident> residents = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResidentsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.residentsRecyclerView;
        EditText searchField = binding.searchResident;

        setupResidentModels();

        ResidentsAdapter adapter  = new ResidentsAdapter(getContext(), residents);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ResidentsViewModel galleryViewModel =
                new ViewModelProvider(this).get(ResidentsViewModel.class);

        searchField.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == 1){
                String input = ((EditText)v).getText().toString();

                if((input.isEmpty())){
                    residents.clear();
                    setupResidentModels();
                }else{
                    residents.removeIf(s -> !s.getName().toLowerCase().contains((input.toLowerCase())));
                }
                adapter.notifyDataSetChanged();
            }
            return false;
        });

        return root;
    }

    private void setupResidentModels(){
        String[] names = {"Gab", "Kyle","Jerome"};
        int image= R.drawable.temp_profile;
        int age = 20;
        String address = "Purok 3";

        for (int i = 0; i < names.length; i++){
            residents.add(new Resident(image, names[i], age, address));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}