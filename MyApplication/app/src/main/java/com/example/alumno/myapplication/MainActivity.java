package com.example.alumno.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alumno.myapplication.helpers.Callback;
import com.example.alumno.myapplication.helpers.MonitorObservable;
import com.example.alumno.myapplication.helpers.ObserverBind;
import com.example.alumno.myapplication.models.Client;

import java.util.Observable;

public class MainActivity extends AppCompatActivity {
Button btnEdad;
MonitorObservable monitorObservable;
ObserverBind observerBind;
Client clientObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clientObject = new Client();
        clientObject.setYear_old(19);
        /*aqui bindeamos un objecto con un observador*/
        monitorObservable = new MonitorObservable(clientObject);
        observerBind = new ObserverBind();
        observerBind.setCallback(new Callback(){
            @Override
            public void doThis(Observable o, Object x){
                //todo lo que yo quiera
                Log.v( "bichito","xxx" );
                Toast.makeText( MainActivity.this,clientObject.getYear_old()+"a",Toast.LENGTH_SHORT).show();

            }
        });
        monitorObservable.addObserver(observerBind);
    btnEdad= (Button)findViewById(R.id.btnEdad);
        btnEdad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientObject.setYear_old(clientObject.getYear_old() +1);
                monitorObservable.setWachedValue(clientObject);
            }
        });



    }

}
