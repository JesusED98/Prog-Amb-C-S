<?php 
//incluimos de la libreria que descargamos el archivo nusoap.php 
include_once ('lib/nusoap.php');

//CONFIGURACION 
//creamos el objeto soap_server 
$servicio = new soap_server(); 
 
//creamos el 'nombre' del espacio de nombres via URN y le llamamos 'miserviciowsdl' 
$NameSpace = "urn:miserviciowsdl"; 
 
// - Ahora definimos el espacio de nombres 
$servicio->schemaTargetNamespace = $NameSpace; 
 
//Ahora nombramos 'El Servicio' y lo vinculamos con el NameSpace o espacio de nombres 
$servicio->configureWSDL("MiPrimerServicioWeb",$NameSpace);  
 
//registramos el servicio, configurando cuatro argumentos 
// Es decir, es lo que envía el cliente 
// primero: el nombre de la función que se va a poner en servicio -> MiFuncion 
// segundo: los parametros de entrada a MiFuncion, serán dos, incluso pueden ser mas 
//          y esto lo hacemos por medio de un arreglo (un arreglo de parametros) 
//          num1 y num2 de tipo integer 
//          Estos se vinculan a los parametros de la funcion que pondremos en servicio 
// tercero: el o los valores de retorno, tambien en forma de arreglo de tipo string 
//          Este se vincula con el valor de retorno de la funcion 
// cuarto el namespace al que lo queremos vincular 
$servicio->register( 
	"MiFuncion", 
	array('num1' => 'xsd:integer', 'num2' => 'xsd:integer'), 
	array('return' => 'xsd:string'), 
	$NameSpace 
); 

$servicio->register( 
	"MiFuncion2", 
	array('num1' => 'xsd:integer', 'num2' => 'xsd:integer'), 
	array('return' => 'xsd:string'), 
	$NameSpace 
);

$servicio->register( 
	"MiFuncion3", 
	array('num1' => 'xsd:integer', 'num2' => 'xsd:integer'), 
	array('return' => 'xsd:string'), 
	$NameSpace 
);

//EL SERVICIO -- LOS METODOS, etc 
// Lo que se envía al cliente --El resultado 
// Ahora por fin creamos la funcion que pondremos al servicio de otros programadores
// Esta función solamente suma dos números --algo sencillo 
function MiFuncion($num1, $num2){ 
	$resultadoSuma = $num1 + $num2; 
	$resultado = "El resultado de la suma de " . $num1 . "+" .$num2 . " es: " . $resultadoSuma; 
	return $resultado; 
} 

function MiFuncion2($num1, $num2){ 
	$resultadoresta = $num1 - $num2; 
	$resultado = "El resultado de la resta de " . $num1 . "-" .$num2 . " es: " . $resultadoresta; 
	return $resultado2; 
} 

function MiFuncion3($num1, $num2){ 
	$resultadomulti = $num1 * $num2; 
	$resultado = "El resultado de la multiplicacion de " . $num1 . "*" .$num2 . " es: " . $resultadomulti; 
	return $resultado3; 
} 

//EJECUCION -- DE ACUERO A LO QUE ENVIE EL CLIENTE 
// ejecutamos el servicio, es decir, lo ponemos al servicio de los demás vía POST 
$servicio->service(file_get_contents("php://input"));
