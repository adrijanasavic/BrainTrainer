package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButen;
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions;
    TextView schoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gamelayout;

    public void playAgain(View view) {
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText( "30s" );
        schoreTextView.setText( Integer.toString( score ) + "/" + Integer.toString( numberOfQuestions ) );

        newQuestion();
        playAgainButton.setVisibility( View.INVISIBLE );
        resultTextView.setText( "" );


        new CountDownTimer( 30100, 1000 ) {

            @Override
            public void onTick(long l) {
                timerTextView.setText( String.valueOf( l / 1000 ) + "s" );
            }

            @Override
            public void onFinish() {
                resultTextView.setText( "Done!" );
                playAgainButton.setVisibility( View.VISIBLE );
            }
        }.start();
    }

    public void chooseAnswer(View view) {
        if (Integer.toString( locationOfCorrectAnswer ).equals( view.getTag().toString() )) {
            resultTextView.setText( "Correct :)" );
            score++;
        } else {
            resultTextView.setText( "Wrond :(" );
        }
        numberOfQuestions++;
        schoreTextView.setText( Integer.toString( score ) + "/" + Integer.toString( numberOfQuestions ) );

        newQuestion();
    }

    public void start(View view) {
        goButen.setVisibility( View.INVISIBLE );
        gamelayout.setVisibility( View.VISIBLE );

        playAgain( findViewById( R.id.timeTextView ) );

    }

    public void newQuestion() {
        Random random = new Random();

        int a = random.nextInt( 21 );
        int b = random.nextInt( 21 );

        sumTextView.setText( Integer.toString( a ) + " + " + Integer.toString( b ) );

        locationOfCorrectAnswer = random.nextInt( 4 );

        answer.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add( a + b );
            } else {
                int wrongAnswer = random.nextInt( 41 );
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt( 41 );
                }
                answer.add( wrongAnswer );
            }

        }

        button0.setText( Integer.toString( answer.get( 0 ) ) );
        button1.setText( Integer.toString( answer.get( 1 ) ) );
        button2.setText( Integer.toString( answer.get( 2 ) ) );
        button3.setText( Integer.toString( answer.get( 3 ) ) );

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        sumTextView = findViewById( R.id.sumTextView );
        button0 = findViewById( R.id.button0 );
        button1 = findViewById( R.id.button1 );
        button2 = findViewById( R.id.button2 );
        button3 = findViewById( R.id.button3 );

        resultTextView = findViewById( R.id.resultTextView );
        schoreTextView = findViewById( R.id.scoreTextView );
        timerTextView = findViewById( R.id.timeTextView );
        playAgainButton = findViewById( R.id.playAgainButton );
        gamelayout = findViewById( R.id.gameLayout );

        goButen = findViewById( R.id.goButton );
        goButen.setVisibility( View.VISIBLE );

        gamelayout.setVisibility( View.INVISIBLE );
    }
}
