<?php 
    include_once("connect.php");

    $db = new database();
    if(isset($_POST['masinhvien'])){
        $masinhvien = $_POST['masinhvien'];
        try{
            $sql = "SELECT * FROM `giaovien` INNER JOIN `khoa` ON `giaovien`.idkhoa  = `khoa`.idkhoa WHERE idgiaovien  = $masinhvien
            ";
            $result = $db->thucthi($sql);
            $number = mysqli_num_rows($result);
            if($number > 0){
                    $row = mysqli_fetch_array($result);
                    $ten = $row['hoten'];
                    $sdt = $row['sdt'];
                    $tenkhoa = $row['tenkhoa'];
                    echo "0".",".$ten.",".$masinhvien.",".$sdt.",".$tenkhoa;
            }
            if($number <= 0){
                
                $sql = "SELECT * FROM `sinhvien` INNER JOIN `lop` ON `sinhvien`.idlop = `lop`.idlop WHERE `sinhvien`.idsinhvien = $masinhvien";
                $result = $db->thucthi($sql);
                $number = mysqli_num_rows($result);
                if($number > 0){
                    $row = mysqli_fetch_array($result);
                    $lop = $row['tenlop'];
                    $ten = $row['hoten'];
                    $ngaysinh = $row['NgaySinh'];
                    $Dienthoai = $row['Dienthoai'];
                    $Email = $row['Email'];
                    $GioiTinh = $row['GioiTinh'];
                    echo "1".",".$lop.",".$ten.",".$masinhvien.",".$ngaysinh.",".$Dienthoai.",".$Email.",".$GioiTinh;
                }else{
                    echo "";
                }
            }

            
        }catch(Exception $ex){
            echo "";
        }
    }else{
        echo "";
    }
?>