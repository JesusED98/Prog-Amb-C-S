<?php 
/** En este cliente vamos aprobar la direccion 
*donde se encuentra el servicio REST 
*Podemos probar desde otra ubicación */ 
/**URL del servicio REST */ 
$url = 'http://localhost/PAC-S/apirest/api/v1'; 
/**la clase que necesitamos y obtenemos de algun lado 
* ademas del metodo que necesitamos tambien */ 
$clase = 'stock'; 
$metodo = 'view'; 

/**Armamos la cadena completa para procesar */ 
$cad = $url . '/' . $clase . '/' . $metodo; 

/**Lee todo el archivo en una cadena 
*  es decir, obtiene el contenido de un archivo a una cadena 
*  y como lo que se obtiene es un JSON 
*  pues es lo que obtenemos */ 
$retorno = file_get_contents($cad); 

/**ya sea que lo utilicemos como json 
*  o como arreglo y en ese caso lo decodificamos 
*  con json_decode() */ 
$miArreglo = json_decode($retorno,1); 

//verificamos lo que obtiene 
var_dump($miArreglo);
