package com.example.barangay_cleaning.ui.reports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.AreasAdapter;
import com.example.barangay_cleaning.adapters.ReportsAdapter;
import com.example.barangay_cleaning.databinding.FragmentAreasBinding;
import com.example.barangay_cleaning.databinding.FragmentReportsBinding;
import com.example.barangay_cleaning.databinding.FragmentResidentsBinding;
import com.example.barangay_cleaning.databinding.FragmentSlideshowBinding;
import com.example.barangay_cleaning.models.Report;
import com.example.barangay_cleaning.models.Resident;
import com.example.barangay_cleaning.ui.areas.AreasViewModel;

import java.util.ArrayList;

public class ReportsFragment extends Fragment {


    private FragmentReportsBinding binding;

    ArrayList<Report> reports = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReportsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.reportsRecyclerView;

        setupReports();

        ReportsAdapter adapter  = new ReportsAdapter(getContext(), reports);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return root;
    }

    private void setupReports(){
        String name ="Littering";
        String status= "unresolved";
        int image= R.drawable.temp_profile;
        String[] names = {"Gab", "Kyle","Jerome"};

        for (int i = 0; i < names.length; i++){
            reports.add(new Report(image, name,status , new Resident(image, names[i], 20, "Purok 3")));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}