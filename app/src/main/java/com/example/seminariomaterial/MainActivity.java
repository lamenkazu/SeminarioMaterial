package com.example.seminariomaterial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textview.MaterialTextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    LinearProgressIndicator linearIndicator;
    CircularProgressIndicator circularIndicator;
    MaterialTextView lblPercent;
    MaterialButton btnProgress;
    Handler activityHandler = new Handler();

    private void initComponents(){
        linearIndicator = findViewById(R.id.linear_indicator);
        circularIndicator = findViewById(R.id.circular_indicator);
        lblPercent = findViewById(R.id.lbl_percent);
        btnProgress = findViewById(R.id.btn_progress);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        setButtonProgress();


        //Efeito Transição Indeterminate para Determinate
        activityHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setTimerProgress();
            }
        }, 3000);
    }

    private void setButtonProgress(){
        btnProgress.setOnClickListener(new View.OnClickListener() {
            int progress;
            @Override
            public void onClick(View v) {

                if(progress == 0){
                    linearIndicator.setIndeterminate(false);
                    circularIndicator.setIndeterminate(false);
                }

                progress += 10;
                progress = progress > 100? 100: progress;

                linearIndicator.setProgressCompat(progress, true);
                circularIndicator.setProgressCompat(progress, true);
                lblPercent.setText(progress + " %");





            }
        });

    }

    private void setTimerProgress(){
        linearIndicator.setIndeterminate(false);
        circularIndicator.setIndeterminate(false);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int progress;
            @Override
            public void run() {

                progress = progress + 1;

                linearIndicator.setProgressCompat(progress, true);
                circularIndicator.setProgressCompat(progress, true);

                activityHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        lblPercent.setText(progress + " %");
                    }
                });


                if(progress == 100){
                    timer.cancel();
                }
            }
        };
        timer.schedule(task, 100, 15);
    }
}