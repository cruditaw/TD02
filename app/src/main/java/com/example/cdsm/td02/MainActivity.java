package com.example.cdsm.td02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Integer minValue;
    private Integer maxValue;

    private EditText minInput;
    private EditText maxInput;

    private TextView messages;

    private boolean validationFailed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        ((Button) findViewById(R.id.btnJeu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickValidation(view);
            }
        });
    }

    private void initView() {
        minInput = ((EditText) findViewById(R.id.editValMin));
        maxInput = ((EditText) findViewById(R.id.editValMax));

        messages = ((TextView) findViewById(R.id.txtMessage));
        validationFailed = true;
    }


    private void onClickValidation(View view) {
        boolean fail = false;
        String min = minInput.getText().toString();
        String max = maxInput.getText().toString();

        if (!min.isEmpty()) {
            System.out.println("---------------------------------------------------- PASSED min empty !");

            if (!max.isEmpty()) {
                System.out.println("---------------------------------------------------- PASSED max empty !");

                if (Integer.parseInt(min) < Integer.parseInt(max)) {
                    System.out.println("---------------------------------------------------- PASSED different !");
                        resetErrorMessage();

                        minValue = Integer.valueOf(minInput.getText().toString());
                        maxValue = Integer.valueOf(maxInput.getText().toString());

                        // intent jeu
                        Intent intent = new Intent(this, Jeu1.class);

                        // add input value to activity parameters
                        intent.putExtra("minVal", minValue);
                        intent.putExtra("maxVal", maxValue);

                        // start activity
                        startActivity(intent);
                        //startActivityForResult(intent, REQ_CODE);

                } else {
                    showErrorMessage("L'interval est incorrect !");
                }

            } else {
                showErrorMessage("Une valeur maximum est requise !");
            }

        } else {
            showErrorMessage("Une valeur minimum est requise !");
        }

    }

    private void resetErrorMessage() {
        messages.setText("");
        messages.setBackgroundColor(getResources().getColor(R.color.colorWhite));
    }

    private void showErrorMessage(String message) {
        messages.setText(message);
        messages.setBackgroundColor(getResources().getColor(R.color.colorWarning));
    }

}
