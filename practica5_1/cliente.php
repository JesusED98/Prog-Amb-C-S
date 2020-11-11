<?php 

$url = "http://ws.cdyne.com/ip2geo/ip2geo.asmx?wsdl"; 

try { 
    $ip = '210.45.151.101 '; //prueba con varias direcciones 
    //$ip = '210.30.111.11 '; //1 
    //$ip = '200.31.101.101 '; //2
    //$ip = '190.35.51.10 '; //3
    //$ip = '180.45.150.81 '; //4
    //$ip = '202.24.109.11 '; //5
    //$ip = '204.28.145.41 '; //6
    //$ip = '160.54.151.11 '; //7
    //$ip = '201.45.101.11 '; //8
    //$ip = '205.75.131.121 '; //9
    //$ip = '210.25.110.110 '; //10
    $client = new SoapClient($url, ["trace" => 1]); 
    $result = $client->ResolveIP(["ipAddress" => $ip, "licenseKey" => "0"]); 
    print_r($result); 
    
    print('<hr/>'); 
    
    print($result->ResolveIPResult->City); 
    print('<br/>'); 
    print($result->ResolveIPResult->StateProvince); 
    print('<br/>'); 
    print($result->ResolveIPResult->Country); 
    print('<br/>'); 
    print($result->ResolveIPResult->Organization); 
    print('<br/>'); 
    print($result->ResolveIPResult->Latitude); 
    print('<br/>'); 
    print($result->ResolveIPResult->Longitude); 
    print('<br/>'); 
    print($result->ResolveIPResult->AreaCode); 
    print('<br/>'); 
    print($result->ResolveIPResult->TimeZone); 
    print('<br/>'); 
    print($result->ResolveIPResult->HasDaylightSavings); 
    print('<br/>'); 
    print($result->ResolveIPResult->Certainty); 
    print('<br/>'); 
    print($result->ResolveIPResult->RegionName); 
    print('<br/>'); 
    print($result->ResolveIPResult->CountryCode); 
    print('<br/>'); 
} catch (SoapFault $e) { 
    echo $e->getMessage();
    } 
echo PHP_EOL;
