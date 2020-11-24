<?php 
// Headers
header('Access-Control-Allow-Origin: *'); 
header('Content-Type: application/json'); 

include_once '../../config/database.php'; 
include_once '../../models/Categorias.php'; 

// Instanciamos DB & conectamos 
$database = new Database(); 
$db = $database->connect(); 

// Instanciamos objeto categoria 
$category = new Category($db); 

// obtenemos las categorias 
$result = $category->read(); 

// obtenemos numro de registros 
$num = $result->rowCount(); 

// verificamos si hay registros 
if($num > 0) { 
    // arreglo de categorias 
    $cat_arr = array(); 
    $cat_arr['data'] = array(); 
    
    while($row = $result->fetch(PDO::FETCH_ASSOC)) { 
        extract($row); 
        
        $cat_item = array(
            'id' => utf8_encode($id), 
            'name' => utf8_encode($nombre) 
        ); 
        
        // armamos datos "data" 
        array_push($cat_arr['data'], $cat_item); 
    } 
    
    // damos salida a consulta en JSON 
    echo json_encode($cat_arr); 

} else { 
    // sin Categorias 
    echo json_encode( 
        array('message' => 'No hay Categorias') 
    ); 
}
