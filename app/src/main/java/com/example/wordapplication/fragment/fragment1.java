package com.example.wordapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.Adapter.DayFormAdapter;
import com.example.wordapplication.DaySense;
import com.example.wordapplication.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class fragment1 extends Fragment {
    View view;
    private List<DaySense> mWord;
    private List<Integer> path;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_fragment1,container,false);
        initWord();
        initView();
        return view;


    }

    private void initView() {


        RecyclerView wordFormRecycler = (RecyclerView)view.findViewById(R.id.day_form_recycler);

        wordFormRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        DayFormAdapter adapter = new DayFormAdapter(mWord);
        wordFormRecycler.setAdapter(adapter);
    }



    private List<DaySense> initWord(){

        mWord = DataSupport.findAll(DaySense.class);
        return mWord;
    }

}
