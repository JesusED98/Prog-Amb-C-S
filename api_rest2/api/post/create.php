<?php 
// Headers 
// indicamos si los recursos de la rspuesta pueden ser compartidos con el origen dado 
header('Access-Control-Allow-Origin: *'); 
// indicamos el tipo de recurso 
header('Content-Type: application/json'); 
//indicamos qué métodos HTTP están permitidos en este punto final 
// particular para solicitudes de origen cruzado 
//como le vamos a enviar datos json por POST entonces limitamos/ permitimos solo a POST 
header('Access-Control-Allow-Methods: POST'); 
//en respuesta a la solicitud que hagamos sobre este archivo, 
// indicamos cuáles encabezados HTTP pueden ser usados durante dicha solicitud. 
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type, 
Access-Control-Allow-Methods, Authorization, X-Requested-With');

include_once '../../config/database.php'; 
include_once '../../models/posts.php'; 

// Instanciamos DB y conectamos 
$database = new Database(); 
$db = $database->connect(); 

// Instanciamos objeto post 
$post = new Post($db); 

// Obtenemos datos enviados 
//usamos la dirección especial 'php: // input' 
// para recuperar datos JSON como una cadena 
// es decir, este archivo lo usaremos para enviarle datos tipo json 
$data = json_decode(file_get_contents("php://input")); 

//configuramos el objeto recien creado 
$post->titulo = $data->xtitulo; 
$post->cuerpo = $data->xcuerpo; 
$post->autor = $data->xautor; 
$post->categoria_id = $data->xcategoria_id; 

// Creamos publicacion 
// si fue satisfactorio mostramos que si fue creado 
if ($post->create()) { 
    echo json_encode( 
        array('message' => 'Publicacion Creada') 
    ); 
} else { 
    echo json_encode( 
        array('message' => 'Publicacion NO Creada') 
    );
}
