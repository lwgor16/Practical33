package my.edu.taruc.practical33;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAge;
    private RadioGroup radioGroupGender;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private CheckBox checkBoxSmoker;
    private TextView textViewPremium;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerAge = findViewById(R.id.spinner);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        radioButtonMale = findViewById(R.id.radioMale);
        radioButtonFemale = findViewById(R.id.radioFemale);
        checkBoxSmoker = findViewById(R.id.checkBoxSmoker);
        textViewPremium = findViewById(R.id.textViewPremium);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.age_group,android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(adapter);
        spinnerAge.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void calculatePremium(View view)
    {
        int position;
        double premium = 0;
        position = spinnerAge.getSelectedItemPosition();
        if(position == 0)
        {
            premium += 50;
        }
        else if(position == 1)
        {
            premium += 55;
        }
        else if(position == 2)
        {
            premium += 60;
        }
        else if(position == 3)
        {
            premium += 70;
        }
        else if(position == 4)
        {
            premium += 120;
        }
        else if(position == 5)
        {
            premium += 160;
        }
        else if(position == 6)
        {
            premium += 200;
        }
        else
        {
            premium += 250;
        }

        int gender;
        gender = radioGroupGender.getCheckedRadioButtonId();

        if(gender == R.id.radioMale)
        {
            if(position == 2)
            {
                premium += 50;
            }
            else if(position == 3)
            {
                premium += 100;
            }
            else if(position == 4)
            {
                premium += 100;
            }
            else if(position == 5)
            {
                premium += 50;
            }
        }

        if(checkBoxSmoker.isChecked())
        {
            if(position == 3)
            {
                premium += 100;
            }
            else if(position == 4)
            {
                premium += 150;
            }
            else if(position == 5)
            {
                premium += 150;
            }
            else if(position == 6)
            {
                premium += 250;
            }
            else if(position == 7)
            {
                premium += 250;
            }
        }

        Currency currency = Currency.getInstance(Locale.getDefault());
        String symbol = currency.getCurrencyCode();
        textViewPremium.setText("Premium price : " + currency + " " + premium);
    }

    public void Reset(View view)
    {
        if(radioGroupGender.getCheckedRadioButtonId() == R.id.radioMale)
        {
            radioButtonMale.setChecked(false);
        }
        else
        {
            radioButtonFemale.setChecked(false);
        }
        textViewPremium.setText("Premium");
        checkBoxSmoker.setChecked(false);
        spinnerAge.setSelection(0);
    }
}
