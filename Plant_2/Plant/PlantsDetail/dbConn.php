<?php
		$DB_USER='root'; 
		$DB_PASS=''; 
		$DB_HOST='localhost';
$LangType='utf8';
		$DB_NAME='plantsdetail';

		$mysqli = new mysqli($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME);
//$connection_str = "mysql:root=$host;ecommercedatabase=$db;charset=utf8";
		mysqli_set_charset($mysqli, 'utf8');
		if (mysqli_connect_errno()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
		}	

?>