package com.example.imc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //variaveis de objetos que precisam ser manipulados da tela
    EditText etAltura;
    EditText etPeso;
    Button btCalcular;
    TextView tvImc;
    TextView tvSituacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pegando esses objetos pelo id e atribuindo para as variveis criadas
        etAltura = findViewById(R.id.etAltura);
        etPeso = findViewById(R.id.etPeso);
        btCalcular = findViewById(R.id.btCalcular);
        tvImc = findViewById(R.id.tvImc);
        tvSituacao = findViewById(R.id.tvSituacao);
    }

    public void calcularImc(View view) {
        Double imc, peso, altura;

        //pega o texto digitado no input e o transforma em string
        peso = Double.parseDouble(etPeso.getText().toString());
        altura = Double.parseDouble(etAltura.getText().toString());

        imc = peso / (altura * altura);

        //pega o resultado do imc e converte para inteiro

        tvImc.setText(("IMC: " + imc));
        tvSituacao.setText(situacao(imc));

        //limpa os dados do editText dps de enviados
        etAltura.setText("");
        etPeso.setText("");

        //vai chamar o método dps de clicar no botão calcular
        minimizaTeclado(btCalcular);
    }

    public String situacao(Double imc) {
        String situacao = "Situação: ";

        if(imc < 18.5) {
            situacao += "abaixo do peso";
        }

        else if(imc < 25) {
            situacao += "peso normal";
        }

        else if(imc < 30) {
            situacao += "sobrepeso";
        }

        else if(imc < 35) {
            situacao += "obesidade grau 1";
        }

        else if(imc < 40) {
            situacao += "obesidade grau 2";
        }

        else {
            situacao += "obesidade grau 3";
        }

        return situacao;
    }

    //minimiza o teclado dps de ter enviado os dados
    public void minimizaTeclado(Button button){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(button.getWindowToken(), 0);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}