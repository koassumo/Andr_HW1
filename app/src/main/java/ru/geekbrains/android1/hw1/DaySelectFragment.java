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

public class DaySelectFragment extends Fragment implements IObserver {

    private RecyclerView recyclerView;
    private String[] listData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_day_select, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listData = getResources().getStringArray(R.array.list_days_array);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerDataAdapter adapter = new RecyclerDataAdapter(listData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void updateText(String text) {
        recyclerView.notifyAll();
        //recyclerView.textView.setText(text);
    }
}
