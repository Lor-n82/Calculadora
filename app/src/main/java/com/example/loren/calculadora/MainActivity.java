package com.example.loren.calculadora;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{
    private TextView pantallaResultado;
    private TextView pantallaOperaciones;
    private String textoOperaciones = "";
    private String operadorActual = "";
    private String resultado = "";
    private boolean sw=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculadora_layout);

        pantallaOperaciones = (TextView)findViewById(R.id.operacionesTextView);
        pantallaResultado = (TextView)findViewById(R.id.resultadoTextView);
        pantallaOperaciones.setText(textoOperaciones);




    }


    public void onClickNumero(View v){
        if(resultado != ""){
            limpiar();
            mostrarOperaciones();
        }
        Button b = (Button) v; //Creamos un botón para después coger su contenido (String)
        textoOperaciones += b.getText(); //Agregamos a la cadena textoOperaciones el String (número) del botón.
        mostrarOperaciones(); //Llamamos al método mostrarOperaciones() para mostrar la cadena textoOperaciones.
    }

    private boolean esOperador(char op){
        switch (op){
            case '+':
            case '-':
            case 'x':
            case '÷':return true;
            default: return false;
        }
    }

    public void onClickOperador(View v) {

        if (textoOperaciones == "") {
            if (v.getId() == R.id.botonResta) {
                textoOperaciones += "-";
                mostrarOperaciones();
            }
            if (v.getId() == R.id.botonRaiz) {
                textoOperaciones += "√";
                mostrarOperaciones();
            }
            return;
        }

        Button b = (Button) v; //Creamos un botón para después coger su contenido (String)

        if (resultado != "") {
            String auxTextoOperaciones = resultado; //Creamos la variable auxTextoOperaciones y le asignamos el valor de String resultado.
            limpiar(); //Llamamos a limpiar() para vaciar la información.
            textoOperaciones = auxTextoOperaciones;
        }

        if (operadorActual != "") {//Si hay un operador...

            if (esOperador(textoOperaciones.charAt(textoOperaciones.length() - 1))) {//Si pulsamos otro operador...
                if (v.getId() == R.id.botonResta) {
                    textoOperaciones += "-";
                    mostrarOperaciones();
                }
                if (v.getId() == R.id.botonRaiz) {
                    textoOperaciones += "√";
                    mostrarOperaciones();
                }
                textoOperaciones = textoOperaciones.replace(textoOperaciones.charAt(textoOperaciones.length() - 1), b.getText().charAt(0));//Reemplaza el primer operador por el segundo operador pulsado.
                mostrarOperaciones();//Llamamos al método updateScreen() y muestra lo que hay en textoOperaciones.
                return;
            } else {// Si no se vuelve a pulsar un operador...
                obtenerResultado(); //Llamamos al método obtenerResultado() y guardamos el resultado.
                textoOperaciones = resultado; // Guardamos en la pantalla el resultado
                resultado = ""; // Vaciamos resultado.
            }textoOperaciones +=  b.getText().toString();
            //operadorActual = b.getText().toString(); // Guardamos el String (símbolo) del botón.

            mostrarOperaciones(); //Llamamos al método mostrarOperaciones() para mostrar la cadena textoOperaciones.
        }
            textoOperaciones += b.getText(); //Agregamos a la cadena textoOperaciones el String (símbolo) del botón.
            operadorActual = b.getText().toString();//Guardamos en operadorActual el String(símbolo) del botón pulsado.
            mostrarOperaciones(); //Llamamos al método mostrarOperaciones() para mostrar la cadena textoOperaciones.
        }


    public void onClickIgual(View v){
        if(textoOperaciones == "") return;//Si no hemos introducido nada y le damos a igual para que no haga nada
        if(!obtenerResultado()) return;// Si no hemos introducido una operación para que no haga nada.

        pantallaResultado.setText(String.valueOf(resultado)); //Mostramos el valor del resultado.
    }
    public void onClickLimpiar(View v){
        limpiar(); //Llama al método limpiar() que limpia la información.
        mostrarOperaciones(); //Llamamos al método mostrarOperaciones() para mostrar la cadena textoOperaciones que estará vacía.
    }
    private boolean obtenerResultado(){
        if(operadorActual == "") return false; //Si no hemos pulsado un operador devuelve falso
        String[] operacion = textoOperaciones.split(Pattern.quote(operadorActual)); //Divide el String textoOperaciones para guardarlo en un array de Strings.
        if(operacion.length < 2) return false; //Si solo hay dos elementos devuelve falso
        resultado = String.valueOf(operacion(operacion[0], operacion[1], operadorActual)); //Guardamos en resultado el resultado de la operación, que se realiza en el método operación.
        return true; //Si hay más de dos Strings devuelve verdadero.
    }

    private double operacion(String a, String b, String op){
        switch (op){
            case "+": return Double.valueOf(a) + Double.valueOf(b); //Devuelve la operación de suma.
            case "-": return Double.valueOf(a) - Double.valueOf(b);//Devuelve la operación de resta.
            case "x": return Double.valueOf(a) * Double.valueOf(b);//Devuelve la operación de multiplicación.
            case "÷": try{
                return Double.valueOf(a) / Double.valueOf(b);//Devuelve la operación de división.
            }catch (Exception e){
                //Estaría bien poner un Toast, división por cero...
            }
            default: return -1; // Si es no es ninguno de esos operadores devuelve -1.
        }
    }

    private void limpiar(){
        textoOperaciones = ""; //Limpia la pantalla .
        operadorActual = ""; //Matta el valor del operador actual.
        pantallaResultado.setText( resultado = ""); //Limpia el resultado.
    }

    private void mostrarOperaciones(){ //Método que muestra las operaciones.
        pantallaOperaciones.setText(textoOperaciones);
    }

    public void BotonSalirPulsado(View v){
        finishAffinity();//Para salir de la aplicación. Mínimo API 16, por eso marca como error, porque el mínimo establecido es el 15.
    }

}


