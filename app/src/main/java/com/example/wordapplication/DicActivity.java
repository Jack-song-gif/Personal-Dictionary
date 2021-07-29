package com.example.wordapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordapplication.Form.EWordForm;
import com.example.wordapplication.Form.IrWordForm;
import com.example.wordapplication.Form.RWordForm;
import com.example.wordapplication.Form.WordForm;


//查看词典
public class DicActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView dic1;
    private TextView dic2;
    private TextView dic3;
    private TextView dic4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dic);
        initView();
    }

    private void initView() {
        dic1 = findViewById(R.id.word_Dic);
        dic2 = findViewById(R.id.IWord_Dic);
        dic3 = findViewById(R.id.RWord_Dic);
        dic4 = findViewById(R.id.EWord_Dic);
        dic1.setOnClickListener(this);
        dic2.setOnClickListener(this);
        dic3.setOnClickListener(this);
        dic4.setOnClickListener(this);
    }
    public void backMain(View view) {
        Intent intent=new Intent(DicActivity.this, MainActivity.class);
        int id=0;
        intent.putExtra( "id" , 0);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.word_Dic:
                Intent intent=new Intent(DicActivity.this, WordForm.class);
                startActivity(intent);
                break;
            case R.id.IWord_Dic:
                Intent intent1=new Intent(DicActivity.this, IrWordForm.class);
                startActivity(intent1);
                break;
            case R.id.RWord_Dic:
                Intent intent2=new Intent(DicActivity.this, RWordForm.class);
                startActivity(intent2);
                break;
            case R.id.EWord_Dic:
                Intent intent3=new Intent(DicActivity.this, EWordForm.class);
                startActivity(intent3);
                break;
            default:
                    break;
        }
    }
}
