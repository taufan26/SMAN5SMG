<?php
require 'config.php';

	$nama = $_POST['nama'];
	$username = $_POST['username'];
	$password = $_POST['password'];


//crud query to check email
$sql = "SELECT * FROM login WHERE username = '$username'";
$check = mysqli_query($conn,$sql);
if (mysqli_num_rows($check)){

	echo "USER ALLREADY EXISTSt";
}else{

	$insertdata = "INSERT INTO login(nama,username,password) VALUES ('$nama','$username','$password')";
	if(mysqli_query($conn,$insertdata)){

		echo "Registrasi Berhasil, Selamat !";
	}else{

		echo "REGISTER FAILED ";
	}
}
?>