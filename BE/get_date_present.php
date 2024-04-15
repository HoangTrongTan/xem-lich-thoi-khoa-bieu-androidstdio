<?php
    include_once("connect.php");
    $db = new database();

    if(isset($_POST['d_m_y']) && isset($_POST['lop'])){
       $d_m_y = $_POST['d_m_y'];
        $lop = $_POST['lop'];
       $sql = "SELECT `thoikhoabieu`.tietbatdau,`thoikhoabieu`.tietketthuc,`thoikhoabieu`.sophong
       ,`lop`.tenlop,`monhoc`.tenmon, `giaovien`.hoten 
       FROM ((((`thoikhoabieu` INNER JOIN `lophoc` ON `thoikhoabieu`.idlophoc = `lophoc`.idlophoc)
       INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop ) 
       INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) 
       INNER JOIN `giaovien` ON `giaovien`.idgiaovien = `lophoc`.idgiaovien) 
       WHERE `lophoc`.ngaybatdau = '$d_m_y' AND `lop`.tenlop LIKE '%$lop%' 
       ORDER BY `thoikhoabieu`.tietbatdau";
       $rs = $db->thucthi($sql);
        $json = array();
            while($row = mysqli_fetch_assoc($rs)){
                $json[] = ($row);
            }
            print_r(json_encode($json));
       

    }
    else if( isset($_POST['d_m_y']) && isset($_POST['magv'])){
        $magv = $_POST['magv'];
        $d_m_y = $_POST['d_m_y'];
        $sql = "SELECT `thoikhoabieu`.tietbatdau,`thoikhoabieu`.tietketthuc,`thoikhoabieu`.sophong,`lop`.tenlop
            ,`monhoc`.tenmon, `giaovien`.hoten FROM ((((`thoikhoabieu` 
            INNER JOIN `lophoc` ON `thoikhoabieu`.idlophoc = `lophoc`.idlophoc)
            INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop ) INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) 
            INNER JOIN `giaovien` ON `giaovien`.idgiaovien = `lophoc`.idgiaovien) 
            WHERE `lophoc`.ngaybatdau = '$d_m_y' AND `giaovien`.idgiaovien = $magv
            ORDER BY `thoikhoabieu`.tietbatdau";
        $rs = $db->thucthi($sql);
        $json = array();
            while($row = mysqli_fetch_assoc($rs)){
                $json[] = ($row);
            }
            print_r(json_encode($json));
    }else{
        $response['success'] = 0;
        $response['message'] = "không tồn tại";
        echo json_encode($response);
    }
?>