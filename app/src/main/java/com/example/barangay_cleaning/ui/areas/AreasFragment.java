package com.example.barangay_cleaning.ui.areas;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.AreasAdapter;
import com.example.barangay_cleaning.databinding.FragmentAreasBinding;
import com.example.barangay_cleaning.models.Area;

import java.util.ArrayList;

public class AreasFragment extends Fragment {

    private FragmentAreasBinding binding;

    ArrayList<Area> areas = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAreasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.areasRecyclerView;
        EditText searchField = binding.searchArea;
        Spinner areasSort = binding.areasSort;

        setupAreasModel();

        AreasAdapter adapter  = new AreasAdapter(getContext(), areas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        areasSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected;
                if(view==null){
                    selected= "all";
                }else{
                    selected= ((TextView)view).getText().toString();
                }
                areas.clear();
                setupAreasModel();
                if(!selected.equals("all")){
                    areas.removeIf(s -> !s.getStatus().equalsIgnoreCase(selected.toLowerCase()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        searchField.setOnKeyListener((v, keyCode, event) -> {
            if(event.getAction() == 1){
                String input = ((EditText)v).getText().toString();

                if((input.isEmpty())){
                    areas.clear();
                    setupAreasModel();
                }else{
                    areas.removeIf(s -> !s.getName().toLowerCase().contains((input.toLowerCase())));
                }
                adapter.notifyDataSetChanged();
            }
            return false;
        });

        return root;
    }

    private void setupAreasModel(){
        String[] names = {"park", "basketball court", "covered court"};
        int image= R.drawable.temp_bg;
        String status = "unclean";

        areas.add(new Area(image, "Kindergarten",  "clean"));
        for (int i = 0; i < names.length; i++){
            areas.add(new Area(image, names[i],  status));
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(getContext(), "destroy areas", Toast.LENGTH_LONG);
        binding = null;
    }
}