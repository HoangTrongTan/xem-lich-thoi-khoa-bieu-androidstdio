<?php 
    include_once("connect.php");
    $db = new database();

        if(isset($_POST['today']) && isset($_POST['lop'])){
            $lop = $_POST['lop'];
            $today = $_POST['today'];
            $dayOfWeek = date('N', strtotime($today)); // Lấy thứ của ngày hiện tại (1: Thứ 2, 2: Thứ 3, ..., 7: Chủ nhật)
            $dates = array();
            for ($i = 1; $i <= 7; $i++) { // Lặp qua từ thứ 2 đến chủ nhật
            $diff = $i - $dayOfWeek;
            $date = date('Y-m-d', strtotime($diff . ' days', strtotime($today))); // Tính ngày của thứ i
            $dates[] = $date;
            }
            $sql = "SELECT `lophoc`.ngaybatdau,`thoikhoabieu`.tietbatdau,`thoikhoabieu`.tietketthuc,`thoikhoabieu`.sophong,`lop`.tenlop,`monhoc`.tenmon, `giaovien`.hoten FROM ((((`thoikhoabieu` INNER JOIN `lophoc` ON `thoikhoabieu`.idlophoc = `lophoc`.idlophoc)INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop ) INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) INNER JOIN `giaovien` ON `giaovien`.idgiaovien = `lophoc`.idgiaovien) WHERE `lophoc`.ngaybatdau BETWEEN '$dates[0]' AND '$dates[6]' AND `lop`.tenlop LIKE '%$lop%'  ORDER BY `lophoc`.ngaybatdau,`thoikhoabieu`.tietbatdau ";
            $result = $db->thucthi($sql);
            $json = array();
            while($r = mysqli_fetch_assoc($result)){
                $json[] = ($r);
            }
         
            print_r(json_encode($json));
        // $db->test($json);
    }
    if(isset($_POST['today']) && isset($_POST['magv'])){
        $magv = $_POST['magv'];
        $today = $_POST['today'];
        $dayOfWeek = date('N', strtotime($today)); // Lấy thứ của ngày hiện tại (1: Thứ 2, 2: Thứ 3, ..., 7: Chủ nhật)
        $dates = array();
        for ($i = 1; $i <= 7; $i++) { // Lặp qua từ thứ 2 đến chủ nhật
        $diff = $i - $dayOfWeek;
        $date = date('Y-m-d', strtotime($diff . ' days', strtotime($today))); // Tính ngày của thứ i
        $dates[] = $date;
        }
        $sql = "SELECT `lophoc`.ngaybatdau,`thoikhoabieu`.tietbatdau,`thoikhoabieu`.tietketthuc
        ,`thoikhoabieu`.sophong,`lop`.tenlop,`monhoc`.tenmon, `giaovien`.hoten 
        FROM ((((`thoikhoabieu` INNER JOIN `lophoc` ON `thoikhoabieu`.idlophoc = `lophoc`.idlophoc)
        INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop ) 
        INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) 
        INNER JOIN `giaovien` ON `giaovien`.idgiaovien = `lophoc`.idgiaovien) 
        WHERE `lophoc`.ngaybatdau BETWEEN '$dates[0]' AND '$dates[6]' and `giaovien`.idgiaovien = $magv
        ORDER BY `lophoc`.ngaybatdau,`thoikhoabieu`.tietbatdau ";
        $result = $db->thucthi($sql);
        $json = array();
        while($r = mysqli_fetch_assoc($result)){
            $json[] = ($r);
        }
        print_r(json_encode($json));
        // print_r("sucesssfull :)))")

    }
?>