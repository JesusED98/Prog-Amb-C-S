<?php 
// Headers 
header('Access-Control-Allow-Origin: *'); 
header('Content-Type: application/json'); 

include_once '../../config/database.php'; 
include_once '../../models/posts.php'; 

// Instanciamos BD y conectamos 
$database = new Database(); 
$db = $database->connect(); 

// Instanciamos objeto post 
$post = new Post($db); 

// obtenemos id si existe, si no, salimos del script 
$post->id = isset($_GET['id']) ? $_GET['id'] : die(); 

// obtenemos la publicacion 
$post->read_single(); 

// Creamos arreglo 
$post_arr = array( 
    'id' => $post->id, 
    'title' => utf8_encode($post->titulo), 
    'body' => utf8_encode($post->cuerpo), 
    'author' => utf8_encode($post->autor), 
    'category_id' => $post->categoria_id, 
    'category_name' => utf8_encode($post->categoria_nombre) 
);

// creamos JSON 
echo (json_encode($post_arr));