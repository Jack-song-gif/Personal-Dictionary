package com.example.wordapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.wordapplication.entity.Eword;
import com.example.wordapplication.entity.RootWord;
import com.example.wordapplication.fragment.fragment0;
import com.example.wordapplication.fragment.fragment1;
import com.example.wordapplication.fragment.fragment2;
import com.example.wordapplication.fragment.fragment3;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = null;


    private FrameLayout mFragmentLayoutTest;//占位

    private Intent intent;
    private ImageView mImgTrans;
    private ImageView mImgDiary;
    private ImageView mImgWrite;
    private ImageView mImgown;

    FragmentManager fm;
    FragmentTransaction ft;
    Fragment mCurrentFragment;
    //用于标识上一个fragment
    private int lastFragment;

    public Fragment[] fragmentlist;
    private  Fragment mTab01 = new fragment0();

    private Fragment  mTab02 = new fragment1();
    private Fragment  mTab03 = new fragment2();
    private Fragment mTab04 = new fragment3();
    private int id=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db= Connector.getDatabase();
        Word word = DataSupport.findFirst(Word.class);
        IrWord irword=DataSupport.findFirst(IrWord.class);
        DaySense daySense=DataSupport.findFirst(DaySense.class);
        RootWord rootWord=DataSupport.findFirst(RootWord.class);
        Eword eword=DataSupport.findFirst(Eword.class);
        if (word == null) {
            readTxt(this, "Result.txt");
            Log.d(TAG,"出错了");
        }
        if (irword == null) {
            readTxt1(this, "irregularn.txt");
            Log.d(TAG,"出错了");
        }
        if (daySense == null) {
            readTxt2(this, "day.txt");
            Log.d(TAG,"出错了");
        }
        if (rootWord == null) {
            readTxt3(this, "Rootword.txt");
            Log.d(TAG,"出错了");
        }
        if (eword == null) {
            readTxt4(this, "Eword.txt");
            Log.d(TAG,"出错了");
        }
        //DataSupport.deleteAll(Pword.class);//删除表中所有数据
        initView();
        initEvent();
        initFragment();
        id=getIntent().getIntExtra("id",0);
        SetSelect(id);

    }
    private void initView() {
        mFragmentLayoutTest = (FrameLayout)findViewById(R.id.id_content);
        mImgTrans = (ImageView) findViewById(R.id.main_iv_Trans);
        mImgDiary = (ImageView) findViewById(R.id.main_iv_Diary);
        mImgWrite = (ImageView) findViewById(R.id.main_iv_Write);
        mImgown = (ImageView) findViewById(R.id.main_iv_own);
    }






    private void readTxt(Context context, String name) {
        BufferedReader reader = null;
        String temp = null;

        try {
            InputStream is = context.getAssets().open(name);
            reader = new BufferedReader(new InputStreamReader(is));
            while ((temp = reader.readLine()) != null) {
                Word word = new Word();
                String[] words = temp.split("=>");
                word.setWord(words[0]);
                word.setInterpretation(words[1]);
                word.save();//保存数据到数据库中
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readTxt1(Context context, String name) {
        BufferedReader reader = null;
        String temp = null;

        try {
            InputStream is = context.getAssets().open(name);
            reader = new BufferedReader(new InputStreamReader(is));
            while ((temp = reader.readLine()) != null) {
                IrWord word = new IrWord();
                String[] words = temp.split("→");
                word.setProWord(words[0]);
                word.setComWord(words[1]);
                word.save();//保存数据到数据库中
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void readTxt2(Context context, String name) {
        BufferedReader reader = null;
        String temp = null;

        try {
            InputStream is = context.getAssets().open(name);
            reader = new BufferedReader(new InputStreamReader(is));
            while ((temp = reader.readLine()) != null) {
                DaySense daySense = new DaySense();
                String[] DayS = temp.split("=>");
                daySense.setWord(DayS[0]);
                daySense.setInterpretation(DayS[1]);
                daySense.save();//保存数据到数据库中
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void readTxt3(Context context, String name) {
        BufferedReader reader = null;
        String temp = null;

        try {
            InputStream is = context.getAssets().open(name);
            reader = new BufferedReader(new InputStreamReader(is));
            while ((temp = reader.readLine()) != null) {
                RootWord rootWord = new RootWord();
                String[] rootW = temp.split("=>");
                rootWord.setRootWord(rootW[0]);
                rootWord.setRootMeaning(rootW[1]);
                rootWord.save();//保存数据到数据库中
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void readTxt4(Context context, String name) {
        BufferedReader reader = null;
        String temp = null;
        try {
            InputStream is = context.getAssets().open(name);
            reader = new BufferedReader(new InputStreamReader(is));
            int line=1;
            while ((temp = reader.readLine()) != null) {
                Eword eword = new Eword();
                String[] eWod = temp.split("=>");
                eword.setEword(eWod[0]);
                eword.seteMeaning(eWod[1]);
                eword.setRootWord(eWod[2]);
                eword.save();//保存数据到数据库中
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void initFragment(){
        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.id_content,mTab01);
        transaction.add(R.id.id_content, mTab02);
        transaction.add(R.id.id_content, mTab03);
        transaction.add(R.id.id_content, mTab04);
        transaction.commit();
    }
    private void hideFragment (FragmentTransaction transaction){
        transaction.hide(mTab01);
        transaction.hide(mTab02);
        transaction.hide(mTab03);
        transaction.hide(mTab04);
    }
    private void SetSelect(int i){
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (i){
            case 0:
                Log.d("setSelect", "1");

                transaction.show(mTab01);
                mImgTrans.setImageResource(R.drawable.trans_pressed);
                break;
            case 1:
                Log.d("setSelect", "2");
                transaction.show(mTab02);
                mImgDiary.setImageResource(R.drawable.diary_pressed);
                break;
            case 2:
                Log.d("setSelect", "3");
                transaction.show(mTab03);
                mImgWrite.setImageResource(R.drawable.write_pressed);
                break;
            case 3:
                Log.d("setSelect", "4");
                transaction.show(mTab04);
                mImgown.setImageResource(R.drawable.person_pressed);
                break;
            default:
                break;
        }
        transaction.commit();
    }
    private void initEvent() {
        mImgTrans.setOnClickListener((View.OnClickListener) this);
        mImgDiary.setOnClickListener((View.OnClickListener) this);
        mImgWrite.setOnClickListener((View.OnClickListener) this);
        mImgown.setOnClickListener((View.OnClickListener) this);
    }
    public void resetImgs() {
        mImgTrans.setImageResource(R.drawable.trans_normal);
        mImgDiary.setImageResource(R.drawable.diary_normal);
        mImgWrite.setImageResource(R.drawable.write_normal);
        mImgown.setImageResource(R.drawable.person_normal);
    }
    @Override
    public void onClick(View v) {
        Log.d("onClick", "1");
        resetImgs();
        switch (v.getId()){
            case R.id.main_iv_Trans:
                SetSelect(0);
                break;
            case  R.id.main_iv_Diary:
                SetSelect(1);
                break;
            case R.id.main_iv_Write:
                SetSelect(2);
                break;
            case R.id.main_iv_own:
                SetSelect(3);
                break;
            default:
                break;
        }

    }


}
