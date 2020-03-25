package com.inventariosbodegas.rommeltorres.myfirstapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.inventariosbodegas.rommeltorres.myfirstapp.R;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Spinner cmbEmpleados, cmbEmpleados1;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);


        cmbEmpleados = root.findViewById(R.id.cmbEmpleados);
        cmbEmpleados1 = root.findViewById(R.id.cmbEmpleados1);
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        ArrayAdapter empleadosArrayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, stringList);
        cmbEmpleados.setAdapter(empleadosArrayAdapter);
        final ArrayAdapter empleados1ArrayAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_design, new ArrayList());
        cmbEmpleados1.setAdapter(empleados1ArrayAdapter);

        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                textView.setText("Bienvenidos");
            }
        });
        homeViewModel.getEmpleadosListLiveData().observe(getViewLifecycleOwner(), new Observer<List<EMPLEADOS>>() {
            @Override
            public void onChanged(List<EMPLEADOS> empleadosList) {
                empleados1ArrayAdapter.clear();
                empleados1ArrayAdapter.addAll(empleadosList);
                Toast.makeText(getContext(), "Nuevo cambio", Toast.LENGTH_SHORT).show();
            }
        });

        EMPLEADOS empleados = new EMPLEADOS();
        empleados.EMP_NOMBRE = "AGUSTIN";
        empleados.EMP_CEDULA = "NO TIENE";
        Long id = homeViewModel.insertEmpleado(empleados);
        Toast.makeText(getContext(), id + "", Toast.LENGTH_SHORT).show();
        cmbEmpleados1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int bj = ((EMPLEADOS) parent.getSelectedItem()).EMP_ID;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return root;
    }
}