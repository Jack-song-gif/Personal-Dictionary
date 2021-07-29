package com.example.wordapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.Adapter.WordFormAdapter;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordSearch extends AppCompatActivity {
    private ImageView search;
    private EditText search_text;
    private Cursor cursor,cursor1;
    public RecyclerView search_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_search);

        search_recycler = findViewById(R.id.search_recycler);
        search = findViewById(R.id.word_search_new);
        search_text = findViewById(R.id.search_key_new);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchWord(v);
            }
        });
    }

    public void searchWord(View view){
        List<Word> results = new ArrayList<>();
        String key = search_text.getText().toString();
        String error = "Your input isn't compliant, please input English or Chinese";
        if (key == null){
            return;
        }else if (!isEnglish(key) && !isChinese(key)){
            Toast.makeText(view.getContext(),error,Toast.LENGTH_SHORT).show();
            return;
        }
        if (isEnglish(key)){
            cursor = DataSupport.findBySQL("select * from word " +
                    "where word like ?", key+"%");

        }else if (isChinese(key)){
            cursor = DataSupport.findBySQL("select * from word where " +
                    "interpretation like ?", "%"+key+"%");
        }
        if (cursor.moveToFirst()){
            do {
                String name = cursor.getString(cursor.getColumnIndex("word"));
                String translate = cursor.getString(cursor.getColumnIndex("interpretation"));
                results.add(new Word(name,translate));
            }while (cursor.moveToNext());
        }
        if (results.size() > 0){
            search_recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
            WordFormAdapter adapter = new WordFormAdapter(results);
            search_recycler.setAdapter(adapter);
            search_recycler.setVisibility(View.VISIBLE);
        }else {
            search_recycler.setVisibility(View.GONE);
            Toast.makeText(view.getContext(),"not found",Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isEnglish(String str){
        return str.matches("^[a-zA-Z]*");
    }

    public static boolean isChinese(String str){
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if(m.find()){
            return true;
        }
        return false;
    }

    public void backMain(View view) {
        finish();
    }
}