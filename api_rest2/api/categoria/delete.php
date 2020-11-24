<?php 
// Headers 
header('Access-Control-Allow-Origin: *'); 
header('Content-Type: application/json'); 
header('Access-Control-Allow-Methods: DELETE'); 
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

// establecemos ID para Eliminar 
$category->id = $data->Xid; 

// Eliminamos categoria 
if($category->delete()) { 
    echo json_encode( 
        array('message' => 'Categoria eliminada') 
    ); 
} else { 
    echo json_encode( 
        array('message' => 'Categoria No eliminada') 
    ); 
}
