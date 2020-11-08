package bisan.android.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private EditText edtTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edtTime = findViewById(R.id.edtTime);
        edtTime.setText("00:00:00");
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
        }


    }


    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("seconds", seconds);
        bundle.putBoolean("running", running);
    }


    public void onClickStart(View view) {
        running = true;
        String str = edtTime.getText().toString();
        if(!str.isEmpty() && str.length()==8)
            runTimer();
        else
            edtTime.setText("error");
    }

    public void onClickStop(View view) {

    }
    public void onClickPause(View view) {
        running = false;
    }

    public void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                String str = edtTime.getText().toString();
                String [] array = str.split(":");
                int hours = Integer.parseInt(array[0]);
                int minutes = Integer.parseInt(array[1]);
                int snds = Integer.parseInt(array[2]);

                if(hours < 25 && minutes < 61 && snds < 61){
                    if(snds!=0)
                        seconds = snds;
                    if(minutes != 0)
                        seconds = minutes % 3600 *60 + snds;
                    if(hours != 0)
                        seconds = (hours *60 * 60) +  ((minutes % 3600) *60) + snds ;

                    if(running){
                        seconds-- ;}
                    if(seconds>=0 ){
                    hours = seconds/3600;
                    minutes = seconds % 3600 /60;
                    snds = seconds % 60;
                        String time = String.format(Locale.getDefault(),
                                "%02d:%02d:%02d", hours, minutes, snds);
                        edtTime.setText(time);}



                    }
                else
                    edtTime.setText("error");

                handler.postDelayed(this, 1000);

            }
        });
    }

}