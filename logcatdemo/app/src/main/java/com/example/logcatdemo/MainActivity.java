package com.example.logcatdemo;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. NullPointerException
        String text = null;
        try {
            Log.d("MainActivity", text.toString()); // Isto causará uma NullPointerException
        } catch (NullPointerException e) {
            Log.e("MainActivity", "NullPointerException: Tentou chamar um método em um objeto nulo", e);
        }

        // 2. ArrayIndexOutOfBoundsException
        int[] array = {1, 2, 3};
        try {
            Log.d("MainActivity", "Accessing array element: " + array[5]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.e("MainActivity", "ArrayIndexOutOfBoundsException: Índice fora dos limites do array", e);
        }

        // 3. ClassCastException
        Object number = "100";
        try {
            Integer castedNumber = (Integer) number; // ClassCastException
            Log.d("MainActivity", "Number: " + castedNumber);
        } catch (ClassCastException e) {
            Log.e("MainActivity", "ClassCastException: Tentou converter um tipo incompatível", e);
        }

        // Exemplo de diferentes níveis de log:
        Log.v("MainActivity", "Este é um log verbose para informações detalhadas.");
        Log.d("MainActivity", "Este é um log de debug para rastrear valores de variáveis.");
        Log.i("MainActivity", "Este é um log informativo.");
        Log.w("MainActivity", "Este é um log de aviso, algo inesperado aconteceu.");
        Log.e("MainActivity", "Este é um log de erro, algo deu errado.");
    }
}
