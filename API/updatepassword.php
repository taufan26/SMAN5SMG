<?php
require_once 'config.php';
$password = md5($_POST['password']);
$cpassword = md5($_POST['cpassword']);
$email = $_POST['username'];
$sql = "SELECT * FROM login WHERE username ='$email'";
$query = mysqli_query($conn, $sql);

if ($password == $cpassword) {
	if (!mysqli_num_rows($query) > 0) {
		echo "Old password did not match";
	}else{
		$update = "UPDATE login SET password = '$password' WHERE username ='$email'";
		$res = mysqli_query($conn, $update);
		if ($res) {
			echo "Password Berhasil di ganti!";
		}else{
			echo "Error:-101";
		}
	}

}else{
		echo "Password Tidak Sama!";
	}

?>