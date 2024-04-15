<?php 
    include_once("connect.php");

    $db = new database();
    if(isset($_POST['NgaySinh']) && isset($_POST['DienThoai']) && isset($_POST['Email']) && isset($_POST['GioiTinh']) && isset($_POST['idsinhvien']) ){
        $NgaySinh = $_POST['NgaySinh'];
        $DienThoai = $_POST['DienThoai'];
        $Email = $_POST['Email'];
        $GioiTinh = (int)$_POST['GioiTinh'];
        $idsinhvien = (int)$_POST['idsinhvien'];
        $sql = "UPDATE `sinhvien` SET `NgaySinh`='$NgaySinh',`GioiTinh`= $GioiTinh,`Dienthoai`='$DienThoai',`Email`='$Email' WHERE `idsinhvien` = $idsinhvien ";
        $rs = $db->thucthi($sql);
        echo $rs;
        
    }else{
        echo "0";
    }
?>