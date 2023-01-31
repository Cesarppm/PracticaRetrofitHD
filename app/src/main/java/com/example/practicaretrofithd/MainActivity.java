package com.example.practicaretrofithd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.practicaretrofithd.Interfaz.JuguestesApi;
import com.example.practicaretrofithd.Model.Juguetes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {


    private TextView JsonHDTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JsonHDTxtView = findViewById(R.id.jsonHD);
        getJuguetes();
    }
    private void getJuguetes(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://huasteco.tiburcio.mx/~dam17091172/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JuguestesApi juguetesApi = retrofit.create(JuguestesApi.class);
        Call<List<Juguetes>> call = juguetesApi.getJuguetes();

        call.enqueue(new Callback<List<Juguetes>>() {
            @Override
            public void onResponse(Call<List<Juguetes>> call, Response<List<Juguetes>> response) {

                if (!response.isSuccessful()){
                    JsonHDTxtView.setText("Codigo: "+response.code());
                    return;
                }
                List<Juguetes> JuguetesList = response.body();
                for (Juguetes jugue: JuguetesList) {
                    String content = "";
                    content += "idjugue: " + jugue.getIdjugue() + "\n";
                    content += "Nombre: " + jugue.getNombre() + "\n";
                    content += "Precio: " + jugue.getPrecio() + "\n";
                    content += "---------------------------------------------------------------" + "\n";

                    JsonHDTxtView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Juguetes>> call, Throwable t) {
                JsonHDTxtView.setText(t.getMessage());

            }
        });
    }
}