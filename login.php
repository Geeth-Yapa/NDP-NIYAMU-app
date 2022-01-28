<?php

define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'root');
define('DB_PASSWORD', 'root');
define('DB_DATABASE', 'ndp');

$con = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

$myusername = $_POST['username'];
      $mypassword = $_POST['password'];
      $sql = "SELECT id,name,points FROM users WHERE username = '$myusername' and password = '$mypassword'";
$result = mysqli_query($con,$sql);
$data=array();
	while($row=mysqli_fetch_assoc($result)){
                // $data["data"][]=$row;
                $data=$row;
        }
header('Content-Type:Application/json');
echo json_encode($data);
?>
