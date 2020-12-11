package com.duolingo.app.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.duolingo.app.Data;
import com.duolingo.app.R;

public class PerfilFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_perfil, container, false);

        TextView tvMoney = (TextView) view.findViewById(R.id.tvCoins);
        tvMoney.setText(Integer.toString(Data.mkMoney));

        TextView tvPoints = (TextView) view.findViewById(R.id.tvCoins3);
        tvPoints.setText(Integer.toString(Data.mkPoints));

        return view;
    }
}