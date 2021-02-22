<?php
require 'config.php';

$username = $_POST['username'];
$password = $_POST['password'];

$sql = "SELECT * FROM login WHERE username = '$username' AND password = '$password'";
$result = array();
$result['data'] = array();


$responce = mysqli_query($conn,$sql);

if(mysqli_num_rows($responce) === 1) {
	$row = mysqli_fetch_assoc($responce);
	$ds['username'] = $row ['username'];
	$ds['nama'] = $row['nama'];



	array_push($result, ['data'], $ds);
	$result['status'] = 'success';
	echo json_encode($result);
	mysqli_close($conn);
}else{
	$result['status'] = 'error';
	echo json_encode($result);
	mysqli_close($conn);

}

?>