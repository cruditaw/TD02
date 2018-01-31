package com.example.cdsm.td02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class Jeu1 extends AppCompatActivity {

    private Integer vies;
    private EditText editValue;
    private TextView scoreValue;
    private TextView messagesJeu1;
    private Integer minValue;
    private Integer maxValue;
    private int randomed;
    private TextView txtEditJeu1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu1);

        initGame();

        ((Button)findViewById(R.id.btnGuess)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onGuessClick();
            }
        });
    }

    private void initGame() {
        editValue = ((EditText) findViewById(R.id.editValJeu1));
        scoreValue = ((TextView) findViewById(R.id.txtScoreValJeu1));
        messagesJeu1 = ((TextView) findViewById(R.id.txtMessageJeu1));
        txtEditJeu1 = ((TextView) findViewById(R.id.txtEditJeu1));
        editValue.setEnabled(true);
        ((Button)findViewById(R.id.btnGuess)).setEnabled(true);

        vies = 10;
        ((TextView) findViewById(R.id.txtScoreValJeu1)).setText(String.valueOf(vies));


        Intent intent = getIntent();
        minValue = intent.getIntExtra("minVal", -1);
        maxValue = intent.getIntExtra("maxVal", -1);

        Random myRandomInt = new Random();

        randomed = myRandomInt.nextInt(maxValue);

        if (randomed < minValue || randomed > maxValue) {
            while (randomed < minValue || randomed > maxValue) {

                System.out.println("random value : " + randomed);
                randomed = myRandomInt.nextInt();
                System.out.println("Re-applied random value : " + randomed);
            }
        }
    }

    private void onAccueilClick() {
        Intent intent = new Intent();
        intent.putExtra("jeu1Score", vies);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void onGuessClick() {

        // recup valeur
        int v = Integer.parseInt(editValue.getText().toString());

        // affichage valeur recup
        txtEditJeu1.setText("Votre valeur : " + String.valueOf(v));

        // reset champs
        editValue.setText("");

        // check superieur
        if (v > randomed) {
            showErrorMessage("Plus petit !");
            vies -= 1;
            ((TextView) findViewById(R.id.txtScoreValJeu1)).setText(String.valueOf(vies));
        }

        // check inferieur
        if (v < randomed) {
            showErrorMessage("Plus grand !");
            vies -= 1;
            ((TextView) findViewById(R.id.txtScoreValJeu1)).setText(String.valueOf(vies));
        }


        if (v == randomed) {
            showErrorMessage("MOUHAHAHAHAHA!!! U WIN!! ");
            editValue.setEnabled(false);
            ((Button)findViewById(R.id.btnGuess)).setEnabled(false);
        }

        if (vies <=0) {
            showErrorMessage("MOUHAHAHAHAHA!!! U LOOOOOOSE!! ");
            editValue.setEnabled(false);
            ((Button)findViewById(R.id.btnGuess)).setEnabled(false);

        }
    }

    private void resetErrorMessage() {
        messagesJeu1.setText("");
        messagesJeu1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    private void showErrorMessage(String message) {
        messagesJeu1.setText(message);
        messagesJeu1.setBackgroundColor(getResources().getColor(R.color.colorWarning));
    }
}
