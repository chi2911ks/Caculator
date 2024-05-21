package com.cb.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView solution, result;
    MaterialButton button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9,
    button_ac, button_c, button_divide, button_multiply, button_add, button_minus, button_equals, button_dot, button_right_bracket, button_left_bracket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        solution = findViewById(R.id.solution);
        result = findViewById(R.id.result);
        assignId(button_0, R.id.button_0);
        assignId(button_1, R.id.button_1);
        assignId(button_2, R.id.button_2);
        assignId(button_3, R.id.button_3);
        assignId(button_4, R.id.button_4);
        assignId(button_5, R.id.button_5);
        assignId(button_6, R.id.button_6);
        assignId(button_7, R.id.button_7);
        assignId(button_8, R.id.button_8);
        assignId(button_9, R.id.button_9);
        assignId(button_c, R.id.button_c);
        assignId(button_ac, R.id.button_ac);
        assignId(button_divide, R.id.button_divide);
        assignId(button_left_bracket, R.id.button_left_bracket);
        assignId(button_right_bracket, R.id.button_right_bracket);
        assignId(button_add, R.id.button_add);
        assignId(button_minus, R.id.button_minus);
        assignId(button_equals, R.id.button_equals);
        assignId(button_dot, R.id.button_dot);
        assignId(button_multiply, R.id.button_multiply);

    }
    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();
        String dataToCalculator = solution.getText().toString();

        if (btnText.equals("AC")) {
            solution.setText("");
            result.setText("0");
            return;
        }
        if (btnText.equals("=")) {

            String postfix = TestStack.InfixToPostfix(dataToCalculator);
            double r = TestStack.CalculatePostfix(postfix);
            DecimalFormat df = new DecimalFormat("#.###");
            String roundedNumber = df.format(r);
            result.setText(roundedNumber);
            return;
        }
        if (btnText.equals("C")) {
            if (!dataToCalculator.isEmpty()){
            dataToCalculator =  dataToCalculator.substring(0, dataToCalculator.length()-1);}
        }else{
            dataToCalculator =  dataToCalculator+btnText;
        }

        solution.setText(dataToCalculator);

    }
}