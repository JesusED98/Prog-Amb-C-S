<?php 
class Stock 
{ 
    /**Creamos el metodo vew que es el que esccribiremos en la URL 
    * este metodo devolvera un arreglo --la consulta de BD */ 
    public function view():array 
    { 
        /**creamos conexion con base de datos */ 
        $con = new PDO('mysql: host=localhost; dbname=filial;','root','');
        /**creamos el sting de consulta o QueryString */ 
        $sql = "SELECT * FROM stock ORDER BY id ASC";
        /**Preparamos la consulta --ahora tenemos un objeto sentencia*/ 
        $SQL = $con->prepare($sql); 
        /**ejecutamos la sentencia */ 
        $SQL->execute(); 
        
        /**creamos un arreglo para los resultados */ 
        $resultados = array(); 
        
        /**Obtiene la siguiente fila de un conjunto de resultados 
        * mientras los halla */ 
        while($row = $SQL->fetch(PDO::FETCH_ASSOC)){ 
            /**y si los obtoene los va agregando al arreglo de resultados */ 
            $resultados[] = $row; 
        } 
        if(!$resultados){ 
            throw new Exception('Ningun producto en Stock'); 
        } 
        return $resultados; 
    } 
}
