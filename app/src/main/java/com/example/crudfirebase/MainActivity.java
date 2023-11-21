package com.example.crudfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    ProgressBar pb1;
    int counter;

    //MQTT
    /*
    String clienteID = "";

    //Variables para la conexión MQTT

    static String MQTTHOST = "tcp://crudfirebase0.cloud.shiftr.io:1833";
    static String MQTTUSER = "crudfirebase0";
    static String MQTTPASS = "DlR3cP4UcA7azL7N";

    static String TOPIC = "INVENTARIO";
    static String TOPIC_MSG_ADD = "Ha sido agregado un nuevo artículo al inventario";
    static String TOPIC_MSG_EDIT = "Ha sido actualizado un artículo del inventario";
    static String TOPIC_MSG_DEL = "Ha sido eliminado un artículo del inventario";

    //Other variables de la actividad
    MqttAndroidClient cliente;
    MqttConnect Options opciones;
    Boolean permisoPublicar;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        //MQTT
        getNombreCLiente();
        connectBroker();
        */

        pb1 = findViewById(R.id.progressBar);
        btn1 = findViewById(R.id.btn_inicio);

        //Button al CRUD
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pb1.setVisibility(View.VISIBLE);

                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {

                        counter++;

                        pb1.setProgress(counter);
                        if(counter == 50){
                            timer.cancel();

                            Intent opcion = new Intent(MainActivity.this, CrudActivity.class);
                            startActivity(opcion);
                        }
                    }
                };
                timer.schedule(timerTask, 50, 50);
            }
        });
    }
    /*
    //Envia un mensaje al sv MQTT
    private void enviarMensaje(String topic, String msg){
        checkConnection();
        if (this.permisoPublicar){
            try{
                int qos = 0;
                this.cliente.publish(topic, msg.getBytes(), qos, false);
                Toast.makeText(getBaseContext(), topic + " : " + msg, Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //Verifica la conexion con el sv MQTT
    private void checkConnection(){
        if(this.cliente.isConnected()){
            this.permisoPublicar = true;
        }else{
            this.permisoPublicar = false;
            connectBroker();
        }
    }

    //Establece la conexion con el sv MQTT
    private void connectBroker(){
        this.cliente = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, this.clienteID);
        this.opciones = new MqttConnectOptions();
        this.opciones.setUserName(MQTTUSER);
        this.opciones.setPassword(MQTTPASS.toCharArray());
        try{
            IMqttToken token = this.cliente.connect(opciones);
            token.setActionCallBack(new IMqttActionListener(){
                @Override
                public void onSuccess(IMqttToken asyncActionToken){
                    Toast.makeText(getBaseContext(), "Conectado", Toast.LENGTH_SHORT).show();
                    mensajearTopic();
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception){
                    Toast.makeText(getBaseContext(), "Conexión Fallida", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (MqttException e){
            e.printStackTrace();
        }
    }

    private void mensajearTopic(){
        try{
            this.cliente.subscribe(TOPIC, 0);
        }catch(MqttSecurityException e){
            e.printStackTrace();
        }catch (MqttSecurityException e){
            e.printStackTrace();
        }
        this.cliente.setCallback(new MqttCallBack(){
            @Override
            public void connectionLost(Throwable cause){
                Toast.makeText(getBaseContext(), "Se desconectó el servidor", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void messageArrived(String topic, MqttMesagge message) throws Exception{

                if(topic.matches(TOPIC)){
                    String msg = new String(message.getPayload());
                    if (msg.matches(TOPIC_MSG_ADD)){
                        //Muestra el mensaje de que se agregó
                    }
                    if (msg.matches(TOPIC_MSG_EDIT)){
                        //Muestra el mensaje de que se editó
                    }
                    if (msg.matches(TOPIC_MSG_DEL)){
                        //Muestra el mensaje de que se eliminó
                    }
                }
            }
            @Override
            public void deliveryComplete(IMqttDeliveryToken token){

            }
        });
    }*/
}