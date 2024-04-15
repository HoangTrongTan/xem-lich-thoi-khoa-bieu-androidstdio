<?php 
    include_once("connect.php");
    $db = new database();
    if(isset($_POST['hocky']) && isset($_POST['lop'])){
        $hocky = (int)$_POST['hocky'];
        $lop = $_POST['lop'];
        $sql = "SELECT DISTINCT `lophoc`.tongsotiet,`monhoc`.tenmon,`monhoc`.idmonhoc,`giaovien`.hoten FROM (((`lophoc` INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop) right JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) INNER JOIN `giaovien` ON `lophoc`.idgiaovien = `giaovien`.idgiaovien) WHERE `lop`.tenlop = '$lop' AND `lophoc`.idkyhoc = $hocky ";
        $result = $db->thucthi($sql);
        $json = array();
        while($r = mysqli_fetch_assoc($result)){
            $json[] = ($r);
        }
        $result = $db->thucthi($sql);
        for($i = 0 ; $i < count($json); $i ++){
            $temp = array();
            $str = $json[$i]['tenmon'];
            $sql = "SELECT `lophoc`.ngaybatdau FROM `lophoc` INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc WHERE `monhoc`.tenmon = '$str' ";
            $rs = $db->thucthi($sql);
            while($r = mysqli_fetch_assoc($rs)){
                $temp[] = ($r);
            }
            if(count($temp) > 1){
                $str = $temp[0]['ngaybatdau']."  ->  ".$temp[count($temp)-1]['ngaybatdau'];
                $json[$i]['thoigian'] = $str;
            }else{
                $str = $temp[0]['ngaybatdau'];
                $json[$i]['thoigian'] = $str;
            }
        }
        // $db->test($json);
        print_r(json_encode($json));
    }else if(isset($_POST['hocky']) && isset($_POST['magv'])){
        $hocky = (int)$_POST['hocky'];
        $magv = (int)$_POST['magv'];
        $sql = "SELECT DISTINCT `lophoc`.tongsotiet,`monhoc`.tenmon,`monhoc`.idmonhoc,`giaovien`.hoten FROM (((`lophoc` INNER JOIN `lop` ON `lophoc`.idlop = `lop`.idlop) right JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc) INNER JOIN `giaovien` ON `lophoc`.idgiaovien = `giaovien`.idgiaovien) WHERE `giaovien`.idgiaovien = $magv AND `lophoc`.idkyhoc = $hocky ";
        $result = $db->thucthi($sql);
        $json = array();
        while($r = mysqli_fetch_assoc($result)){
            $json[] = ($r);
        }
        $result = $db->thucthi($sql);
        for($i = 0 ; $i < count($json); $i ++){
            $temp = array();
            $str = $json[$i]['tenmon'];
            $sql = "SELECT `lophoc`.ngaybatdau FROM `lophoc` INNER JOIN `monhoc` ON `lophoc`.idmonhoc = `monhoc`.idmonhoc WHERE `monhoc`.tenmon = '$str' ";
            $rs = $db->thucthi($sql);
            while($r = mysqli_fetch_assoc($rs)){
                $temp[] = ($r);
            }
            if(count($temp) > 1){
                $str = $temp[0]['ngaybatdau']."  ->  ".$temp[count($temp)-1]['ngaybatdau'];
                $json[$i]['thoigian'] = $str;
            }else{
                $str = $temp[0]['ngaybatdau'];
                $json[$i]['thoigian'] = $str;
            }
        }
        // $db->test($json);
        print_r(json_encode($json));
    }else{
        echo "[{}]";
    }
        
?>