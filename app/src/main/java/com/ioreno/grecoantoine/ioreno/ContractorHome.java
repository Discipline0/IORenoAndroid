package com.ioreno.grecoantoine.ioreno;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContractorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor_home);

        MultiSelectionSpinner spinner = (MultiSelectionSpinner) findViewById(R.id.spinnerFilters);
        spinner.setItems(getApplicationContext().getResources().getStringArray(R.array.type_arrays));
    }
}
