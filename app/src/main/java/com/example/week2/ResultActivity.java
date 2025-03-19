package com.example.week2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ResultActivity extends AppCompatActivity {
    TextView txt_result;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt_result = findViewById(R.id.txt_result);
        btn_back = findViewById(R.id.btn_back);

        Intent myintent = getIntent();
        Bundle mybundle = myintent.getBundleExtra("mypackage");
        Double a = mybundle.getDouble("a");
        Double b = mybundle.getDouble("b");
        Double c = mybundle.getDouble("c");
        DecimalFormat dcf = new DecimalFormat("0.00");

        if (a == 0) {
            if (b != 0) {
                double x = -c / b;
                txt_result.setText("Phương trình trở thành bậc nhất: x = " + dcf.format(x));
            } else {
                if (c == 0) {
                    txt_result.setText("Vô số nghiệm");
                } else {
                    txt_result.setText("Phương trình vô nghiệm");
                }
            }
        } else {
            double delta = b * b - 4 * a * c;
            if (delta < 0) {
                txt_result.setText("Phương trình vô nghiệm");
            } else if (delta == 0) {
                double x = -b / (2.0 * a);
                txt_result.setText("Phương trình có nghiệm kép: x1 = x2 = " + dcf.format(x));
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2.0 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2.0 * a);
                txt_result.setText("Phương trình có 2 nghiệm phân biệt\n x1 = " + dcf.format(x1) + " và x2 = " + dcf.format(x2));
            }
        }

        btn_back.setOnClickListener(v -> finish());
    }
}
