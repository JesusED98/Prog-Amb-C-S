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

// obtenemos ID 
$category->id = isset($_GET['id']) ? $_GET['id'] : die(); 

// obtenemos categoria 
$category->read_single(); 

// Creamos array 
$category_arr = array( 
    'id' => $category->id, 
    'name' => utf8_encode($category->name) 
); 

// Consstuimos JSON 
echo json_encode($category_arr);
