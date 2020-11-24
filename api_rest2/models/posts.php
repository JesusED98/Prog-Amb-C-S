<?php 
class Post { 
    // variables para uso d la BD 
    private $conn; 
    private $table = 'posts';    

    // Properties de la clase 
    public $id; 
    public $categoria_id; 
    public $categoria_nombre; 
    public $titulo; 
    public $cuerpo; 
    public $autor; 
    public $creado_en; 
    
    // Constructor 
    // inicializamos conexion 
    public function __construct($db) { 
        $this->conn = $db; 
    } 
    
    // metodo para leer Posts 
    public function read() { 
        // Creamos query 
        $query = 'SELECT 
                    c.nombre as categoria_nombre, 
                    p.id, p.categoria_id, 
                    p.titulo, 
                    p.cuerpo, 
                    p.autor, 
                    p.creado_en 
                FROM ' . $this->table . ' p 
                LEFT JOIN 
                    categoria c ON p.categoria_id = c.id 
                ORDER BY 
                    p.creado_en DESC '; 
        
        // creamos la sentencia preparada 
        $stmt = $this->conn->prepare($query);
        
        // ejecutamos la sentencia 
        $stmt->execute(); 
        
        // retornamos el resultado de la consulta 
        return $stmt; 
    }
    
    // metodo para obtener una publicaacion especifica 
    public function read_single() { 
        // Create query 
        $query = 'SELECT 
                    c.nombre as categoria_nombre, 
                    p.id, p.categoria_id, 
                    p.titulo, 
                    p.cuerpo, 
                    p.autor, 
                    p.creado_en 
                FROM ' . $this->table . ' p 
                        LEFT JOIN 
                            categoria c ON p.categoria_id = c.id 
                    WHERE 
                        p.id = ? 
                        LIMIT 0,1'; 
                        /** la linea p.id = ? sinifica que usa parametros 
                        * de sustición de signos de interrogación */ 
        // preparamos la sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // vinculamos la posición índice 1 del parámetro. 
        $stmt->bindParam(1, $this->id); 
        
        // ejecutamos sentencia
        $stmt->execute(); 
        
        /** obtenemos un array indexado por los nombres 
        * de las columnas del conjunto de resultados.*/ 
        $row = $stmt->fetch(PDO::FETCH_ASSOC); 
        
        // establecemos propiedades 
        $this->titulo = $row['titulo']; 
        $this->cuerpo = $row['cuerpo']; 
        $this->autor = $row['autor']; 
        $this->categoria_id = $row['categoria_id']; 
        $this->categoria_nombre = $row['categoria_nombre']; 
    }
    
    // Creamos publicacion Post 
    public function create() 
    { 
        // Creamos query 'una forma diferente de insertar' 
        $query = 'INSERT INTO ' . $this->table . ' 
        SET 
            titulo = :titulo, 
            cuerpo = :cuerpo, 
            autor = :autor, 
            categoria_id = :categoria_id'; 
        /** las lineas con dos puntos ':' sinifica que usa parametros
        * de sustición de nombres como ':nombre' */ 
        
        // Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // sanitizamos los valores a insertar 
        // htmlspecialchars: Convierte caracteres especiales en entidades HTML 
        // strip_tags: Retira las etiquetas HTML y PHP de un string 
        $this->titulo = htmlspecialchars(strip_tags($this->titulo)); 
        $this->cuerpo = htmlspecialchars(strip_tags($this->cuerpo)); 
        $this->autor = htmlspecialchars(strip_tags($this->autor)); 
        $this->categoria_id = htmlspecialchars(strip_tags($this->categoria_id)); 
        
        // enlazamos datos con los parametros 
        $stmt->bindParam(':titulo', $this->titulo); 
        $stmt->bindParam(':cuerpo', $this->cuerpo); 
        $stmt->bindParam(':autor', $this->autor); 
        $stmt->bindParam(':categoria_id', $this->categoria_id); 
        
        // Ejecutamos la query 
        if ($stmt->execute()) { 
            //retornamos el estado satisfactorio, 
            //es decir, que sí se pudo ejecutar 
            return true; 
        } 
        
        // mostramos error si hubiese algun error 
        $error = "Error: \n" . $stmt->error; 
        print($error); 
        // retornamos el estado insatisfactorio 
        return false; 
    }
    
    // Creamos actualizar publicacion 
    public function update() 
    { 
        // Create query 
        $query = 'UPDATE ' . $this->table . ' 
                                SET 
                                    titulo = :titulo, 
                                    cuerpo = :cuerpo, 
                                    autor = :autor, 
                                    categoria_id = :categoria_id 
                                WHERE id = :id'; 

        // Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // sanitizamos los valores a insertar 
        // htmlspecialchars: Convierte caracteres especiales en entidades HTML 
        // strip_tags: Retira las etiquetas HTML y PHP de un string 
        $this->titulo = htmlspecialchars(strip_tags($this->titulo)); 
        $this->cuerpo = htmlspecialchars(strip_tags($this->cuerpo)); 
        $this->autor = htmlspecialchars(strip_tags($this->autor)); 
        $this->categoria_id = htmlspecialchars(strip_tags($this->categoria_id));
        $this->id = htmlspecialchars(strip_tags($this->id)); 
        
        // enlazamos datos con los parametros 
        $stmt->bindParam(':titulo', $this->titulo); 
        $stmt->bindParam(':cuerpo', $this->cuerpo); 
        $stmt->bindParam(':autor', $this->autor); 
        $stmt->bindParam(':categoria_id', $this->categoria_id); 
        $stmt->bindParam(':id', $this->id); 
        
        // Ejecutamos query 
        if ($stmt->execute()) { 
            //retornamos el estado satisfactorio, 
            //es decir, que sí se pudo ejecutar 
            return true; 
        } 
        
        // mostramos error si hubiese algun error 
        printf("Error: %s.\n", $stmt->error); 
        // retornamos el estado insatisfactorio 
        return false; 
    }
    
    public function delete() 
    { 
        // Creamos query 
        $query = 'DELETE FROM ' . 
                    $this->table .
                    ' WHERE id = :id'; 
        
        // Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // sanitizamos 
        $this->id = htmlspecialchars(strip_tags($this->id)); 
        
        // vinculamos parametro 
        $stmt->bindParam(':id', $this->id); 
        
        // Ejecutamos query 
        if ($stmt->execute()) { 
            return true; 
        } 
        
        // Mostramos error si algo sale incorrecto 
        print("Error: \n". $stmt->error);
        
        return false; 
    }
}
