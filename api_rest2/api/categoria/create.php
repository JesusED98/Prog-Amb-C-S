<?php 
// Headers 
header('Access-Control-Allow-Origin: *'); 
header('Content-Type: application/json; charset=utf-8'); 
header('Access-Control-Allow-Methods: POST'); 
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, 
Content-Type, Access-Control-Allow-Methods, Authorization,X-Requested-With'); 

include_once '../../config/database.php'; 
include_once '../../models/Categorias.php'; 

// Instanciamos DB & conectamos 
$database = new Database(); 
$db = $database->connect(); 

// Instanciamos objeto categoria
$category = new Category($db); 

// obtenemos datos 
$data = json_decode(file_get_contents("php://input")); 
$category->name = $data->Xnombre; 

// Creamos Categoria 
if($category->create()) { 
    echo json_encode( 
        array('message' => 'Categoria Creada') 
    ); 
} else { 
    echo json_encode( 
        array('message' => 'Categoria No Creada') 
    ); 
}
