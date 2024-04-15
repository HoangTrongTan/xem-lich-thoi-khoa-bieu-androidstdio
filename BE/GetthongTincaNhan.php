<?php
    include_once('connect.php');
    $db = new database();
    if(isset($_POST['idsinhvien'])){
        $idsinhvien = (int)($_POST['idsinhvien']);
        $sql = "SELECT `sinhvien`.`idsinhvien`,`sinhvien`.`hoten`,`lop`.`tenlop`,`khoa`.`tenkhoa`,`chuyennganh`.`TenChuyenNganh`,`hedaotao`.`SoNam`,`sinhvien`.`NgaySinh`,`sinhvien`.`GioiTinh`,`sinhvien`.`Dienthoai`,`sinhvien`.`Email` 
        FROM ( ( (`sinhvien` 
        INNER JOIN `lop` ON `sinhvien`.`idlop` = `lop`.`idlop`) 
        INNER JOIN `khoa` ON `lop`.`idkhoa` = `khoa`.`idkhoa`) 
        INNER JOIN `chuyennganh` ON `chuyennganh`.`idchuyennganh` = `sinhvien`.`idchuyennganh` ) 
        INNER JOIN `hedaotao` ON `hedaotao`.`idHeDaoTao` = `sinhvien`.`idHeDaoTao` 
        WHERE `sinhvien`.`idsinhvien` = $idsinhvien";

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