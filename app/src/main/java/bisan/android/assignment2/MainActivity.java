package bisan.android.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;


@SuppressWarnings("unchecked")
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    public static final String NAME = "NAME";
    public static final String WEIGHT = "WEIGHT";
    public static final String HEIGHT = "HEIGHT";
    public static final String GENDER = "GENDER";

    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private EditText edtName;
    private EditText edtHeight;
    private EditText edtWeight;
    private TextView textBMI;
    private Spinner spGender;
    private TextView txtGe;
    private TableRow trgender;
    private TextView txtres;
    private String[] gender={"Female","Male"};
    private ArrayAdapter dataAdapter  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkPrefs();

    }

    //setupViews
    private void setupViews(){
        edtName = findViewById(R.id.edtName);
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        textBMI = findViewById(R.id.txtBMI);
        spGender = findViewById(R.id.spGender);
        spGender.setOnItemSelectedListener(this);
        txtGe = (TextView) findViewById(R.id.txtGe);
        txtres = findViewById(R.id.txtres);
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spGender.setAdapter(dataAdapter);

    }



    //Spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
        txtres.setText(spGender.getSelectedItem().toString());
 }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //SAVE
    private void checkPrefs(){
//        String text = spGender.getSelectedItem().toString();


            String name = prefs.getString(NAME,"");
            String height = prefs.getString(HEIGHT,"");
            String weight = prefs.getString(WEIGHT,"");
            String gender = prefs.getString(GENDER,"");

        edtName.setText(name);
        edtHeight.setText(height);
        edtWeight.setText(weight);
        txtGe.setText(gender);

    }

    private void setupSharedPrefs(){
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    //Saved Button
    public void saveOnClicked(View view) {

        String name = edtName.getText().toString();
        String height = edtHeight.getText().toString();
        String weight = edtWeight.getText().toString();
        String gender = txtres.getText().toString();

            editor.putString(NAME,name);
            editor.putString(HEIGHT,height);
            editor.putString(WEIGHT,weight);
            editor.putString(GENDER,"Gender of "+name+" is "+gender);
        editor.commit();





    }

    //bmi
     public void bmiOnClicked(View view) {
        if(edtWeight.getText().toString().isEmpty() || edtHeight.getText().toString().isEmpty()){
            textBMI.setText("Fill Height & Weight fields");
        }
        else{
    double heightInMeter = Integer.parseInt(String.valueOf(edtHeight.getText()))/100.0 ;
    double bmi = Integer.parseInt(String.valueOf(edtWeight.getText())) / (heightInMeter * heightInMeter );
    String status = null;
    if (bmi < 18.5) {
        status = "Underweight";
    } else if (bmi > 18.5 && bmi < 25) {
        status = "Normal";
    } else if (bmi > 25 && bmi < 30) {
        status = "Overweight";
    } else if (bmi == 30 || bmi > 30) {
        status = "Obese";
    }
    textBMI.setText( String. format("%.2f", bmi) + "==>" + status);}

}
 //action of timer
    public void timerOnClicked(View view) {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);

    }


    public void onClickStart(View view) {
    }

    public void onClickStop(View view) {
    }

    public void onClickPause(View view) {
    }
}