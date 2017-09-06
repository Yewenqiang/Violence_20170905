package com.violence.ywq.storage2_file;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText ed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = (EditText) findViewById(R.id.ed);
        load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
    }

    private void save() {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(openFileOutput("file.text", Context.MODE_PRIVATE));
            bos.write(ed.getText().toString().getBytes());
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //加载数据
    private void load() {
        BufferedInputStream bis = null;
        try {
            bis = new BufferedInputStream(openFileInput("file.text"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int hasRead = bis.read(buffer);
                if (hasRead < 0) {
                    break;
                }
                baos.write(buffer, 0, hasRead);//读多少写多少
            }
            ed.setText(baos.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
