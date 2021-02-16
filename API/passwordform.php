<?php

if (isset($_POST['submit'])) {
	$password = $_POST['password'];
	$cpassword = $_POST['cpassword'];
	//$email = $_POST['email'];
	if ($password == "" && $cpassword == "") {
		echo "Harap di isi dengan password yang valid";
	}else{
		if ($password == $cpassword) {
			echo "Success";
		}else{
			echo "Password Tidak Sama";
		}
	}
}

?>

<!DOCTYPE html>
<html>
<head>
	<title>Forgot Password</title>
</head>
<body>
	<form action="passwordform.php" method="POST">
	Enter Password Baru: <input type="text" name="password"><br>
	Enter Password Lagi: <input type="text" name="cpassword"><br>
	<input type="submit" name="submit">		
	</form>
</body>
</html>