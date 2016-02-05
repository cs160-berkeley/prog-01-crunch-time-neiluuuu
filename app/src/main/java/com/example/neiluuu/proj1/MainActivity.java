package com.example.neiluuu.proj1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.AdapterView;
import android.text.TextUtils;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HashMap<String, Object[]> conversion = new HashMap<String, Object[]>();
        conversion.put("Calories", new Object[]{100, " Cal(s)"});
        conversion.put("Pushups", new Object[]{350, "Rep(s)"});
        conversion.put("Situps", new Object[] {200, "Rep(s)"});
        conversion.put("Squats", new Object[]{225, "Rep(s)"});
        conversion.put("Leg-lifts", new Object[]{25, "Min(s)"});
        conversion.put("Plank", new Object[]{25, "Min(s)"});
        conversion.put("Jumping Jacks", new Object[]{10, "Min(s)"});
        conversion.put("Pullups", new Object[]{100, "Rep(s)"});
        conversion.put("Cycling", new Object[]{12, "Min(s)"});
        conversion.put("Walking", new Object[]{20, "Min(s)"});
        conversion.put("Jogging", new Object[]{12, "Min(s)"});
        conversion.put("Swimming", new Object[]{13, "Min(s)"});
        conversion.put("Stair Climbing", new Object[]{15, "Min(s)"});

        Spinner exercise1 = (Spinner) findViewById(R.id.exercise1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exercise1.setAdapter(adapter);

        exercise1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                TextView units1 = (TextView) findViewById(R.id.units1);
                TextView caloriesBurned = (TextView) findViewById(R.id.calories);
                TextView converted = (TextView) findViewById(R.id.converted);

                Object[] val = conversion.get(parent.getItemAtPosition(pos).toString());
                String text = (String) val[1];

                units1.setText(text);
                converted.setText("--");
                caloriesBurned.setText("--     Calories Burned");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        Spinner exercise2 = (Spinner) findViewById(R.id.exercise2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.exercises, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exercise2.setAdapter(adapter2);

        exercise2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                TextView units2 = (TextView) findViewById(R.id.units2);
                TextView caloriesBurned = (TextView) findViewById(R.id.calories);
                TextView converted = (TextView) findViewById(R.id.converted);

                Object[] val = conversion.get(parent.getItemAtPosition(pos).toString());
                String text = (String) val[1];

                units2.setText(text);
                converted.setText("--");
                caloriesBurned.setText("--     Calories Burned");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                return;
            }

        });

        EditText input = (EditText)findViewById(R.id.input);
        input.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                TextView caloriesBurned = (TextView) findViewById(R.id.calories);
                TextView converted = (TextView) findViewById(R.id.converted);
                converted.setText("--");
                caloriesBurned.setText("--     Calories Burned");
            }
        });

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView caloriesBurned = (TextView) findViewById(R.id.calories);
                TextView converted = (TextView) findViewById(R.id.converted);
                EditText input = (EditText)findViewById(R.id.input);
                Spinner exercise1 = (Spinner) findViewById(R.id.exercise1);
                Spinner exercise2 = (Spinner) findViewById(R.id.exercise2);
                String exerciseText1 = (String) exercise1.getSelectedItem();
                String exerciseText2 = (String) exercise2.getSelectedItem();
                if (!TextUtils.isEmpty(input.getText().toString())) {
                    Object[] val1 = conversion.get(exerciseText1);
                    Object[] val2 = conversion.get(exerciseText2);
                    int amt = Integer.parseInt(input.getText().toString());
                    float calBurned = (float) amt * 100 / (int) val1[0];
                    caloriesBurned.setText(calBurned + " Calories Burned");
                    float convertedValue = calBurned * (int) val2[0] / 100;
                    converted.setText(Float.toString(convertedValue));
                }
            }
        });
    }
}
