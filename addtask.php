<?php

define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'root');
define('DB_PASSWORD', 'root');
define('DB_DATABASE', 'ndp');

$con = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

        $userid = $_POST['userid'];
        $task = $_POST['task'];
        $taskid = $_POST['taskid'];
        $comment = $_POST['comment'];
        $points = 0;

        switch($taskid){
                case 0: $points = 8; break;
                case 1: $points = 10; break;
                case 2: $points = 5; break;
                case 3: $points = 6; break;
                case 4: $points = 7; break;
                case 6: $points = 4; break;
        }


	$current_score_sql = "SELECT points FROM users WHERE id  = '$userid'";
        $current_score = 0 ;

        $inserttask_sql = "INSERT INTO tasks (userid, task, date, comment) VALUES ('$userid', '$task', current_timestamp, '$comment')";
        $result = mysqli_query($con,$current_score_sql);

        $data=array();
        while($row=mysqli_fetch_assoc($result)){
                // $data["data"][]=$row;
                $current_score=(int) $row['points'];
        }
        $newpoints =(int) $points + $current_score;
        $update_sql = "UPDATE users SET points = '$newpoints' WHERE id = '$userid'";

        if(mysqli_query($con,$inserttask_sql)){
                if(mysqli_query($con,$update_sql)){
                        header('Content-Type:Application/json');
                        echo json_encode($newpoints);
                }
        }
header('Content-Type:Application/json');
echo null;
?>

