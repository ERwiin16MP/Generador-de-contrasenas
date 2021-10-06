package com.erwin16mp.generadordecontraseas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class Index extends AppCompatActivity {

    private AdView mAdView;
    private EditText TextBox_Numero_de_caracteres;
    private CheckBox CheckBox_Numeros, CheckBox_Letras_Mayusculas, CheckBox_Letras_Minusculas, CheckBox_Caracteres_Especiales;
    private TextView Label_Contraseña;
    private int Numero;
    private  Random random = new Random();
    private String Contraseña_Generada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6200EE")));
        setContentView(R.layout.activity_index);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        TextBox_Numero_de_caracteres = findViewById(R.id.Numero);
        CheckBox_Numeros = findViewById(R.id.checkBox_numeros);
        CheckBox_Letras_Mayusculas = findViewById(R.id.checkBox_letras_mayusculas);
        CheckBox_Letras_Minusculas = findViewById(R.id.checkBox_letras_minusculas);
        CheckBox_Caracteres_Especiales = findViewById(R.id.checkBox_caracteres);
        Label_Contraseña = findViewById(R.id.Contraseña_Generada);
    }

    public void menos(View view) {
        if (TextBox_Numero_de_caracteres.getText().toString().isEmpty()) {
            TextBox_Numero_de_caracteres.setError("Falta el número");
        } else {
            Numero = Integer.parseInt(TextBox_Numero_de_caracteres.getText().toString());
            if (Numero>1&&Numero<19){
                Numero--;
                TextBox_Numero_de_caracteres.setText(String.valueOf(Numero));
            }
        }
    }

    public void mas(View view) {
        if (TextBox_Numero_de_caracteres.getText().toString().isEmpty()) {
            TextBox_Numero_de_caracteres.setError("Falta el número");
        } else {
            Numero = Integer.parseInt(TextBox_Numero_de_caracteres.getText().toString());
            if (Numero>0&&Numero<18){
                Numero++;
                TextBox_Numero_de_caracteres.setText(String.valueOf(Numero));
            }
        }
    }

    public void generar(View view) {
        if (TextBox_Numero_de_caracteres.getText().toString().isEmpty()) {
            Toast.makeText(this, "El numero de caracteres esta vacio", Toast.LENGTH_SHORT).show();
        } else {
            if (numero() == false && mayusculas() == false && minusculas() == false && caracter() == false) {
                Toast.makeText(this, "No ha marcado ningun criterio", Toast.LENGTH_SHORT).show();
            } else {
                Label_Contraseña.setText(aleatorio(Integer.parseInt(TextBox_Numero_de_caracteres.getText().toString())));
            }
        }
    }

    private String aleatorio(int i){
        Contraseña_Generada="";
        do {
            switch (random.nextInt(4)) {
                case 0:
                    if (numero()){
                        Contraseña_Generada+= String.valueOf(numero_aleatorio());
                    }
                    break;
                case 1:
                    if (mayusculas()){
                        Contraseña_Generada+=letra_aleatoria();
                    }
                    break;
                case 2:
                    if (minusculas()){
                        Contraseña_Generada+=letra_aleatoria().toLowerCase();
                    }
                    break;
                case 3:
                    if (caracter()){
                    Contraseña_Generada+=caracter_aleatorio();}
                    break;
            }
        }while (Contraseña_Generada.length()<i);
        return Contraseña_Generada;
    }

    private boolean numero(){
        if (CheckBox_Numeros.isChecked()){
            return true;
        } else {
            return false;
        }
    }

    private boolean mayusculas(){
        if (CheckBox_Letras_Mayusculas.isChecked()){
            return true;
        } else {
            return false;
        }
    }

    private boolean minusculas(){
        if (CheckBox_Letras_Minusculas.isChecked()){
            return true;
        } else {
            return false;
        }
    }
    private boolean caracter(){
        if (CheckBox_Caracteres_Especiales.isChecked()){
            return true;
        } else {
            return false;
        }
    }

    public void copiar(View view) {
        if (Label_Contraseña.getText().toString().isEmpty()){
            Toast.makeText(this, "No se puede copiar, porque no hay nada", Toast.LENGTH_SHORT).show();
        }else {
            ClipData clip = ClipData.newPlainText("text", Label_Contraseña.getText().toString());
            ClipboardManager clipboard = (ClipboardManager)this.getSystemService(CLIPBOARD_SERVICE);
            clipboard.setPrimaryClip(clip);
            Snackbar.make(view,"Copiado al portapapeles: "+Label_Contraseña.getText().toString(),Snackbar.LENGTH_SHORT).show();
        }
    }

    private int numero_aleatorio(){
        return random.nextInt(10);
    }

    private String letra_aleatoria(){
        String Letra = null;
        switch (random.nextInt(27)) {
            case 0:
                Letra = "A";
                break;
            case 1:
                Letra = "B";
                break;
            case 2:
                Letra = "C";
                break;
            case 3:
                Letra = "D";
                break;
            case 4:
                Letra = "E";
                break;
            case 5:
                Letra = "F";
                break;
            case 6:
                Letra = "G";
                break;
            case 7:
                Letra = "H";
                break;
            case 8:
                Letra = "I";
                break;
            case 9:
                Letra = "J";
                break;
            case 10:
                Letra = "K";
                break;
            case 11:
                Letra = "L";
                break;
            case 12:
                Letra = "M";
                break;
            case 13:
                Letra = "N";
                break;
            case 14:
                Letra = "Ñ";
                break;
            case 15:
                Letra = "O";
                break;
            case 16:
                Letra = "P";
                break;
            case 17:
                Letra = "Q";
                break;
            case 18:
                Letra = "R";
                break;
            case 19:
                Letra = "S";
                break;
            case 20:
                Letra = "T";
                break;
            case 21:
                Letra = "U";
                break;
            case 22:
                Letra = "V";
                break;
            case 23:
                Letra = "W";
                break;
            case 24:
                Letra = "X";
                break;
            case 25:
                Letra = "Y";
                break;
            case 26:
                Letra = "Z";
                break;
        }
        return Letra;
    }

    private String caracter_aleatorio() {
        String caracter = null;
        switch (random.nextInt(33)) {
            case 0:
                caracter=" ";
                break;
            case 1:
                caracter="!";
                break;
            case 2:
                caracter="\"";
                break;
            case 3:
                caracter="#";
                break;
            case 4:
                caracter="$";
                break;
            case 5:
                caracter="%";
                break;
            case 6:
                caracter="&";
                break;
            case 7:
                caracter="\'";
                break;
            case 8:
                caracter="(";
                break;
            case 9:
                caracter=")";
                break;
            case 10:
                caracter="*";
                break;
            case 11:
                caracter="+";
                break;
            case 12:
                caracter=",";
                break;
            case 13:
                caracter="-";
                break;
            case 14:
                caracter=".";
                break;
            case 15:
                caracter="/";
                break;
            case 16:
                caracter=":";
                break;
            case 17:
                caracter=";";
                break;
            case 18:
                caracter="<";
                break;
            case 19:
                caracter="=";
                break;
            case 20:
                caracter=">";
                break;
            case 21:
                caracter="?";
                break;
            case 22:
                caracter="@";
                break;
            case 23:
                caracter="[";
                break;
            case 24:
                caracter="\\";
                break;
            case 25:
                caracter="]";
                break;
            case 26:
                caracter="^";
                break;
            case 27:
                caracter="_";
                break;
            case 28:
                caracter="`";
                break;
            case 29:
                caracter="{";
                break;
            case 30:
                caracter="|";
                break;
            case 31:
                caracter="}";
                break;
            case 32:
                caracter="~";
                break;
        }
        return caracter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Compartir:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Un simple generador de contraseñas: ");
                startActivity(Intent.createChooser(intent, "Compartir"));
                break;
            case R.id.Apoyar:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                LayoutInflater inflater = getLayoutInflater();
                builder.setView(inflater.inflate(R.layout.apoyar, null));
                builder.create().show();
                break;
            case R.id.Mas:

                break;
            case R.id.Acerca_de:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                LayoutInflater inflater1 = getLayoutInflater();
                builder1.setView(inflater1.inflate(R.layout.acerca_de, null));
                builder1.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}