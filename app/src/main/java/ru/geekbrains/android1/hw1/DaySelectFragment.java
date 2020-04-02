package ru.geekbrains.android1.hw1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DaySelectFragment extends Fragment {


    private RecyclerView recyclerView;
    private String[] listData = new String[] {"Сегодня", "Завтра", "Послезавтра", "Через 2 дня",
            "Через 3 дня", "Через 4 дня", "Через 5 дней", "Через 6 дней", "Через 7 дней"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerDataAdapter adapter = new RecyclerDataAdapter(listData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
