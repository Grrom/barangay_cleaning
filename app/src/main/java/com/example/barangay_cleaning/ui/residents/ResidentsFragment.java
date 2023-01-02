package com.example.barangay_cleaning.ui.residents;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.databinding.FragmentResidentsBinding;
import com.example.barangay_cleaning.models.Constants;
import com.example.barangay_cleaning.models.Resident;
import com.example.barangay_cleaning.adapters.ResidentsAdapter;

import java.util.ArrayList;

public class ResidentsFragment extends Fragment {

    private FragmentResidentsBinding binding;

    ArrayList<Resident> residents = new ArrayList<>();

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

        searchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable chars) {
                String input = chars.toString();
                Log.e("DD", input );

                setupResidentModels();
                residents.removeIf(s -> !(s.getFullName()+ " " + s.getAddress()).toLowerCase().contains((input.toLowerCase())));

                adapter.notifyDataSetChanged();
            }
        } );

        return root;
    }

    private void setupResidentModels(){
        residents.clear();
        residents.addAll(Constants.getResidents());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}