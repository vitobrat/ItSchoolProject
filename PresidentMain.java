package fragments.GamesPresident;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myitschoolproject.R;

public class PresidentMain extends AppCompatActivity {

    public static Character manager;
    public static Story story;
    EditText et_name;
    public static int flag=1;
    Button btn1;
    Button btn2;
    Button btn3;

    public void end(){
        btn1.setText("Завершить");
        btn2.setVisibility(View.INVISIBLE);
        btn3.setVisibility(View.INVISIBLE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_president_main);

        TextView text = findViewById(R.id.text);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        et_name = findViewById(R.id.et_name);
        text.setText("Добро пожайловать, президент!\n"
                + "Я буду вашим советником на протяжении вашего президенства и буду доносить всю актуальную информацию.\n"
                + "Вы только что вступили в свою новую должность. Но нет времени радоваться, нужно развивать страну\n"
                + "В дальнейшем вам нужно будет принять множество непростых решений. Эти решения будут влиять на ваши показатели. Такие как: деньги, поддержка власти и уровень диктатуры в стране.\n"
                + "Как вас зовут?");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_name.setVisibility(View.INVISIBLE);
                flag=2;
                manager = new Character(et_name.getText().toString());
                story = new Story();
                btn2.setVisibility(View.VISIBLE);
                btn1.setText("1");

                manager.M+=story.current_situation.dM;
                manager.S+=story.current_situation.dS;
                manager.D+=story.current_situation.dD;
                text.setText("=====\nДеньги:" + manager.M + "млн.\nПоддержка населения:"
                        + manager.S + "%\n  Уровень диктатуры в стране:" + manager.D + "%\n"+"========"
                        + story.current_situation.subject + "=======\n"+story.current_situation.text);


                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag++;
                        if (flag>3) flag=1;
                        switch (flag){
                            case 1:
                                btn1.setText("Продолжить");
                                btn2.setVisibility(View.INVISIBLE);
                                btn3.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                btn2.setVisibility(View.VISIBLE);
                                btn1.setText("1");
                                break;
                            case 3:
                                btn3.setVisibility(View.VISIBLE);
                        }

                        story.go(1);

                        manager.M+=story.current_situation.dM;
                        manager.S+=story.current_situation.dS;
                        manager.D+=story.current_situation.dD;
                        if (story.isEnd()) {
                            if (manager.S >= 50) {
                                text.setText("Пришли результаты выборов. Мои поздравления," + manager.name + ", вы победили на них! Это, конечно же, толька ваша заслуга. Мне очень будет приятно работать с вами и в дальнейшем." + "\n\"=======Конец!=======\"");
                            } else if (manager.S < 50) {
                                text.setText("Пришли результаты выборов. К сожелению," + manager.name + ", вы на них програли. Не отчаивайтесь, плохие события случаются постоянно. Может еще увидимся, а пока прощайте." + "\n\"======Конец!======\"");
                            } else if (manager.D == 100) {
                                text.setText("Вы проиграли выборы," + manager.name + ". Но из-за того, что вы достигли полноправной власти и стали диктатором всего за 4 года, вам не составило труда сфальсифицировать выборы.Мои поздравления.\n"
                                        + "Но мне кажется, что это не закончится хорошо, но кто я такой, чтобы перечить моему полноправному правителю." + "\n\"=====Конец!====\"");
                            }
                            end();
                        }
                        else if(story.isEnd_M(manager.M)) {
                            text.setText("Похоже, что казна пуста. Экономика находится в критическом состоянии. Страна на грани краха. Чтобы ваши граждани вас не вынесли вас из президентского дворца вперед ногами, вы решаете по своей воле покинуть пост президента.\n"
                                    + "Вас опишут в строках книг по истории не самыми лестными словами, мягко говоря."+"\n\"=====Конец!====\"");
                            end();

                        }else{
                            if(manager.S<3) {
                                manager.S=3;
                            }
                            text.setText("=====\nДеньги:" + manager.M + "млн.\nПоддержка населения:"
                                    + manager.S + "%\n  Уровень диктатуры в стране:" + manager.D + "%\n"+"========"
                                    + story.current_situation.subject + "=======\n"+story.current_situation.text);

                        }
                    }

                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag++;
                        if (flag>3) flag=1;
                        switch (flag){
                            case 1:
                                btn1.setText("Продолжить");
                                btn2.setVisibility(View.INVISIBLE);
                                btn3.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                btn2.setVisibility(View.VISIBLE);
                                btn1.setText("1");
                                break;
                            case 3:
                                btn3.setVisibility(View.VISIBLE);
                        }

