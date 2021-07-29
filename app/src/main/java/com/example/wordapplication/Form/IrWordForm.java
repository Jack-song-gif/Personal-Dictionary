package com.example.wordapplication.Form;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.Adapter.IrWordFormAdapter;
import com.example.wordapplication.DicActivity;
import com.example.wordapplication.IrWord;
import com.example.wordapplication.R;

import org.litepal.crud.DataSupport;

import java.util.List;

public class IrWordForm extends AppCompatActivity {
    private TextView wordNum;
    private List<IrWord> mWord;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ir_word_form);
        initWord();
        RecyclerView wordFormRecycler = (RecyclerView) findViewById(R.id.word_form_recycler);
        wordNum = findViewById(R.id.word_num);
        wordNum.setText("共有 "+mWord.size()+" 个单词");

        wordFormRecycler.setLayoutManager(new LinearLayoutManager(this));
        IrWordFormAdapter adapter = new IrWordFormAdapter(mWord);
        wordFormRecycler.setAdapter(adapter);

    }

    private List<IrWord> initWord(){
        mWord = DataSupport.findAll(IrWord.class);
        return mWord;
    }


    public void backMain(View view) {
        Intent intent=new Intent(IrWordForm.this, DicActivity.class);
        startActivity(intent);
    }
}
