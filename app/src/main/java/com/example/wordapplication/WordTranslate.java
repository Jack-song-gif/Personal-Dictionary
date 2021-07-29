package com.example.wordapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wordapplication.Translateapi.TransApi;

import java.util.Map;

public class WordTranslate extends AppCompatActivity {
    //设置appid和securitykey,这两项可以去百度翻译API上申请获得
    private static final String APP_ID = "20210630000876242";
    private static final String SECURITY_KEY = "nmK9vrRtoDkVYkkywgk9";
    private static final String TAG="WordTranslate";
    private TextView to,method,start;
    private EditText Tfrom;
    private Map<String, String> results;
    private PopupMenu popupMenu;
    private String fromDirection = "zh";
    private String  toDirection = "en";
    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_translate);

        initComponent();
    }

    private void initComponent(){
        to = findViewById(R.id.translate_to);
        Tfrom = findViewById(R.id.translate_from);
        method = findViewById(R.id.translate_method);
        start = findViewById(R.id.translate_start);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (APP_ID.equals("20210630000876242") && "nmK9vrRtoDkVYkkywgk9".equals(SECURITY_KEY)){
                    //                开启线程,调用api获得翻译结果
                    new Thread(new httpConnectThread(APP_ID,SECURITY_KEY)).start();
                    Log.d(TAG,"第一条语句");
                }else{
                    Toast.makeText(WordTranslate.this, "未申请到百度翻译API开发者的AppID", Toast.LENGTH_SHORT).show();
                    return;
                }

            }

        });
    }

    private Map<String, String> getResult(String appId, String securityKey) {
        TransApi api = new TransApi(appId,securityKey);
        //获得输入文本框的内容
        query = Tfrom.getText().toString();
        Log.d(TAG, query);
        if (fromDirection.equals("zh")){
            return api.getTransResult(query,"zh","en");
        }else {
            return api.getTransResult(query,"en","zh");
        }
    }

    public class httpConnectThread implements Runnable{
        private String APP_ID;
        private String SECURITY_KEY;

        public httpConnectThread(String APP_ID, String SECURITY_KEY) {
            this.APP_ID = APP_ID;
            this.SECURITY_KEY = SECURITY_KEY;
        }

        @Override
        public void run() {
            results = getResult(APP_ID,SECURITY_KEY);
            final String toText = results.get("dst");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    to.setText(toText);
                }
            });
        }
    }

    public void menuClick(View view){
        popupMenu = new PopupMenu(this,view);
        getMenuInflater().inflate(R.menu.translate_direction_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.en_to_zh:
                        fromDirection = "en";
                        toDirection = "zh";
                        method.setText("当前模式:英译汉");
                        break;
                    case R.id.zh_to_en:
                        fromDirection = "zh";
                        toDirection = "en";
                        method.setText("当前模式:汉译英");
                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
}