package com.dhian.unitconverter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.dhian.unitconverter.ConversionFactors.Length;
import com.dhian.unitconverter.ConversionFactors.Temperature;
import com.dhian.unitconverter.ConversionFactors.Weight;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerType, spinnerSourceUnit, spinnerTargetUnit;
    private Button convertButton;
    private EditText inputValueText;
    private TextView textViewResultValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context self = this;

        // Enable toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        String[] converterTypes = getResources().getStringArray(R.array.converter_types);

        spinnerType = (Spinner) findViewById(R.id.spinner_type);
        spinnerSourceUnit = (Spinner) findViewById(R.id.spinner_source_unit);
        spinnerTargetUnit = (Spinner) findViewById(R.id.spinner_target_unit);
        textViewResultValue = (TextView) findViewById(R.id.textView_resultValue);

        spinnerSourceUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                textViewResultValue.setText("-");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        spinnerTargetUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                textViewResultValue.setText("-");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println(" " + position + " - " + id);
                String selectedType = converterTypes[position];

                switch(selectedType) {
                    case "Weight":
                        setSpinnerValues(spinnerSourceUnit, R.array.weight_units);
                        setSpinnerValues(spinnerTargetUnit, R.array.weight_units);
                        break;
                    case "Length":
                        setSpinnerValues(spinnerSourceUnit, R.array.length_units);
                        setSpinnerValues(spinnerTargetUnit, R.array.length_units);
                        break;
                    case "Temperature":
                        setSpinnerValues(spinnerSourceUnit, R.array.temperature_units);
                        setSpinnerValues(spinnerTargetUnit, R.array.temperature_units);
                        break;
                    default:
                        break;
                }
                inputValueText.setText("");
                textViewResultValue.setText("-");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {}
        });

        this.setSpinnerValues(spinnerType, R.array.converter_types);

        convertButton = (Button) findViewById(R.id.button_convert);
        convertButton.setEnabled(false);

        inputValueText = (EditText) findViewById(R.id.editTextNumber_inputValue);
        inputValueText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                convertButton.setEnabled(s.length() > 0);
                textViewResultValue.setText("");
            }
        });

        convertButton.setOnClickListener(v -> {
            String inputValueStr = inputValueText.getText().toString().trim();
            if (inputValueStr.isEmpty()) {
                Toast.makeText(self, "Value cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }

            double inputValue;
            try {
                inputValue = Double.parseDouble(inputValueStr);
            } catch (Exception e) {
                Toast.makeText(self, "Please enter a valid value", Toast.LENGTH_SHORT).show();
                return;
            }
            String selectedSourceUnit = (String) spinnerSourceUnit.getSelectedItem();
            String selectedTargetUnit = (String) spinnerTargetUnit.getSelectedItem();

            double convertedValue;
            switch ((String) spinnerType.getSelectedItem()) {
                case "Weight":
                    convertedValue = Weight.convert(inputValue, selectedSourceUnit, selectedTargetUnit);
                    break;
                case "Length":
                    convertedValue = Length.convert(inputValue, selectedSourceUnit, selectedTargetUnit);
                    break;
                case "Temperature":
                    convertedValue = Temperature.convert(inputValue, selectedSourceUnit, selectedTargetUnit);
                    break;
                default:
                    Toast.makeText(self, "Invalid conversion type", Toast.LENGTH_SHORT).show();
                    return;
            }
            textViewResultValue.setText(String.format("%s %s =\n%s %s", inputValue, selectedSourceUnit, convertedValue, selectedTargetUnit));
        });
    }

    private void setSpinnerValues(Spinner spinner, int textArrayId) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            textArrayId,
            android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
