package com.example.loren.calculadora;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity {
    private Button mBotonInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBotonInicio=(Button) findViewById(R.id.boton_iniciar_sesion);
        setContentView(R.layout.login_layout);
    }

    public void BotonInicio(View view){
        Intent i1=new Intent(login.this,MainActivity.class);
        startActivity(i1);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }
}
