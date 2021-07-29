package com.example.wordapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.wordapplication.NoteActivity;
import com.example.wordapplication.R;
import com.example.wordapplication.entity.Pword;

import java.text.SimpleDateFormat;
import java.util.Date;

public class fragment2 extends Fragment implements View.OnClickListener{
    View view;
    private ImageView noteSave;
    //private ImageView noteModify;
    private EditText editText;
    private ImageView myhistory;
    private  String TAG=" fragment2";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.activity_fragment2,container,false);
        initView();
        return view;


    }

    private void initView() {
        noteSave = view.findViewById(R.id.note_save);
        //noteModify = view.findViewById(R.id.note_modify);
        editText = view.findViewById(R.id.Edit_write);
        myhistory = view.findViewById(R.id.find_note);
        noteSave.setOnClickListener(this);
        myhistory.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.note_save:
                Pword pword=new Pword();
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                pword.setMysaying(editText.getText().toString());
                pword.setDate(sdf.format(date));
                pword.save();
                Log.d(TAG,"保存成功");
                String saying=editText.getText().toString();
                Toast.makeText(getContext(),saying,Toast.LENGTH_SHORT).show();
                editText.setText("");
                break;
            case R.id.find_note:
                Intent intent1 = new Intent(getActivity(), NoteActivity.class);
                startActivity(intent1);
                break;
            default:
                    break;

        }
    }
}
