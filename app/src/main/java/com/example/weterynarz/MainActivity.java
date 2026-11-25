package com.example.weterynarz;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etWlasciciel, etCelWizyty, etCzas;
    private ListView lvGatunek;
    private TextView tvWiek, tvWynik;
    private SeekBar sbWiek;
    private Button btnOk;

    private String[] gatunki = {"Pies", "Kot", "Świnka morska"};
    private String wybranyGatunek = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etWlasciciel = findViewById(R.id.etWlasciciel);
        lvGatunek = findViewById(R.id.lvGatunek);
        tvWiek = findViewById(R.id.tvWiek);
        sbWiek = findViewById(R.id.sbWiek);
        etCelWizyty = findViewById(R.id.etCelWizyty);
        etCzas = findViewById(R.id.etCzas);
        btnOk = findViewById(R.id.btnOk);
        tvWynik = findViewById(R.id.tvWynik);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                gatunki
        );
        lvGatunek.setAdapter(adapter);

        lvGatunek.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wybranyGatunek = gatunki[position];

                if (wybranyGatunek.equals("Pies")) {
                    sbWiek.setMax(18);
                } else if (wybranyGatunek.equals("Kot")) {
                    sbWiek.setMax(20);
                } else if (wybranyGatunek.equals("Świnka morska")) {
                    sbWiek.setMax(9);
                }
            }
        });

        sbWiek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvWiek.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wlasciciel = etWlasciciel.getText().toString();
                String wiek = tvWiek.getText().toString();
                String cel = etCelWizyty.getText().toString();
                String czas = etCzas.getText().toString();

                String komunikat = wlasciciel + ", " + wybranyGatunek + ", " + wiek + ", " + cel + ", " + czas;
                tvWynik.setText(komunikat);
            }
        });
    }
}
