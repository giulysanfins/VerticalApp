package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tela_Horizontal extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor acelerometro;

    public BigDecimal horizontalX;
    public double xx = 7.2;
    public double resultado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_horizontal);
         final TextView diferenca_text = findViewById(R.id.diferenca);
        diferenca_text.setText(" ");
        final TextView valor_text = findViewById(R.id.valorObtido);
        valor_text.setText(" ");

        ConstraintLayout tela = findViewById(R.id.telaMinha);

        tela.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onPause();
                System.out.println(horizontalX);

                if(horizontalX.doubleValue() == xx){
                    Toast.makeText(Tela_Horizontal.this, "acertou!",
                            Toast.LENGTH_LONG).show();

                } //horizontalX eh a variavel que o cara apertou
                resultado = horizontalX.doubleValue() - xx; //xx eh o valor truncado
                valor_text.setText("O valor encontrado foi de  " + horizontalX);
                BigDecimal resultado_1casa = new BigDecimal(resultado).setScale(1, RoundingMode.HALF_EVEN);
                diferenca_text.setText("A diferenca foi de  " + resultado_1casa);
            }
        });
        // Instância da classe sensorManager através do método abaixo
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Definindo o tipo de sensor que vou estar utilizando
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }



    // Método que inicia a captura do acelerômetro
    protected void onResume(){
        super.onResume();

        // Parâmetro SENSOR_DELAY_NORMAL define a velocidade da captura das informações
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    // Método para parar quando não houver interação do usuário, para economizar a bateria
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    //Acionando se houver mudança na precisão do sensor do acelerômetro
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    //Acionando sempre quando houver na posição do dispositivo identificado pelo sensor
    public void onSensorChanged(SensorEvent event){





        double x = event.values[0];
        BigDecimal bd = new BigDecimal(x).setScale(1, RoundingMode.HALF_EVEN);
        System.out.println(bd.doubleValue());
        horizontalX=bd;


    }

}
