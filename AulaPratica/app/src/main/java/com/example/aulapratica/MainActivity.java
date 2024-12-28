package com.example.aulapratica;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText inputData;
    private TextView outputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData = findViewById(R.id.inputData);
        outputData = findViewById(R.id.outputData);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnLoad = findViewById(R.id.btnLoad);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(inputData.getText().toString());
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
    }

    private void saveData(String data) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("data", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String jsonString = jsonObject.toString();
        
        FileOutputStream fos;
        try {
            fos = openFileOutput("data.json", Context.MODE_PRIVATE);
            fos.write(jsonString.getBytes());
            fos.close();
            Log.d("MainActivity", "Data saved successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        // Lê o arquivo JSON do armazenamento interno
        FileInputStream fis;
        try {
            fis = openFileInput("data.json");
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            String jsonString = new String(buffer);

            // Converte a string JSON de volta para um objeto
            JSONObject jsonObject = new JSONObject(jsonString);
            String data = jsonObject.getString("data");

            // Exibe os dados carregados
            outputData.setText(data);
            Log.d("MainActivity", "Data loaded successfully");
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
