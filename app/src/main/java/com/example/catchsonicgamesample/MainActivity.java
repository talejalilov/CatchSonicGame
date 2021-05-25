package com.example.catchsonicgamesample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView time, scoreView;
    ImageView imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8, imageView9;
    ImageView[] imageArrays;

    Handler handler;
    Runnable runnable;

    int score;
    int userScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.timeViewId);
        scoreView = findViewById(R.id.scoreViewId);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArrays = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        hideImage();


        score = 0;

        new CountDownTimer(10000, 500) {


            @Override
            public void onTick(long millisUntilFinished) {
                time.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {

                time.setText("Time off");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArrays){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game");
                alert.setMessage("Your Score " + score + "\n \n Do you want to repeat?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //restart
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(MainActivity.this,"Game over",Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();


    }

    public void inCrease(View v) {
        score += 10;

        scoreView.setText("Score: " + score);


    }

    public void hideImage(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                for(ImageView image : imageArrays){
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int i = random.nextInt(9);
                imageArrays[i].setVisibility(View.VISIBLE);

                handler.postDelayed(this,1000);


            }
        };
        handler.post(runnable);


    }


}