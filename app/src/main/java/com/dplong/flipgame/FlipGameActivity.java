package com.dplong.flipgame;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.dplong.flipgame.Index;
import com.dplong.flipgame.ItemGame;

import java.util.ArrayList;
import java.util.Random;

public class FlipGameActivity extends AppCompatActivity {

    private Button btnReset, btnStatus;
    private Chronometer chronometer;
    private ItemGame[][] matrixGame = new ItemGame[7][6];

    private Random random;
    //private String[] simple = {"🎄", "🎐", "🎁", "🎠", "🎭", "🧶", "🥋", "🔔", "🧾", "📝", "🖊", "📆", "🎿", "📨", "\uD83D\uDE0D", "⚔", "📦"};
    private String[] simple = {"🐵","🦁","🦝","🐭","🐨","🐴","🦄","🐔","🐲","🦓","🦊","🐱","🙊","🦢","🦚","🦋","🐌","🐝","🦗","🐞"};
    private ArrayList<Index> arrayIndex = new ArrayList<>();
    private int countOn = 0;
    private String inValue = "";
    private Index indexA = new Index();
    //private MediaPlayer mediaPlayer;
    private int countdown, width, height;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_flip_game);
        initGame();
        initActionGame();
        btnReset = findViewById(R.id.btn_reset);
        btnStatus = findViewById(R.id.btn_status);
        chronometer = findViewById(R.id.chronometer);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeStatusGame();
            }
        });
        resetGame();
        reEnableGamePlay(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*@Override
    public void onPause() {
        super.onPause();
        //mediaPlayer.release();
    }*/

    private void initGame() {
        width = 5;
        height = 6;
        matrixGame[1][1] = new ItemGame((Button) findViewById(R.id.btn_1x1));
        matrixGame[1][2] = new ItemGame((Button) findViewById(R.id.btn_1x2));
        matrixGame[1][3] = new ItemGame((Button) findViewById(R.id.btn_1x3));
        matrixGame[1][4] = new ItemGame((Button) findViewById(R.id.btn_1x4));
        matrixGame[1][5] = new ItemGame((Button) findViewById(R.id.btn_1x5));
        matrixGame[2][1] = new ItemGame((Button) findViewById(R.id.btn_2x1));
        matrixGame[2][2] = new ItemGame((Button) findViewById(R.id.btn_2x2));
        matrixGame[2][3] = new ItemGame((Button) findViewById(R.id.btn_2x3));
        matrixGame[2][4] = new ItemGame((Button) findViewById(R.id.btn_2x4));
        matrixGame[2][5] = new ItemGame((Button) findViewById(R.id.btn_2x5));
        matrixGame[3][1] = new ItemGame((Button) findViewById(R.id.btn_3x1));
        matrixGame[3][2] = new ItemGame((Button) findViewById(R.id.btn_3x2));
        matrixGame[3][3] = new ItemGame((Button) findViewById(R.id.btn_3x3));
        matrixGame[3][4] = new ItemGame((Button) findViewById(R.id.btn_3x4));
        matrixGame[3][5] = new ItemGame((Button) findViewById(R.id.btn_3x5));
        matrixGame[4][1] = new ItemGame((Button) findViewById(R.id.btn_4x1));
        matrixGame[4][2] = new ItemGame((Button) findViewById(R.id.btn_4x2));
        matrixGame[4][3] = new ItemGame((Button) findViewById(R.id.btn_4x3));
        matrixGame[4][4] = new ItemGame((Button) findViewById(R.id.btn_4x4));
        matrixGame[4][5] = new ItemGame((Button) findViewById(R.id.btn_4x5));
        matrixGame[5][1] = new ItemGame((Button) findViewById(R.id.btn_5x1));
        matrixGame[5][2] = new ItemGame((Button) findViewById(R.id.btn_5x2));
        matrixGame[5][3] = new ItemGame((Button) findViewById(R.id.btn_5x3));
        matrixGame[5][4] = new ItemGame((Button) findViewById(R.id.btn_5x4));
        matrixGame[5][5] = new ItemGame((Button) findViewById(R.id.btn_5x5));
        matrixGame[6][1] = new ItemGame((Button) findViewById(R.id.btn_6x1));
        matrixGame[6][2] = new ItemGame((Button) findViewById(R.id.btn_6x2));
        matrixGame[6][3] = new ItemGame((Button) findViewById(R.id.btn_6x3));
        matrixGame[6][4] = new ItemGame((Button) findViewById(R.id.btn_6x4));
        matrixGame[6][5] = new ItemGame((Button) findViewById(R.id.btn_6x5));
    }

    private void changeStatusGame(){
        if (btnStatus.getText().equals("Bắt đầu") || btnStatus.getText().equals("Chơi lại")){
            resetGame();
            reEnableGamePlay(true);
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
            btnStatus.setText("Tạm dừng");
            return;
        }
        if (btnStatus.getText().equals("Tạm dừng")){
            reEnableGamePlay(false);
            chronometer.stop();
            btnStatus.setText("Tiếp tục");
            return;
        }
        if (btnStatus.getText().equals("Tiếp tục")){
            reEnableGamePlay(true);
            chronometer.start();
            btnStatus.setText("Tạm dừng");
            return;
        }
    }

    private void initActionGame() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 5; j++) {
                matrixGame[i][j].getBtn_ixj().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Handler gamePlayhandler = new Handler(Looper.myLooper());
                       /* //------------------------------------------------------------------------------
                        mediaPlayer.start();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mediaPlayer.stop();
                            }
                        }, 100);*/
                        //------------------------------------------------------------------------------
                        reEnableGamePlay(false);
                        final Button inAction = (Button) view;
                        int onX, onY = 0;
                        for (onX = 1; onX <= 6; onX++) {
                            for (onY = 1; onY <= 5; onY++) {
                                if (matrixGame[onX][onY].getBtn_ixj().getId() == inAction.getId()) {
                                    break;
                                }
                            }
                            if (onY <= 5) {
                                break;
                            }
                        }
                        countOn++;
                        matrixGame[onX][onY].getBtn_ixj().setText(matrixGame[onX][onY].getValue());
                        matrixGame[onX][onY].getBtn_ixj().setBackgroundColor(Color.parseColor("#FFEB3B"));
                        final Index indexB = new Index(onX, onY);
                        if (countOn == 1) {
                            indexA.x = indexB.x;
                            indexA.y = indexB.y;
                            inValue = matrixGame[indexB.x][indexB.y].getValue();
                            reEnableGamePlay(true);
                            return;
                        }
                        if (indexA.equals(indexB)){     // block double click
                            countOn = 1;
                            reEnableGamePlay(true);
                            return;
                        }
                        gamePlayhandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                // Do something after 1s = 1000ms
                                if (inValue.equals(matrixGame[indexB.x][indexB.y].getValue())) {
                                    inAction.setVisibility(View.INVISIBLE);
                                    matrixGame[indexA.x][indexA.y].getBtn_ixj().setVisibility(View.INVISIBLE);
                                    countdown -= 2;
                                    if (countdown <= 0){
                                        chronometer.stop();
                                        btnStatus.setText("Chơi lại");
                                        btnReset.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    matrixGame[indexB.x][indexB.y].getBtn_ixj().setText("\uD83D\uDD78");
                                    matrixGame[indexB.x][indexB.y].getBtn_ixj().setBackgroundColor(Color.parseColor("#00FF0A"));

                                    matrixGame[indexA.x][indexA.y].getBtn_ixj().setText("\uD83D\uDD78");
                                    matrixGame[indexA.x][indexA.y].getBtn_ixj().setBackgroundColor(Color.parseColor("#00FF0A"));
                                }
                                indexA.Reset();
                                countOn = 0;
                                inValue = "";
                                reEnableGamePlay(true);
                            }
                        }, 400);
                    }
                });
            }
        }
    }

    private void resetGame() {
        btnReset.setVisibility(View.GONE);
        random = new Random();
        arrayIndex.clear();
        int i, j;
        for (i = 1; i <= height; i++) {
            for (j = 1; j <= width; j++) {
                arrayIndex.add((new Index(i, j)));
                matrixGame[i][j].getBtn_ixj().setVisibility(View.VISIBLE);
                matrixGame[i][j].getBtn_ixj().setText("\uD83D\uDD78");
                matrixGame[i][j].getBtn_ixj().setBackgroundColor(Color.parseColor("#00FF0A"));
            }
        }
        i = 0;
        j = 0;
        while (arrayIndex.size() > 1) {
            j++;
            int position = random.nextInt(arrayIndex.size() - 1);
            matrixGame[arrayIndex.get(position).x][arrayIndex.get(position).y].setValue(simple[i]);
            //matrixGame[arrayIndex.get(position).x][arrayIndex.get(position).y].getBtn_ixj().setText(simple[i]);
            arrayIndex.remove(position);
            if (j == 2) {
                i++;
                j = 0;
            }
        }
        matrixGame[arrayIndex.get(0).x][arrayIndex.get(0).y].setValue(simple[i]);
        countdown = width*height;
    }

    private void reEnableGamePlay(Boolean isEnable){
        int i,j;
        for (i = 1; i <= height; i++) {
            for (j = 1; j <= width; j++) {
                arrayIndex.add((new Index(i, j)));
                matrixGame[i][j].getBtn_ixj().setEnabled(isEnable);
            }
        }
    }
}
