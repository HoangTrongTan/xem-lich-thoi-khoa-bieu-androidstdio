<?php 
    include_once("connect.php");
    $db = new database();

    if(isset($_POST['loaithongbao'])){
        $loaithongbao = (int)$_POST['loaithongbao'];
        $sql = "SELECT * FROM `thongbao` WHERE `loaithongbao` = $loaithongbao";
        $rs = $db->thucthi($sql);
        $json = array();
        while($row = mysqli_fetch_assoc($rs)){
            $json[] = ($row);
        }
        // $db->test($json);
        print_r(json_encode($json));

    }else{
        echo "0";
    }
        
?>