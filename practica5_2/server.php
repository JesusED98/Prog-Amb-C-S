<?php 

class MiClase 
{ 
    public function MetodoSaludar() 
    { 
        return 'Hola ' . func_get_args()[0] . PHP_EOL; 
    } 
} 

try { 
    $server = new SoapServer( 
        null, 
        [ 
            'uri' => 'http://localhost/PAC-S/practica5_2/server.php', 
        ] 
    ); 
    
    $server->setClass('MiClase'); 
    $server->handle(); 
} 
catch (SOAPFault $f) { 
    print $f->faultstring; 
}
