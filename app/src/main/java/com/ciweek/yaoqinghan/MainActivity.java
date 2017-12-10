package com.ciweek.yaoqinghan;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private FrameLayout pictureView;
    private TextView name;
    private Handler mHandler = new Handler();
    private List<String> namelist;
    final Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initview();
        initData();
    }

    private void initData() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i =0;i<namelist.size();i++) {
                    Log.e("第"+i+"次","");
                    name.setText(namelist.get(i));

                    if(i<10){
                        huizhi(i,namelist.get(i));
                        handler.postDelayed(this, 15);
                    }


                }


            }
        });




    }



    private void huizhi(final int i, final String s) {
        if(i==namelist.size()-1){
            Toast.makeText(MainActivity.this,"这是最后一张",Toast.LENGTH_SHORT).show();
        }

        pictureView.setDrawingCacheEnabled(true);
        pictureView.buildDrawingCache();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final Bitmap bmp = pictureView.getDrawingCache(); // 获取图片
                savePicture(bmp, "test"+i+"名字是："+s+".jpg");// 保存图片
                pictureView.destroyDrawingCache(); // 保存过后释放资源
                name.setText("");
            }
        }, 1000);




    }

    private void initview() {
        name = (TextView) findViewById(R.id.name);
        pictureView = (FrameLayout) findViewById(R.id.picture);
        namelist = new ArrayList<>();
        namelist.add("张三");
        namelist.add("李四");
        namelist.add("王五");
        namelist.add("王1");
        namelist.add("王2");
        namelist.add("王3");namelist.add("王4");
        namelist.add("王4");
        namelist.add("王5");namelist.add("王6");







    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        name.invalidate();
        return super.onTouchEvent(event);

    }

    public void savePicture(Bitmap bm, String fileName) {

        if (bm == null) {
            Toast.makeText(this, "savePicture null !", Toast.LENGTH_SHORT).show();
            return;
        }
        File foder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/111111111111111111");
        Log.e("11","="+foder);

        if (!foder.exists()) {
            foder.mkdirs();
        }
        File myCaptureFile = new File(foder, fileName);
        try {
            if (!myCaptureFile.exists()) {
                myCaptureFile.createNewFile();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
            bm.compress(Bitmap.CompressFormat.JPEG, 90, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "保存成功!", Toast.LENGTH_SHORT).show();

    }
}

