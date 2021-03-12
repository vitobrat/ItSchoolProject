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

public class CubicEquation extends AppCompatActivity {
    EditText et_a;
    EditText et_b;
    EditText et_c;
    EditText et_d;
    TextView tv_x1;
    TextView tv_x2;
    TextView tv_x3;
    Button btn_solve;
    Button btn_exit;
    boolean flag=true;

    private static final double TWO_PI = 2.0 * Math.PI;
    private static final double FOUR_PI = 4.0 * Math.PI;


    public int nRoots;
    public double x1;
    public double x2;
    public double x3;



    public void solve(double a,double b,double c,double d){
        if (a == 0.0) {
            tv_x1.setText("Переменная \"а\" не может быть равна 0!!!");
            tv_x1.setVisibility(View.VISIBLE);
            flag=false;
        }else{
            double denom = a;
            a = b/denom;
            b = c/denom;
            c = d/denom;
            double a_over_3 = a / 3.0;
            double Q = (3*b - a*a) / 9.0;
            double Q_CUBE = Q*Q*Q;
            double R = (9*a*b - 27*c - 2*a*a*a) / 54.0;
            double R_SQR = R*R;
            double D = Q_CUBE + R_SQR;

            if (D < 0.0)
            {
                nRoots = 3;
                double theta = Math.acos (R / Math.sqrt (-Q_CUBE));
                double SQRT_Q = Math.sqrt (-Q);
                x1 = 2.0 * SQRT_Q * Math.cos (theta/3.0) - a_over_3;
                x2 = 2.0 * SQRT_Q * Math.cos ((theta+TWO_PI)/3.0) - a_over_3;
                x3 = 2.0 * SQRT_Q * Math.cos ((theta+FOUR_PI)/3.0) - a_over_3;
                sortRoots();
            }
            else if (D > 0.0)
            {
                nRoots = 1;
                double SQRT_D = Math.sqrt (D);
                double S = Math.cbrt (R + SQRT_D);
                double T = Math.cbrt (R - SQRT_D);
                x1 = (S + T) - a_over_3;
//            x2 = Double.NaN;
//            x3 = Double.NaN;
            }
            else
            {
                nRoots = 3;
                double CBRT_R = Math.cbrt (R);
                x1 = 2*CBRT_R - a_over_3;
                x2 = x3 = CBRT_R - a_over_3;
                sortRoots();
            }
        }

    }


    private void sortRoots()
    {
        if (x1 < x2) {
            double tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
        if (x2 < x3) {
            double tmp = x2;
            x2 = x3;
            x3 = tmp;
        }
        if (x1 < x2) {
            double tmp = x1;
            x1 = x2;
            x2 = tmp;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cubic_equation);
        et_a = findViewById(R.id.et_a);
        et_b = findViewById(R.id.et_b);
        et_c = findViewById(R.id.et_c);
        et_d = findViewById(R.id.et_d);
        tv_x1 = findViewById(R.id.tv_x1);
        tv_x2 = findViewById(R.id.tv_x2);
        tv_x3 = findViewById(R.id.tv_x3);
        btn_solve = findViewById(R.id.btn_solve);
        btn_exit = findViewById(R.id.btn_exit);

        btn_solve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_x1.setVisibility(View.INVISIBLE);
                tv_x2.setVisibility(View.INVISIBLE);
                tv_x3.setVisibility(View.INVISIBLE);
                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);
                String a_s = et_a.getText().toString();
                double a = Double.parseDouble(a_s);
                String b_s = et_b.getText().toString();
                double b = Double.parseDouble(b_s);
                String c_s = et_c.getText().toString();
                double c = Double.parseDouble(c_s);
                String d_s = et_d.getText().toString();
                double d = Double.parseDouble(d_s);
                if(c_s==null || b_s==null || a_s==null || d_s==null) {
                    tv_x1.setText("Введите значение для всех трех величин!");
                    tv_x1.setVisibility(View.VISIBLE);
                }else{
                    CubicEquation cubicEquation = new CubicEquation();
                    cubicEquation.solve(a,b,c,d);
                    if (flag){
                        tv_x1.setText("x1 = " + df.format(cubicEquation.x1));
                        tv_x1.setVisibility(View.VISIBLE);
                        if (cubicEquation.nRoots == 3) {
                            tv_x2.setText("x2 = " + df.format(cubicEquation.x2));
                            tv_x3.setText("x3 = " + df.format(cubicEquation.x3));
                            tv_x2.setVisibility(View.VISIBLE);
                            tv_x3.setVisibility(View.VISIBLE);
                        }
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