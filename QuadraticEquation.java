package fragments.Math;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myitschoolproject.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class QuadraticEquation extends AppCompatActivity {
    EditText et_a;
    EditText et_b;
    EditText et_c;
    TextView tv_x1;
    TextView tv_x2;
    Button btn_solve;
    Button btn_exit;
    public double D;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadratic_equation);
        et_a = findViewById(R.id.et_a);
        et_b = findViewById(R.id.et_b);
        et_c = findViewById(R.id.et_c);
        tv_x1 = findViewById(R.id.tv_x1);
        tv_x2 = findViewById(R.id.tv_x2);
        btn_solve = findViewById(R.id.btn_solve);
        btn_exit = findViewById(R.id.btn_exit);
        btn_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_x1.setVisibility(View.INVISIBLE);
                tv_x2.setVisibility(View.INVISIBLE);
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);
                String a_s = et_a.getText().toString();
                double a = Double.parseDouble(a_s);
                String b_s = et_b.getText().toString();
                double b = Double.parseDouble(b_s);
                String c_s = et_c.getText().toString();
                double c = Double.parseDouble(c_s);
                D = b*b-4*a*c;
                D=Math.sqrt(D);
                if(a==0&&b==0&&c==0){
                    tv_x1.setText("Х принадлежит любому множеству чисел");
                    tv_x1.setVisibility(View.VISIBLE);
                }else{
                    if (D>0){
                        double result1 = (-b-D)/2*a;
                        double result2 = (-b+D)/2*a;
                        tv_x1.setText("X1:"+df.format(result1)+"");
                        tv_x2.setText("X2:"+df.format(result2)+"");
                        tv_x1.setVisibility(View.VISIBLE);
                        tv_x2.setVisibility(View.VISIBLE);
                    }else if(D==0){
                        double result = -b/2*a;
                        tv_x1.setText("X:"+df.format(result)+"");
                        tv_x1.setVisibility(View.VISIBLE);
                    }else {
                        tv_x1.setText("Не существует решений данного уравнения");
                        tv_x1.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}