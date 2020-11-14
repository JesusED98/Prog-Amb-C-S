<?php 
$argumento1 = "Mundo!"; 

$client = new SoapClient(null, array( 
    'location' => "http://localhost/PAC-S/practica5_2/server.php", 
    'uri' => "http://localhost/PAC-S/practica5_2/server.php", 
    'trace' => 1 
)); 

try { 
    $result = $client->__soapCall("MetodoSaludar", [$argumento1]); 
    echo $result; 
} catch (SOAPFault $e) { 
    echo $e->getMessage() . PHP_EOL; 
}
