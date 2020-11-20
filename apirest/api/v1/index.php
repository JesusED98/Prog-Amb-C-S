<?php 
/*con la siguiente linea configuramos la pagina para 
el tipo de dato que retornara, es decir json*/
header('Content-Type: application/json; charset=utf-8'); 

/** como vamos a crear un objeto anonimo de la clase stock 
* incluiremos el archivo que lo contiene */ 
require_once('./clases/stock.php'); 

class Rest 
{ 
    public static function open($peticion) 
    { 
        $url = explode('/', $peticion['url']); 
        
        /**obtenemos la clase */ 
        $clase = ucfirst($url[0]); 
        array_shift($url); 
        
        /**obtenemos el metodo */ 
        $metodo = $url[0]; 
        array_shift($url); 
        
        /**obtenemos los parametros */ 
        $parametros = array(); 
        $parametros = $url; 
        try { 
            /**si existe la clase y el metodo */ 
            if (class_exists($clase)) { 
                if (method_exists($clase, $metodo)) { 
                    /**mandamos llamar al metodo de la clase stock con los parametros 
                    * obteniendo el resultado de la consulta*/ 
                    $retorno = call_user_func_array(array(new $clase, $metodo), $parametros);
                    
                    /**regresamos el resultado del metodo de la clase(la consulta) 
                    * en formato JSON */ 
                    return json_encode(array('status' => 'satisfactorio', 'datos' => $retorno)); 
                } else { 
                    return json_encode(array('status' => 'Error', 'datos' => 'Método inexistente')); 
                } 
            } else { 
                return json_encode(array('status' => 'Error', 'datos' => 'Claseinexistente'));
            } 
        } catch (Exception $ex) { 
            return json_encode(array('status' => 'Error', 'datos' => $ex->getMessage())); 
        } 
    } 
} 

/**Aqui es donde usamos el metodo open de la clase rest 
* que hace uso de stock */ 
if (isset($_REQUEST)) { 
    echo Rest::open($_REQUEST);
}
