<?php 
class Category 
{ 
    // variables para uso d la BD 
    private $conn; 
    private $table = 'categoria'; 
    
    // Propiedades 
    public $id; 
    public $name; 
    public $created_at; 
    
    // Constructor 
    // inicializamos conexion 
    public function __construct($db) 
    {
        $this->conn = $db; 
    } 
    
    // m[etodo para obtener categorias 
    public function read() 
    { 
        // Create query 
        $query = 'SELECT 
                        id, 
                        nombre, 
                        creado_en 
                    FROM 
                        ' . $this->table . ' 
                    ORDER BY 
                        creado_en DESC'; 
        
        // creamos la sentencia preparada 
        $stmt = $this->conn->prepare($query); 
        
        // Ejecutamos query 
        $stmt->execute(); 
        
        //// retornamos el resultado de la consulta 
        return $stmt; 
    } 
    
    // metodo para obtener una categoria especifica 
    public function read_single() 
    { 
        // Creamos query 
        $query = 'SELECT 
                        id, 
                        nombre 
                    FROM 
                        ' . $this->table . ' 
                    WHERE id = ? 
                    LIMIT 0,1'; 
        
        /**usamos parametros de sustición de signos de interrogación */ 
        
        //Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // vinculamos ID por indice 
        $stmt->bindParam(1, $this->id); 
        
        // Ejecutamos query 
        $stmt->execute(); 
        
        $row = $stmt->fetch(PDO::FETCH_ASSOC); 
        
        // establecemos propiedades 
        $this->id = $row['id']; 
        $this->name = $row['nombre']; 
    }
    
    // Creamos Categoria 
    public function create() 
    { 
        // Creamos Query 
        $query = 'INSERT INTO ' . 
                    $this->table . ' 
                SET 
                    nombre = :name'; 
        
        // Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // sanitizamos datos 
        $this->name = htmlspecialchars(strip_tags($this->name)); 
        
        // vinculamos datos 
        $stmt->bindParam(':name', $this->name); 
        
        // Ejecutamos query 
        if ($stmt->execute()) { 
            return true; 
        } 
        
        // mostramos error si algo sale equívoco 
        print("Error: \n". $stmt->error); 
        return false; 
    } 
    
    // actualizamos Categoria 
    public function update() 
    { 
        // Creamos Query 
        $query = 'UPDATE ' . 
                    $this->table . ' 
                SET 
                    nombre = :name 
                WHERE 
                    id = :id'; 
        
        // Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // sanitizamos datos 
        $this->name = htmlspecialchars(strip_tags($this->name)); 
        $this->id = htmlspecialchars(strip_tags($this->id)); 
        
        // vinculamos datos 
        $stmt->bindParam(':name', $this->name); 
        $stmt->bindParam(':id', $this->id); 
        
        // Ejecutamos query 
        if ($stmt->execute()) { 
            return true; 
        }
        
        // mostramos error si algo sale mal 
        print("Error: \n". $stmt->error); 
        return false; 
    } 
    
    // borrar Categoria 
    public function delete() 
    { 
        // Creamos query 
        $query = 'DELETE FROM ' . $this->table . ' WHERE id = :id'; 
        
        // Preparamos sentencia 
        $stmt = $this->conn->prepare($query); 
        
        // sanitizamos datos 
        $this->id = htmlspecialchars(strip_tags($this->id)); 
        
        // vinculamos datos 
        $stmt->bindParam(':id', $this->id); 
        
        // Ejecutamos query 
        if ($stmt->execute()) { 
            return true; 
        } 
        
        // mostramos error si algo sale mal 
        print("Error: \n". $stmt->error); 
        return false; 
    } 
}
