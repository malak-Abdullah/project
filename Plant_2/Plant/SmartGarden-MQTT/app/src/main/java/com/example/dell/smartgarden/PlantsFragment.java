package com.example.dell.smartgarden;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class PlantsFragment extends Fragment {
    Button btn_plants, btn_vegetables;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_plants, null);

    }
//************
@Override
public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    btn_plants = (Button) getActivity().findViewById(R.id.btn_plant);
    btn_plants.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Activity_GlobalVariable.SELECTTYPE="1";
            startActivity(new Intent(getActivity(), Activity_getlist.class)
            );
        }
    });

   getActivity().findViewById(R.id.btn_vegetable).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Activity_GlobalVariable.SELECTTYPE="2";
            startActivity(new Intent(getActivity(), Activity_getlist.class)
                   );
        }
    });


}
// ************

}