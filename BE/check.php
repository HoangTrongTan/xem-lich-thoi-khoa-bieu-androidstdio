<?php
    include_once("connect.php");
    $db = new database();
    if($db){
        $response['success'] = 1;
        $response['message'] = "kết nối thành công";

        echo json_encode($response);
    }else{
        $response['success'] = 0;
        $response['message'] = "kết nối thất bại";

        echo json_encode($response);
    }
?>