package com.duolingo.app.ui.lliga;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.duolingo.app.R;
import com.duolingo.app.tasks.TaskActivity;

public class LligaFragment extends Fragment {

    private LligaViewModel lligaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lligaViewModel =
                ViewModelProviders.of(this).get(LligaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lliga, container, false);

        Button btNext = root.findViewById(R.id.mkButtonActivity);
        btNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TaskActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
}