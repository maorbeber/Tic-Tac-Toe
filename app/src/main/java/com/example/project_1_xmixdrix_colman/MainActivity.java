package com.example.project_1_xmixdrix_colman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public boolean checkWin(int[][] posArr) {
        for (int i = 0; i < posArr.length; i++) {
            for (int j = 0; j < posArr[i].length; j++) {
                if (posArr[i][j] == 1) {
                    if (i == 0 && j == 0) {
                        if ((posArr[i][j + 1] == 1 && posArr[i][j + 2] == 1) ||
                                (posArr[i + 1][j + 1] == 1 && posArr[i + 2][j + 2] == 1) ||
                                (posArr[i + 1][j] == 1 && posArr[i + 2][j] == 1)) {
                            return true;
                        }
                    } else if (i == 0) {
                        if ((j == 2 && (posArr[i + 1][j - 1] == 1 && posArr[i + 2][j - 2] == 1)) ||
                                (posArr[i + 1][j] == 1 && posArr[i + 2][j] == 1)) {
                            return true;
                        }
                    } else if (j == 0 && posArr[i][j + 1] == 1 && posArr[i][j + 2] == 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final boolean[] xPlays = {true};
        final boolean[] end = {false};
        final int[] numOfPlays = {0};
        ImageButton[][] board = new ImageButton[3][3];
        int[][] boardX = new int[3][3];
        int[][] boardO = new int[3][3];
        for (int i = 0; i < boardX.length; i++) {
            for (int j = 0; j < boardX[i].length; j++) {
                boardX[i][j] = 0;
                boardO[i][j] = 0;
            }
        }
        findViewById(R.id.player).setBackgroundResource(R.drawable.xplay);
        String play = "Play again";
        Button replay = findViewById(R.id.replay);
        ImageButton btn0 = (ImageButton) findViewById(R.id.imageButton00);
        ImageButton btn1 = (ImageButton) findViewById(R.id.imageButton01);
        ImageButton btn2 = (ImageButton) findViewById(R.id.imageButton02);
        ImageButton btn3 = (ImageButton) findViewById(R.id.imageButton10);
        ImageButton btn4 = (ImageButton) findViewById(R.id.imageButton11);
        ImageButton btn5 = (ImageButton) findViewById(R.id.imageButton12);
        ImageButton btn6 = (ImageButton) findViewById(R.id.imageButton20);
        ImageButton btn7 = (ImageButton) findViewById(R.id.imageButton21);
        ImageButton btn8 = (ImageButton) findViewById(R.id.imageButton22);
        View.OnClickListener mixDrix = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!end[0]) {
                    if (!(view.getBackground().getConstantState() == getDrawable(R.drawable.x).getConstantState() ||
                            view.getBackground().getConstantState() == getDrawable(R.drawable.o).getConstantState())) {
                        numOfPlays[0]++;
                        if (xPlays[0]) {
                            findViewById(R.id.player).setBackgroundResource(R.drawable.oplay);
                            view.setBackgroundResource(R.drawable.x);
                            for (int i = 0; i < board.length; i++) {
                                for (int j = 0; j < board[i].length; j++) {
                                    if (board[i][j].getId() == view.getId()) {
                                        boardX[i][j] = 1;
                                    }
                                }
                            }
                            if (checkWin(boardX)) {
                                end[0] = true;
                                findViewById(R.id.winner).setBackgroundResource(R.drawable.xwin);
                                replay.setText(play);
                                return;
                            }
                            xPlays[0] = false;
                        } else {
                            findViewById(R.id.player).setBackgroundResource(R.drawable.xplay);
                            view.setBackgroundResource(R.drawable.o);
                            for (int i = 0; i < board.length; i++) {
                                for (int j = 0; j < board[i].length; j++) {
                                    if (board[i][j].getId() == view.getId()) {
                                        boardO[i][j] = 1;
                                    }
                                }
                            }
                            if (checkWin(boardO)) {
                                end[0] = true;
                                findViewById(R.id.winner).setBackgroundResource(R.drawable.owin);
                                replay.setText(play);
                                return;
                            }
                            xPlays[0] = true;
                        }
                        if (numOfPlays[0] == 9) {
                            findViewById(R.id.winner).setBackgroundResource(R.drawable.nowin);
                            end[0] = true;
                            replay.setText(play);
                        }
                    }
                }
            }
        };
        replay.setOnClickListener(view -> {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        });
        btn0.setOnClickListener(mixDrix);
        btn1.setOnClickListener(mixDrix);
        btn2.setOnClickListener(mixDrix);
        btn3.setOnClickListener(mixDrix);
        btn4.setOnClickListener(mixDrix);
        btn5.setOnClickListener(mixDrix);
        btn6.setOnClickListener(mixDrix);
        btn7.setOnClickListener(mixDrix);
        btn8.setOnClickListener(mixDrix);
        board[0][0] = btn0;
        board[0][1] = btn1;
        board[0][2] = btn2;
        board[1][0] = btn3;
        board[1][1] = btn4;
        board[1][2] = btn5;
        board[2][0] = btn6;
        board[2][1] = btn7;
        board[2][2] = btn8;
    }
}