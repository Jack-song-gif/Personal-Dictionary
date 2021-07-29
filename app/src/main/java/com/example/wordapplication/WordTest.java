package com.example.wordapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.litepal.crud.DataSupport;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class WordTest extends AppCompatActivity {

    private String TAG="WordTest";
    private TextView option1,option2,option3,option4,quest,maxTestNum,passed,previous;
//    四个选项中的Word类
    private Word A,B,C,D;
//    四个选项在数据库中的id数组
    int[] num =new int[]{0, 0, 0, 0};
//    已通过的单词数量
    int passedNum = 0;
    int hasTestNum = 0;
    int maxNum = 0;
//    错误单词的列表
    public Queue<String> wrongWords = new LinkedList<>();

    Word temp = DataSupport.findLast(Word.class);
    private int maxSize = temp.getId(),
            answer = 0;
    public Random random = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_test);

        initComponent();
        Intent intent = getIntent();
        maxNum = intent.getIntExtra("maxNum",0);
        maxTestNum.setText("总词数: "+ maxNum);
        passed.setText("已通过: "+ passedNum);

        getOptions();
        setClick();

    }

    private void initComponent(){
        option1 = findViewById(R.id.optionA);
        option2 = findViewById(R.id.optionB);
        option3 = findViewById(R.id.optionC);
        option4 = findViewById(R.id.optionD);
        quest = findViewById(R.id.question);
        maxTestNum = findViewById(R.id.under_tested);
        passed = findViewById(R.id.has_passed);
        previous = findViewById(R.id.previous);
    }

//    获得每次的四个选项
    private void getOptions(){
        String temp;
//        由随机数来决定下一组单词是没做过的还是做错过的,当错题队列的长度达到10时,下一个必为错题.
        if ((Math.pow(random.nextInt(10),2)) < Math.pow(wrongWords.size(),2) || hasTestNum == maxNum){
            String[] list = wrongWords.poll().split(",");
            for (int i=0;i<list.length-1;i++){
                num[i] = Integer.parseInt(list[i]);
            }
            answer = Integer.parseInt(list[list.length-1]);
            Toast.makeText(this,"这次选的是错题",Toast.LENGTH_LONG).show();
        }else {
            for (int i=0;i<4;i++){
                int t = random.nextInt(maxSize) + 1;
                temp = null;
                while (isInArray(num,t) || temp == null){
                    t = random.nextInt(maxSize) + 1;
                    try {
                        temp = getDBData(t).getInterpretation();
                    } catch (NullPointerException e) {
                        temp = null;
                    }
                }
                num[i] = t;
            }
            answer = random.nextInt(4);
            hasTestNum++;
        }
        quest.setText(getDBData(num[answer]).getWord());

        A = getDBData(num[0]);
        option1.setText(A.getInterpretation());
        B = getDBData(num[1]);
        option2.setText(B.getInterpretation());
        C = getDBData(num[2]);
        option3.setText(C.getInterpretation());
        D = getDBData(num[3]);
        option4.setText(D.getInterpretation());
    }

//    通过getOption获得的随机数组从数据库取得相应的Word对象
    public Word getDBData(int i){
        temp = DataSupport.find(Word.class,i);
        return temp;
    }

//    判断随机的序号是否已在选项中出现
    private boolean isInArray(int[] array,int num){
        for (int value : array) {
            if (value == num) {
                return true;
            }
        }
        return false;
    }

//    判断用户所选择的选项是否为正确答案
    private boolean isQuest(Word word){
        return word.getId() == num[answer];
    }

//    刷新背景设置,并跳转到下一题
    private void setBackground(final View view, final boolean state){
        if (state){
            view.setBackground(ContextCompat.getDrawable(this,R.drawable.right_style));
        }else{
            view.setBackground(ContextCompat.getDrawable(this,R.drawable.wrong_style));
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackground(ContextCompat.getDrawable(view.getContext(),R.drawable.answer_style));
                if (!state){
                    wrongWords.offer(addWrongProblem());
//                    WrWord word = new WrWord();
//                    String[] words = wrongWords.poll().split(",");
//                    word.setWword(words[0]);
//                    word.setInterpretation(words[1]);
//                    word.save();//保存数据到数据库中
//                    Log.d(TAG,words.toString());
                }else {
                    passedNum++;
                    passed.setText("已通过: " + (passedNum));
                }
                String test = quest.getText().toString();
                previous.setText(quest.getText().toString());
                finishTest(view);
            }
        }, 1000);
    }

//    单词测试完后触发
    private void finishTest(View view){
        if (passedNum == maxNum){
            AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
            dialog.setTitle("测试完啦");
            dialog.setTitle("恭喜你完成了全部的测试,是否选择再测试20个?");
            dialog.setPositiveButton("再来20个", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    maxNum += 20;
                    maxTestNum.setText("总词数: "+ maxNum);
                    getOptions();
                }
            });
            dialog.setNegativeButton("返回主界面", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }else {
            getOptions();
        }
    }

//    设置点击事件的监听器
    private void setClick(){
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackground(v,isQuest(A));
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackground(v,isQuest(B));
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackground(v,isQuest(C));
            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackground(v,isQuest(D));
            }
        });
    }

//    将错误单词记录在一个ArrayList中
    private String addWrongProblem(){
        String temp = "";
        for (int i : num){
            temp = temp + i +",";
        }
        temp = temp + answer;

        return temp;
    }

    public void backMain(View view) {
        finish();
    }
}