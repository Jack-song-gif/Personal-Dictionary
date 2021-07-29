package com.example.wordapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wordapplication.DicActivity;
import com.example.wordapplication.R;
import com.example.wordapplication.WordSearch;
import com.example.wordapplication.WordTest;
import com.example.wordapplication.WordTranslate;

public class fragment0 extends Fragment implements View.OnClickListener {
    View view;
    private ImageView word_form, word_search;
    private TextView start, translateStart, testNum;
    private SeekBar seekBar;
    private int maxNum = 100;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_fragment0,container,false);
        initView();
        bindComponent();
        return view;


    }

    private void initView() {
    }

    private void bindComponent() {
        word_form = view.findViewById(R.id.word_form);
        word_search = view.findViewById(R.id.word_search);
        start = view.findViewById(R.id.bt_start_test);
        translateStart = view.findViewById(R.id.word_translate);
        seekBar = view.findViewById(R.id.seek_bar);
        testNum = view.findViewById(R.id.test_num);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                testNum.setText("测试单词总数:" + progress);
                maxNum = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        start.setOnClickListener(this);
        word_form.setOnClickListener(this);
        word_search.setOnClickListener(this);
        translateStart.setOnClickListener(this);
    }
    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.word_form:
                Intent intent = new Intent(getActivity(), DicActivity.class);
                startActivity(intent);
                break;
            case R.id.word_search:
                Intent intent1 = new Intent(getActivity(), WordSearch.class);
                startActivity(intent1);
                break;
            case R.id.bt_start_test:
                if (maxNum < 1) {
                    Toast.makeText(getContext(), "单词总数不能为0", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent2 = new Intent(getActivity(), WordTest.class);
                intent2.putExtra("maxNum", maxNum);
                startActivity(intent2);
                break;
            case R.id.word_translate:
                Intent intent3 = new Intent(getActivity(), WordTranslate.class);
                startActivity(intent3);

        }
    }

}
