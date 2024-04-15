
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  .bodyer{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100px;
  }
  .container{
    width: calc(100% - 50%);
    padding: 15px;
    background: #E7BDA733;
    border-radius: 15px;
    box-shadow: 0 5px 10px #999898;
  }
  .btn{
    
    background: yellowgreen;
  }
  </style>
<?php 
    include_once("../connect.php");
    $db = new database();
    if(isset($_POST['them'])){
        $tieude = $_POST['tieude'];
        $noidung = $_POST['noidung'];
        $loaianh = $_POST['loaianh'];
        $loaithongbao = $_POST['loaithongbao'];
        $rs = $db->thucthi("INSERT INTO `thongbao`(`tieude`,`noidung`, `loaianh`, `loaithongbao`) VALUES ('$tieude','$noidung',$loaianh,$loaithongbao)");
        if($rs){
            echo "thành công";
        }else{
            echo "that bại";
        }
    }
?>
  <div class="bodyer">
  <div class="container">

  <form action="index.php" method="post">
  <div class="form-group">
    <input type="text" class="form-control" placeholder="Tiêu đề" name="tieude">
  </div>
  <div class="form-group">
    <input type="text" class="form-control" placeholder="Nội Dung" name="noidung">
  </div>
  <div class="form-group">
    <select class="form-control"  name="loaianh">
            <option value = 0>Quan trọng</option>
            <option value = 1>Chung Chung</option>
            <option value = 2>Tin tức trong trường</option>
    </select>
  </div>
  <div class="form-group">
        <select class="form-control"  name="loaithongbao">
                <option value = 0>Thông báo chung</option>
                <option value = 1>Thông báo riêng</option>
        </select>
  </div>
  <div class="form-group">
    <button type="submit" class="btn btn-primary" name="them">thêm</button>
  </div>
  
</form>

  </div>
  </div>
  