                        story.go(2);
                        manager.M+=story.current_situation.dM;
                        manager.S+=story.current_situation.dS;
                        manager.D+=story.current_situation.dD;
                        if (story.isEnd()) {
                            if (manager.S >= 50) {
                                text.setText("Пришли результаты выборов. Мои поздравления," + manager.name + ", вы победили на них! Это, конечно же, толька ваша заслуга. Мне очень будет приятно работать с вами и в дальнейшем." + "\n\"=======Конец!=======\"");
                            } else if (manager.S < 50) {
                                text.setText("Пришли результаты выборов. К сожелению," + manager.name + ", вы на них програли. Не отчаивайтесь, плохие события случаются постоянно. Может еще увидимся, а пока прощайте." + "\n\"=======Конец!=====\"");
                            } else if (manager.D == 100) {
                                text.setText("Вы проиграли выборы," + manager.name + ". Но из-за того, что вы достигли полноправной власти и стали диктатором всего за 4 года, вам не составило труда сфальсифицировать выборы.Мои поздравления.\n"
                                        + "Но мне кажется, что это не закончится хорошо, но кто я такой, чтобы перечить моему полноправному правителю." + "\n\"=====Конец!====\"");
                            }
                            end();
                        }
                        else if(story.isEnd_M(manager.M)) {
                            text.setText("Похоже, что казна пуста. Экономика находится в критическом состоянии. Страна на грани краха. Чтобы ваши граждани вас не вынесли вас из президентского дворца вперед ногами, вы решаете по своей воле покинуть пост президента.\n"
                                    + "Вас опишут в строках книг по истории не самыми лестными словами, мягко говоря."+"\n\"=====Конец!====\"");
                            end();
                        }else{
                            if(manager.S<3) {
                                manager.S=3;
                            }
                            text.setText("=====\nДеньги:" + manager.M + "млн.\nПоддержка населения:"
                                    + manager.S + "%\n  Уровень диктатуры в стране:" + manager.D + "%\n"+"========"
                                    + story.current_situation.subject + "=======\n"+story.current_situation.text);

                        }
                    }
                });
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flag++;
                        if (flag>3) flag=1;
                        switch (flag){
                            case 1:
                                btn1.setText("Продолжить");
                                btn2.setVisibility(View.INVISIBLE);
                                btn3.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                btn2.setVisibility(View.VISIBLE);
                                btn1.setText("1");
                                break;
                            case 3:
                                btn3.setVisibility(View.VISIBLE);
                        }

                        story.go(3);
                        manager.M+=story.current_situation.dM;
                        manager.S+=story.current_situation.dS;
                        manager.D+=story.current_situation.dD;
                        if (story.isEnd()) {
                            if (manager.S >= 50) {
                                text.setText("Пришли результаты выборов. Мои поздравления," + manager.name + ", вы победили на них! Это, конечно же, толька ваша заслуга. Мне очень будет приятно работать с вами и в дальнейшем." + "\n\"=======Конец!=======\"");
                            } else if (manager.S < 50) {
                                text.setText("Пришли результаты выборов. К сожелению," + manager.name + ", вы на них програли. Не отчаивайтесь, плохие события случаются постоянно. Может еще увидимся, а пока прощайте." + "\n\"=======Конец!======\"");
                            } else if (manager.D == 100) {
                                text.setText("Вы проиграли выборы," + manager.name + ". Но из-за того, что вы достигли полноправной власти и стали диктатором всего за 4 года, вам не составило труда сфальсифицировать выборы.Мои поздравления.\n"
                                        + "Но мне кажется, что это не закончится хорошо, но кто я такой, чтобы перечить моему полноправному правителю." + "\n\"=====Конец!====\"");
                            }
                            end();
                        }
                        else if(story.isEnd_M(manager.M)) {
                            text.setText("Похоже, что казна пуста. Экономика находится в критическом состоянии. Страна на грани краха. Чтобы ваши граждани вас не вынесли вас из президентского дворца вперед ногами, вы решаете по своей воле покинуть пост президента.\n"
                                    + "Вас опишут в строках книг по истории не самыми лестными словами, мягко говоря."+"\n\"=====Конец!====\"");
                            end();
                        }else{
                            if(manager.S<3) {
                                manager.S=3;
                            }
                            text.setText("=====\nДеньги:" + manager.M + "млн.\nПоддержка населения:"
                                    + manager.S + "%\n  Уровень диктатуры в стране:" + manager.D + "%\n"+"========"
                                    + story.current_situation.subject + "======\n"+story.current_situation.text);

                        }
                    }
                });

            }
        });






    }
}