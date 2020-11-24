<?php 
// Headers 
// indicamos si los recursos de la rspuesta pueden ser compartidos con el origen dado 
header('Access-Control-Allow-Origin: *'); 
// indicamos el tipo de recurso 
header('Content-Type: application/json; charset=utf-8'); 

// incluimos las configuraciones de base de datos 
include_once '../../config/database.php'; 
//incluimos el modelo post 
include_once '../../models/posts.php'; 

// Instanciamos DB & conectamos 
$database = new Database(); 
$db = $database->connect(); 

// Instanciamos un objeto post 
$post = new Post($db); 

// consultamos los posts del blog 
$result = $post->read(); 
// obtenemos el numero de renglones 
$num = $result->rowCount(); 

// verificamos si hay posts 
if ($num > 0) { 
    // Creamos arreglo para el conjunto de registros consultados 
    $posts_arr = array(); 
    // $posts_arr['data'] = array(); 
    
    while ($row = $result->fetch(PDO::FETCH_ASSOC)) { 
        // extraemos las variables que trae el arreglo $row. 
        extract($row); 
        // y las utilizamos para formar un arreglos de datos personalizado 
        /**las variables extraidas son; 
        * id, titulo, cuerpo, autor, categoria_id y categoria_nombre */ 
        $post_item = array( 
            'id' => $id, 
            'title' => utf8_encode($titulo), 
            'body' => utf8_encode(html_entity_decode($cuerpo)), 
            'author' => utf8_encode($autor), 
            'category_id' => $categoria_id, 
            'category_name' => utf8_encode($categoria_nombre)
        ); 
        
        // Insertamos los registros al final del array $post_arr 
        array_push($posts_arr, $post_item); 
        // array_push($posts_arr['data'], $post_item); 
    } 
    /** Convertimos a JSON los resultados y 
    * devolvemos la peticion como salida segun el Content-Type */ 
    echo json_encode($posts_arr); 
} else { 
    // No hay posts 
    echo json_encode( 
        array('message' => 'No hay Publicaciones') 
    ); 
}
