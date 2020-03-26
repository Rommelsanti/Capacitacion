package com.inventariosbodegas.rommeltorres.myfirstapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.inventariosbodegas.rommeltorres.myfirstapp.R;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.CARGAS_FAMILIARES;
import com.inventariosbodegas.rommeltorres.myfirstapp.data.model.EMPLEADOS;
import com.inventariosbodegas.rommeltorres.myfirstapp.webApi.WebApiAdapter;
import com.inventariosbodegas.rommeltorres.myfirstapp.webApi.webApiServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Spinner cmbEmpleados, cmbEmpleados1;
    private TextView textView;
    private Button cmbConection, cmbConection1, btnCrearEnviar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView = root.findViewById(R.id.text_home);


        cmbEmpleados = root.findViewById(R.id.cmbEmpleados);
        cmbEmpleados1 = root.findViewById(R.id.cmbEmpleados1);
        cmbConection = root.findViewById(R.id.cmbConection);
        cmbConection1 = root.findViewById(R.id.cmbConection1);
        btnCrearEnviar = root.findViewById(R.id.btnCrearEnviar);

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

        final EMPLEADOS empleados = new EMPLEADOS();
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

        cmbConection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call<List<EMPLEADOS>> empleadosCall = WebApiAdapter.getWebApiService(webApiServices.class).getEmpleados();
                        Response<List<EMPLEADOS>> responseEmpleados = null;
                        try {
                            responseEmpleados = empleadosCall.execute();
                            if (responseEmpleados.isSuccessful()) {
                                final Response<List<EMPLEADOS>> finalResponseEmpleados = responseEmpleados;
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        empleados1ArrayAdapter.clear();
                                        empleados1ArrayAdapter.addAll(finalResponseEmpleados.body());
                                        Toast.makeText(getContext(), "Descarga Web", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();

            }
        });

        cmbConection1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Call<List<EMPLEADOS>> empleadosCall = WebApiAdapter.getWebApiService(webApiServices.class).getEmpleados();
                empleadosCall.enqueue(new Callback<List<EMPLEADOS>>() {
                    @Override
                    public void onResponse(Call<List<EMPLEADOS>> call, final Response<List<EMPLEADOS>> response) {
                        if (response.isSuccessful()) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    empleados1ArrayAdapter.clear();
                                    empleados1ArrayAdapter.addAll(response.body());
                                    Toast.makeText(getContext(), "Descarga Web 2", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<EMPLEADOS>> call, Throwable t) {

                    }
                });
            }
        });

        btnCrearEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMPLEADOS empleados1 = new EMPLEADOS();
                empleados1.EMP_NOMBRE = "ROMMEL";
                empleados1.EMP_DIRECCION = "LA MAGDALENA";
                empleados1.CARGAS_FAMILIARES = new ArrayList<>();
                empleados1.EMP_STATUSSERVER = false;
                Long id = homeViewModel.insertEmpleado(empleados1);
                CARGAS_FAMILIARES cargas_familiares = new CARGAS_FAMILIARES();
                cargas_familiares.CARFAM_NOMBRE = "HUNTER";
                cargas_familiares.EMP_ID = Integer.parseInt(id + "");
                homeViewModel.insertCargasFalimiares(cargas_familiares);
                homeViewModel.changeStatus(cargas_familiares.EMP_ID);
            }
        });
        return root;
    }
}