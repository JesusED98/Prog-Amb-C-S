<?php 
//incluimos de la libreria que descargamos, el archivo nusoap.php 
include_once ('lib/nusoap.php'); 

// creamos el cliente a partir de la clase nusoap_client 
// nos pide dos argumentos 
// el primero: la URI donde se encuentra el servicio y 
// el segundo es booleano true si la URI tiene WSDL y false si No 
//    se puede usar con false pero sin '?wsdl' 
//    $cliente = new nusoap_client("http://127.0.0.1/PAC-s/practica5_3a/servicio.php",false) 
$cliente = new nusoap_client("http://127.0.0.1/PAC-s/practica5_3a/servicio.php",false); 

//ahora de manera directa y para probar creamos dos variables 
$n1 = 45; 
$n2 = 15; 

//Ahoara creamos los parametros para la funcion del servicio 
// El servicio me pide un arreglo con dos valores 
// uno para 'num1' y otro para 'num2' 
$parametros = array('num1'=>$n1,'num2'=>$n2); 

//ahora creamos la variable que va a tener el valor de retorno 
// de la funcion en servicio 
// utilizamos el método call que necesita dos argumentos 
// el primero es la funcion que quiero utilizar del servicio 
// el segundo son los valores que ya tenemos en la variable 
//    $parametros y que necesita la funcion: "MiFuncion(num1,num2)" 
$Respuesta = $cliente->call("MiFuncion",$parametros);  

// finalmente mostramos la respuesta del servicio 
print_r($Respuesta);
