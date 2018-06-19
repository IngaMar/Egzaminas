package com.example.dariusm.egzaminas.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dariusm.egzaminas.Model.DatabaseSQLite;
import com.example.dariusm.egzaminas.Model.Klientas;
import com.example.dariusm.egzaminas.R;

public class MainActivity extends AppCompatActivity {
    Button btnSubmit, btnUpdate, btnDelete;
    EditText name;
    EditText etName;

    CheckBox cbNaujas, cbLojalus, cbIprastas;
    Spinner spinner;
    ArrayAdapter<String> adapter;

    Klientas pradinisKlientas;
    Klientas galutinisKlientas;

    DatabaseSQLite db;

    //String items[] = {"Apyrankė", "Auskarai", "Vėrinys", "Rinkinys"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseSQLite(MainActivity.this);

        int entryID = -1;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (!extras.isEmpty()) {
                entryID = extras.getInt(ENTRY_ID);
            }
        } else { // jeigu yra naujas irasas, id = -1, jeigu egzistuojantis, bus teigiamas
            entryID = (Integer) savedInstanceState.getSerializable(ENTRY_ID);
        }

        if (entryID == -1) {
            setTitle(R.string.new_entry_label);
        } else {
            setTitle(R.string.entry_update_label);
        }

        pradinisKlientas = new Klientas();
        if (entryID == -1) { //naujas irasas
            pradinisKlientas.setId(-1);
            pradinisKlientas.setVardas("");
            pradinisKlientas.setMetai(0);
            pradinisKlientas.setTelefon(0);
            pradinisKlientas.setTipas("Naujas");

        } else { // egzistuojantis irasas
            pradinisKlientas = db.getKlientas(entryID);
        }
        galutinisKlientas = new Klientas();

        btnSubmit = (Button) findViewById(R.id.btnAdd);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        if (entryID == -1) { //naujas irasas - disable update button
            btnUpdate.setEnabled(false);
            btnSubmit.setEnabled(true);
            btnDelete.setEnabled(false);
        } else { // egzistuojantis irasas - disable submit
            btnUpdate.setEnabled(true);
            btnSubmit.setEnabled(false);
        }

        etName = (EditText) findViewById(R.id.etName);
        //etWeight = (EditText) findViewById(R.id.etWeight);
        //etHeight = (EditText) findViewById(R.id.etHeight);


        cbNaujas = (CheckBox) findViewById(R.id.cbNaujas);
        cbLojalus = (CheckBox) findViewById(R.id.cbLojalus);
        cbIprastas = (CheckBox) findViewById(R.id.cbIprastas);

        //spinner = (Spinner) findViewById(R.id.spinner);
        //adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        //spinner.setAdapter(adapter);

        fillFields(pradinisKlientas);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getFields()){

                    db.addKlientas(galutinisKlientas);

                    Intent goToSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(goToSearchActivity);}
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFields()) {

                    db.updateKlientas(galutinisKlientas);

                    Intent goToSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(goToSearchActivity);}
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.deleteKlientas(pradinisKlientas.getId());

                Intent goToSearchActivity = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(goToSearchActivity);
            }
        });
    }

    private boolean getFields() {

        if (etName.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Įveskite vardą ir pavardę !", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!(cbNaujas.isChecked() || cbLojalus.isChecked() || cbIprastas.isChecked())) {
            Toast.makeText(MainActivity.this, "Pasirinkite kliento tipą!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
// TO DO: ismeta pranesima, kad nepasinkta, bet issaugoja su klaidomis
            String name = etName.getText().toString();
            String spinnerText = "";



            spinnerText = spinner.getSelectedItem().toString();

            galutinisKlientas.setId(pradinisKlientas.getId());
            galutinisKlientas.setVardas(name);
            galutinisKlientas.setTipas(checkboxText);
        }
        return true;
    }

    private void fillFields(Klientas klientas) {
        etName.setText(klientas.getVardas());
        cbNaujas.setChecked(klientas.getTipas().contains("Naujas"));
        cbLojalus.setChecked(klientas.getTipas().contains("Lojalus"));
        cbIprastas.setChecked(klientas.getTipas().contains("Įprastas"));
        spinner.setSelection(adapter.getPosition(klientas.getTipas()));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getFields();
                if (pradinisKlientas.equals(galutinisKlientas)) { //Nebuvo pakeistas
                    finish();
                } else {  //Buvo pakeistas
                    showDialog();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                MainActivity.this);

        // set title
        alertDialogBuilder.setTitle("Įspėjimas");

        // set dialog message
        alertDialogBuilder
                .setMessage("Išsaugoti pakeitimus?")
                .setCancelable(false)
                .setPositiveButton("Taip", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                })
                .setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        MainActivity.this.finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
