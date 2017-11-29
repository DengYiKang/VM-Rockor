package com.kongqw.kqwrockerdemo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.TextView;


import com.kongqw.rockerlibrary.view.RockerView;



public class MainActivity extends Activity implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener  {

    private TextView angle_log;
    private TextView diraction_log;
    private TextView length_log;
    private MyVideo videoView1;
    private String PATH_URL = "http://192.168.1.254:8090/?action=stream";

    private void initVideoView() {
        // 设置屏幕常亮

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        videoView1 = (MyVideo) findViewById(R.id.videoView1);
        videoView1.setVideoPath(PATH_URL);
        videoView1.setMediaController(new MediaController(this));
        videoView1.requestFocus();
        videoView1.start();
        MediaController mc = new MediaController(this);
        mc.setVisibility(View.INVISIBLE);
        videoView1.setMediaController(mc);
    } //播放错误



    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
    public void Rocket(){
        angle_log = (TextView) findViewById(R.id.angle_text);
        diraction_log = (TextView) findViewById(R.id.diraction_text);
        length_log = (TextView) findViewById(R.id.length_text);


        RockerView rockerViewRight = (RockerView) findViewById(R.id.rockerView);
        if (rockerViewRight != null) {
            rockerViewRight.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
                @Override
                public void onStart() {
                    angle_log.setText(null);
                }

                @Override
                public void angle(double angle) {
                    angle_log.setText("摇动角度 : " + angle);
                }

                @Override
                public void location(double length ) {
                    MainActivity.this.length_log.setText("长度: " + length);
                }

                @Override
                public void onFinish() {
                    angle_log.setText(null);
                    length_log.setText(null);
                }
            });
            rockerViewRight.setOnShakeListener(RockerView.DirectionMode.DIRECTION_8, new RockerView.OnShakeListener() {
                @Override
                public void onStart() {

                }

                @Override
                public void direction(RockerView.Direction direction) {
                    diraction_log.setText("方向" + getDirection(direction));
                }

                @Override
                public void onFinish() {

                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Rocket();
        initVideoView();


    }

    private String getDirection(RockerView.Direction direction) {
        String message = null;
        switch (direction) {
            case DIRECTION_LEFT:
                message = "左";
                break;
            case DIRECTION_RIGHT:
                message = "右";
                break;
            case DIRECTION_UP:
                message = "上";
                break;
            case DIRECTION_DOWN:
                message = "下";
                break;
            case DIRECTION_UP_LEFT:
                message = "左上";
                break;
            case DIRECTION_UP_RIGHT:
                message = "右上";
                break;
            case DIRECTION_DOWN_LEFT:
                message = "左下";
                break;
            case DIRECTION_DOWN_RIGHT:
                message = "右下";
                break;
            default:
                break;
        }
        return message;
    }
}
