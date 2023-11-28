package com.ulicesp10.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast


class Calculadora : AppCompatActivity() {
    private lateinit var  valor:TextView;
    private  var operador:String="";
    private  var  auxiliar:String="";
    private lateinit var Mas:TextView
    private lateinit var Menos:TextView
    private lateinit var Mult:TextView
    private lateinit var Div:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)
        iniciarOperadores()
    }
    fun iniciarOperadores(){
        valor   = findViewById(R.id.tvTotal)
        valor.setText("0")
        Mas = findViewById(R.id.signo_mas)
        Menos = findViewById(R.id.signo_menos)
        Mult = findViewById(R.id.signo_por)
        Div = findViewById(R.id.signo_div)
    }
    fun desactivarOperadores(){
        Mas.setBackgroundResource(R.color.inactivo)
        Menos.setBackgroundResource(R.color.inactivo)
        Mult.setBackgroundResource(R.color.inactivo)
        Div.setBackgroundResource(R.color.inactivo)
    }
    fun bottonClicked(view: View){

        when(view.getId()){
            R.id.cero->{
                concatenar("0")
            }
            R.id.numero_1->{
                concatenar("1")
            }
            R.id.numero_2->{
                concatenar("2")
            }
            R.id.numero_3->{
                concatenar("3")
            }
            R.id.numero_4->{
                concatenar("4")
            }
            R.id.numero_5->{
                concatenar("5")
            }
            R.id.numero_6->{
                concatenar("6")
            }
            R.id.numero_7->{
                concatenar("7")
            }
            R.id.numero_8->{
                concatenar("8")
            }
            R.id.numero_9->{
                concatenar("9")
            }
            R.id.punto->{
                if(!valor.text.contains("."))
                    concatenar(".")

            }
            R.id.signo_c->{
                valor.setText("0")
                operador = "";
                desactivarOperadores()
            }
            R.id.signo_del->{
                if(valor.text.length>0) {
                    var aux = valor.text.subSequence(0, valor.text.length - 1)
                    valor.setText(aux)
                }
                if(valor.text.length==0){
                    valor.setText("0")
                }
            }

        }
    }

    fun bottonClickedOperaciones(view: View){

        when(view.id){
            R.id.signo_mas ->{
                Mas.setBackgroundResource(R.color.activo)

                realizarOporacion("+")

            }
            R.id.signo_menos->{

                if(valor.text.equals("0") &&operador.equals(""))
                    valor.setText("-")
                else{
                    Menos.setBackgroundResource(R.color.activo)
                    realizarOporacion("-")
                }
            }R.id.signo_div->{
                Div.setBackgroundResource(R.color.activo)
                realizarOporacion("/")
            }
            R.id.signo_por->{
                Mult.setBackgroundResource(R.color.activo)
                realizarOporacion("*")
            }R.id.signo_igual->{
                if (!operador.equals(""))
                realizarOporacion("=")
            }

        }
    }
    fun realizarOporacion(Operacion:String){
        if (Operacion.equals("=")) {
            var valor2 = valor.text.toString()
            valor.text = Operar(operador, auxiliar, valor2)

        }
        else {
            if (operador.equals("")) {
                operador = Operacion;
                auxiliar = valor.text.toString()
                valor.setText("0")
            } else {
                var valor2 = valor.text.toString()
                valor.text = Operar(operador, auxiliar, valor2)
                if (valor.text.contains("0.0")) valor.setText("0")
            }
        }
    }
    fun concatenar(num:String){
        desactivarOperadores()
        if(valor.text.equals("0") && valor.text.length==1 || valor.text.contains("Error")){
            valor.setText(num)

        }else {

            var aux = "" + valor.text + num
            valor.setText(aux)
        }
    }
    fun Operar(operacion:String, n1:String, n2: String): String {
        when(operacion) {
            "+" -> {
                operador = ""
                if (n1.contains(".") || n1.contains(""))
                {
                return  (n1.toBigDecimal() + n2.toBigDecimal()).toString()
                }
                else{
                    return (n1.toBigInteger()+n2.toBigInteger()).toString()
                }


            }
            "-"->{
                operador=""
                if(n1.contains(".") || n1.contains(""))
                    return  (n1.toBigDecimal()-n2.toBigDecimal()).toString()
                else
                    return (n1.toBigInteger()-n2.toBigInteger()).toString()
            }
            "*"->{
                operador=""
                if(n1.contains(".") || n1.contains(""))
                    return  (n1.toBigDecimal()*n2.toBigDecimal()).toString()
                else
                    return (n1.toBigInteger()*n2.toBigInteger()).toString()
            }
            "/"->{
                operador=""
                try {
                    if(n1.contains(".") || n1.contains(""))
                        return  (n1.toBigDecimal()/n2.toBigDecimal()).toString()
                    else
                        return (n1.toBigInteger()/n2.toBigInteger()).toString()
                }catch (e: ArithmeticException){
                    return "Error"
                }

            }


        }

        return "";

    }

}


