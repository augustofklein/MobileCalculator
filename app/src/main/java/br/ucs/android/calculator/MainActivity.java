package br.ucs.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    private float acumulator;
    private TextView textResult;
    private Boolean flagNewNumber = false;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textResult = findViewById(R.id.textResult);
        initializeDisplay();
    }

    private void initializeDisplay(){
        acumulator = 0;
        textResult.setText("0");
    }

    private String getFromDisplay(){
        return textResult.getText().toString();
    }

    private void showOnDisplay(String text){
        textResult.setText(text);
    }

    private void showOnDisplay(Float number){
        textResult.setText(Float.toString(number));
    }

    private void numPressed(String number){
        if(flagNewNumber){
            showOnDisplay(number);
            flagNewNumber = false;
        }else{
            if("0".equals(getFromDisplay())){
                showOnDisplay(number);
            }else{
                String newNumber = getFromDisplay() + number;
                showOnDisplay(newNumber);
            }
        }
    }

    public void pressNumber(View view){

        Button button = (Button) view;
        String text = button.getText().toString();

        numPressed(text);

    }

    public void pressOperator(View view){
        Button button = (Button) view;
        String text = button.getText().toString();

        returnOperation(text);
    }

    public void pressClear(View view){
        initializeDisplay();
        pendingOperation = "=";
    }

    public void pressBtnDot(View view){
        addFloatingDot();
    }

    private void addFloatingDot(){

        String currentDisplay = getFromDisplay();

        if(flagNewNumber){
            currentDisplay = "0.";
           flagNewNumber = false;
        }else{
            if(currentDisplay.indexOf(".") == -1){
                currentDisplay += ".";
            }
            showOnDisplay(currentDisplay);
        }

    }

    private void returnOperation(String operation){
        if(getFromDisplay().equals("")){
            showOnDisplay("O campo está vazio, digite um valor!");
            showOnDisplay("0");
        }else {
            if (flagNewNumber && !pendingOperation.equals("=")) {
            } else {
                flagNewNumber = true;
                switch (pendingOperation) {
                    case "+":
                        acumulator += Float.parseFloat(getFromDisplay());
                        break;

                    case "-":
                        acumulator -= Float.parseFloat(getFromDisplay());
                        break;

                    case "*":
                        acumulator *= Float.parseFloat(getFromDisplay());
                        break;

                    case "/":
                        acumulator /= Float.parseFloat(getFromDisplay());
                        break;

                    default:
                        acumulator += Float.parseFloat(getFromDisplay());
                        pendingOperation = operation;
                        break;
                }
            }
            showOnDisplay(acumulator);
        }
    }
}