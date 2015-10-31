package com.example.john.jclprofilework.Introduce;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.john.jclprofilework.R;
import com.example.john.jclprofilework.jclModule.Tools;


public class Introduce extends Fragment {
    public Introduce() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tools.debug("We are in Introduce", 3);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.introduce, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Tools.debug("Introduce -- onActivityCreated", 3);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
