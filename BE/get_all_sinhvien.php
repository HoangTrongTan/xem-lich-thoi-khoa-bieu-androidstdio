<?php 
    include_once("connect.php");
    $db = new database();

    if( isset($_POST['class']) ){
        $lop = $_POST['class'];
        $sql = "SELECT `lop`.tenlop,`sinhvien`.hoten,`sinhvien`.idsinhvien 
        FROM `sinhvien` LEFT JOIN `lop` ON `sinhvien`.idlop = `lop`.idlop 
        WHERE `lop`.tenlop LIKE '%$lop%' ";

        $rs = $db->thucthi($sql);
        $json = array();
        while($row = mysqli_fetch_assoc($rs)){
            $json[] = ($row);
        }
        
        // $db->test($json);
        print_r(json_encode($json));

    }else if( isset($_POST['magv']) && isset($_POST['monhoc']) )
    {
        $monhoc = $_POST['monhoc'];
        $lop = $_POST['magv'];
            $sql = "SELECT DISTINCT `sinhvien`.`hoten`,`lop`.`tenlop`,`sinhvien`.`idsinhvien`  
            FROM `lophoc` INNER JOIN `lop` ON `lophoc`.`idlop` = `lop`.`idlop` 
            INNER JOIN `sinhvien` ON `lop`.`idlop` = `sinhvien`.`idlop` INNER JOIN `monhoc`  ON `lophoc`.`idmonhoc` = `monhoc`.`idmonhoc`
            WHERE `lophoc`.`idgiaovien` = $lop AND `monhoc`.`tenmon` LIKE '%$monhoc%' ";
            $rs = $db->thucthi($sql);
            $json = array();
            while($row = mysqli_fetch_assoc($rs)){
                $json[] = ($row);
            }
            print_r(json_encode($json));

    }else{
        echo "0";
    }
        
?>