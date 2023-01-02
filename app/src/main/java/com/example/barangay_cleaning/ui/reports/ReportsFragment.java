package com.example.barangay_cleaning.ui.reports;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.adapters.ReportsAdapter;
import com.example.barangay_cleaning.databinding.FragmentReportsBinding;
import com.example.barangay_cleaning.models.Constants;
import com.example.barangay_cleaning.models.Report;

import java.util.ArrayList;

public class ReportsFragment extends Fragment {


    private FragmentReportsBinding binding;

    ArrayList<Report> reports = new ArrayList<>();
    ReportsAdapter adapter ;

    @Override
    public void onResume() {
        super.onResume();
        setupReports();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReportsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = binding.reportsRecyclerView;
        EditText searchField = binding.searchReports;
        Spinner reportsSort = binding.reportsSort;

        setupReports();

        adapter  = new ReportsAdapter(getContext(), reports);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        reportsSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected;
                if(view==null){
                    selected= "all";
                }else{
                    selected= ((TextView)view).getText().toString();
                }
                setupReports();
                if(!selected.equals("all")){
                    reports.removeIf(s -> !s.getStatus().equalsIgnoreCase(selected.toLowerCase()));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

                setupReports();
                reports.removeIf(s -> !(s.getName()+ " "+ s.getOffender().getFullName()).toLowerCase().contains((input.toLowerCase())));
                adapter.notifyDataSetChanged();
            }
        });

        return root;
    }

    private void setupReports(){
        reports.clear();
        reports.addAll(Constants.getReports(getActivity()));
        if(adapter!=null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}