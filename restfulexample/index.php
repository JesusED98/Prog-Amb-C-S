<?php 
//header("HTTP/1.1 400 OK");
//header("HTTP/1.1 500 OK");
header("HTTP/1.1 200 OK");
echo '<h1>'.$_SERVER['REQUEST_METHOD'].'</h1>';
