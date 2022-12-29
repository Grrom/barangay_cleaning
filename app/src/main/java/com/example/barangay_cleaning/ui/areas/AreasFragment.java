package com.example.barangay_cleaning.ui.areas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.AreasAdapter;
import com.example.barangay_cleaning.adapters.ResidentsAdapter;
import com.example.barangay_cleaning.databinding.FragmentAreasBinding;
import com.example.barangay_cleaning.databinding.FragmentResidentsBinding;
import com.example.barangay_cleaning.databinding.FragmentSlideshowBinding;
import com.example.barangay_cleaning.models.Area;
import com.example.barangay_cleaning.models.Resident;
import com.example.barangay_cleaning.ui.residents.ResidentsViewModel;

import java.util.ArrayList;

public class AreasFragment extends Fragment {

    private FragmentAreasBinding binding;

    ArrayList<Area> areas = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAreasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.areasRecyclerView;
        setupAreasModel();

        AreasAdapter adapter  = new AreasAdapter(getContext(), areas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        AreasViewModel areasViewModel =
                new ViewModelProvider(this).get(AreasViewModel.class);

        return root;
    }

    private void setupAreasModel(){
        String[] names = {"park", "basketball court", "covered court"};
        int image= R.drawable.temp_bg;
        String status = "clean";

        for (int i = 0; i < names.length; i++){
            areas.add(new Area(image, names[i],  status));
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}