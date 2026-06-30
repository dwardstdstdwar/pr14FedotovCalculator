package com.example.pr14simplecalculatorhomyakov;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnEquals;
    private Button btnClear, btnBackspace, btnDot, btnPercent;
    private Button btnMC, btnMPlus, btnMMinus, btnMR;

    private String currentInput = "0";
    private String operator = "";
    private double firstValue = 0;
    private double memoryValue = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
    }

    private void initViews() {
        tvDisplay = findViewById(R.id.tvDisplay);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnEquals = findViewById(R.id.btnEquals);
        btnPercent = findViewById(R.id.btnPercent);

        btnClear = findViewById(R.id.btnClear);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnDot = findViewById(R.id.btnDot);

        btnMC = findViewById(R.id.btnMC);
        btnMPlus = findViewById(R.id.btnMPlus);
        btnMMinus = findViewById(R.id.btnMMinus);
        btnMR = findViewById(R.id.btnMR);
    }

    private void setListeners() {
        // Цифровые кнопки
        btn0.setOnClickListener(v -> onNumberClick("0"));
        btn1.setOnClickListener(v -> onNumberClick("1"));
        btn2.setOnClickListener(v -> onNumberClick("2"));
        btn3.setOnClickListener(v -> onNumberClick("3"));
        btn4.setOnClickListener(v -> onNumberClick("4"));
        btn5.setOnClickListener(v -> onNumberClick("5"));
        btn6.setOnClickListener(v -> onNumberClick("6"));
        btn7.setOnClickListener(v -> onNumberClick("7"));
        btn8.setOnClickListener(v -> onNumberClick("8"));
        btn9.setOnClickListener(v -> onNumberClick("9"));

        // Кнопки операций
        btnAdd.setOnClickListener(v -> onOperatorClick("+"));
        btnSubtract.setOnClickListener(v -> onOperatorClick("-"));
        btnMultiply.setOnClickListener(v -> onOperatorClick("*"));
        btnDivide.setOnClickListener(v -> onOperatorClick("/"));

        // Кнопка равно
        btnEquals.setOnClickListener(v -> onEqualsClick());

        // Кнопка процента
        btnPercent.setOnClickListener(v -> onPercentClick());

        // Кнопки управления
        btnClear.setOnClickListener(v -> onClearClick());
        btnBackspace.setOnClickListener(v -> onBackspaceClick());
        btnDot.setOnClickListener(v -> onDotClick());

        // Кнопки памяти
        btnMC.setOnClickListener(v -> {
            memoryValue = 0;
        });

        btnMPlus.setOnClickListener(v -> {
            memoryValue += Double.parseDouble(currentInput);
        });

        btnMMinus.setOnClickListener(v -> {
            memoryValue -= Double.parseDouble(currentInput);
        });

        btnMR.setOnClickListener(v -> {
            currentInput = String.valueOf(memoryValue);
            tvDisplay.setText(currentInput);
            isNewInput = true;
        });
    }

    private void onNumberClick(String number) {
        if (isNewInput) {
            currentInput = number;
            isNewInput = false;
        } else {
            if (currentInput.equals("0")) {
                currentInput = number;
            } else {
                currentInput += number;
            }
        }
        tvDisplay.setText(currentInput);
    }

    private void onOperatorClick(String op) {
        if (!operator.isEmpty()) {
            onEqualsClick();
        }
        firstValue = Double.parseDouble(currentInput);
        operator = op;
        isNewInput = true;
    }

    private void onEqualsClick() {
        if (operator.isEmpty()) return;

        double secondValue = Double.parseDouble(currentInput);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = firstValue / secondValue;
                } else {
                    tvDisplay.setText("Ошибка");
                    currentInput = "0";
                    operator = "";
                    isNewInput = true;
                    return;
                }
                break;
        }

        currentInput = String.valueOf(result);
        tvDisplay.setText(currentInput);
        operator = "";
        isNewInput = true;
    }

    private void onPercentClick() {
        double value = Double.parseDouble(currentInput);
        currentInput = String.valueOf(value / 100);
        tvDisplay.setText(currentInput);
    }

    private void onClearClick() {
        currentInput = "0";
        operator = "";
        firstValue = 0;
        isNewInput = true;
        tvDisplay.setText(currentInput);
    }

    private void onBackspaceClick() {
        if (currentInput.length() > 1) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        } else {
            currentInput = "0";
        }
        tvDisplay.setText(currentInput);
    }

    private void onDotClick() {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            tvDisplay.setText(currentInput);
        }
    }
}