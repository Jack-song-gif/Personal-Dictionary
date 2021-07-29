package com.example.wordapplication;

import android.os.Bundle;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wordapplication.Adapter.NoteFormAdapter;
import com.example.wordapplication.entity.Pword;

import org.litepal.crud.DataSupport;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private List<Pword> mWord;
    private PopupMenu popupMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        initWord();
        RecyclerView PwordFormRecycler = (RecyclerView) findViewById(R.id.diary_note_recycler);

        PwordFormRecycler.setLayoutManager(new LinearLayoutManager(this));
        NoteFormAdapter adapter = new NoteFormAdapter(mWord);
//        adapter.setOnItemClickListener(new NoteFormAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View itemView, int position) {
//                popupMenu = new PopupMenu(itemView.getContext(),itemView);
//                popupMenu.getMenuInflater().inflate(R.menu.diary_menu,popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()){
//                            case R.id.diary_to_save:
//                                //Toast.makeText(v.getContext(),"保存",Toast.LENGTH_SHORT).show();
//                                break;
//                            case R.id.dairy_to_delete:
//
//                                break;
//                            case R.id.dairy_to_modify:
//                                Pword pword=new Pword();
//
//                                break;
//                        }
//                        return true;
//                    }
//                });
//                popupMenu.show();
//
//            }
//        });
        PwordFormRecycler.setAdapter(adapter);

    }

    private List<Pword> initWord(){
        mWord = DataSupport.findAll(Pword.class);
        return mWord;
    }
}
