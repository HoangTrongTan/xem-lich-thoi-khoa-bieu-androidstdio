<?php
    include_once('connect.php');
    $db = new database();

        $sql = "SELECT * FROM `quyche_quydinh`";
        $rs = $db->thucthi($sql);
        $json = array();
        while($row = mysqli_fetch_assoc($rs)){
            $json[] = ($row);
        }
        // $db->test($json);
        print_r(json_encode($json));    
?>