package com.inventariosbodegas.rommeltorres.myfirstapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        homeViewModel.getEmpleadosListLiveData().observe(getViewLifecycleOwner(), new Observer<List<EMPLEADOS>>() {
            @Override
            public void onChanged(List<EMPLEADOS> empleadosList) {
                Toast.makeText(getContext(), "Nuevo cambio", Toast.LENGTH_SHORT).show();
            }
        });

        EMPLEADOS empleados = new EMPLEADOS();
        empleados.EMP_NOMBRE = "AGUSTIN";
        empleados.EMP_CEDULA = "NO TIENE";
        Long id = homeViewModel.insertEmpleado(empleados);
        Toast.makeText(getContext(), id + "", Toast.LENGTH_SHORT).show();

        return root;
    }
}