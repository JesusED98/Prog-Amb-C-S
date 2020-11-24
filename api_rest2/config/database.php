<?php 
class Database { 
    // DB Parametros para la base de datos 
    private $host = 'localhost'; 
    private $db_name = 'xblog'; 
    private $username = 'root'; 
    private $password = ''; 
    private $conn; 
    
    // DB Connect 
    public function connect() { 
        $this->conn = null; 
        try { 
            /**configuramos la conexion*/ 
            $this->conn = new PDO('mysql:host=' . $this->host . ';dbname=' . $this->db_name, $this->username, $this->password); 
            /** habilitamos el reporte de errores para poder manejar excepciones*/
            $this->conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        } catch(PDOException $e) { 
            echo 'Error de conexion: ' . $e->getMessage(); 
        } 
        /**retornamos el objeto de la conexion*/ 
        return $this->conn; 
    } 
}
