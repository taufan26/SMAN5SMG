<?php
require 'koneksi.php';
$email = $_GET['key'];
$sql = "SELECT * FROM login WHERE username ='$email'";
$result = mysqli_query($conn,$sql);
if (mysqli_num_rows($result) === 1) {
	if (isset($_POST['submit'])) {
		$password = md5($_POST['password']);
		$cpassword = md5($_POST['cpassword']);
		if ($password == "" && $cpassword == "") {
			echo "Isi semua field";
		}else{
			if ($password == $cpassword) {
				$update = "UPDATE login SET password = '$password' WHERE username = '$email'";
				if (mysqli_query($conn,$update)) {
					echo "<h2> Password Berhasi di ganti, Silakan Login!!!";
				}else{
					echo "Terjadi Kesalahan pada perubahan password dan klik tautan email!";
				}
			}else{
				echo "Password Tidak Sama!";
			}

		}
	}else{
		echo "Click here to submit button and change password!";
	}
}

?>

<!DOCTYPE html>
<html>
<head>
	<title>Forgot Password</title>
</head>
<body>
	<form action="" method="POST">
		<h1><?php echo "Welcome " . $email?></h1>
	Enter Password Baru: <input type="text" name="password" id="password"><br>
	Enter Password Lagi: <input type="text" name="cpassword" id="cpassword"><br>
	<input type="submit" name="submit">		
	</form>
</body>
</html>