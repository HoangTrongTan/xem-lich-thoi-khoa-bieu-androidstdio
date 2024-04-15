<?php
    include_once('connect.php');
    $db = new database();

    $sql = "SELECT `thoikhoabieu`.tietbatdau,`thoikhoabieu`.tietketthuc,`thoikhoabieu`.sophong,`lop`.tenlop
            ,`monhoc`.tenmon, `giaovien`.hoten FROM ((((`thoikhoabieu` 
            INNER JOIN `lophoc` ON `thoikhoabieu`.idlophoc = `lophoc`.idlophoc)
            INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop ) INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) 
            INNER JOIN `giaovien` ON `giaovien`.idgiaovien = `lophoc`.idgiaovien) 
            WHERE `lophoc`.ngaybatdau = '2023-05-15' AND `giaovien`.idgiaovien = 01007003
            ORDER BY `thoikhoabieu`.tietbatdau";
    
        $rs = $db->thucthi($sql);
        $json = array();
        while($row = mysqli_fetch_assoc($rs)){
            $json[] = ($row);
        }
        $db->test($json);
 
        // print_r(json_encode($json));
?>