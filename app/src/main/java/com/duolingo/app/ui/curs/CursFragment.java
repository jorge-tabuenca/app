package com.duolingo.app.ui.curs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.duolingo.app.R;

public class CursFragment extends Fragment {

    private CursViewModel cursViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cursViewModel =
                ViewModelProviders.of(this).get(CursViewModel.class);
        View root = inflater.inflate(R.layout.fragment_curs, container, false);
        final TextView textView = root.findViewById(R.id.text_curs);
        cursViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}