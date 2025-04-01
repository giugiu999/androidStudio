package com.example.listycitylab3;

import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.app.Dialog;

public class EditFragment extends DialogFragment {

    private City city;
    private AddCityFragment.AddCityDialogListener listener;

    public static EditFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putSerializable("city", city);
        EditFragment fragment = new EditFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityFragment.AddCityDialogListener) {
            listener = (AddCityFragment.AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city = (City) getArguments().getSerializable("city");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_add_city, null);
        EditText cityNameEditText = view.findViewById(R.id.edit_c);
        EditText provinceNameEditText = view.findViewById(R.id.edit_p);

        cityNameEditText.setText(city.getName());
        provinceNameEditText.setText(city.getProvince());

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view)
                .setTitle("Edit City")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {
                    // user input
                    String newCityName = cityNameEditText.getText().toString();
                    String newProvinceName = provinceNameEditText.getText().toString();
                    city.setName(newCityName);
                    city.setProvince(newProvinceName);
                    // update the original city
                    listener.addCity(city);
                });

        return builder.create();
    }
}
