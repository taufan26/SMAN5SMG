<?php
include 'config.php';

if ($_POST) {
	
	//data
	$username = $_POST['username'] ?? '';
	$password = $_POST['password'] ?? '';

	$response = [];//data response

	//cek username didalam database
	$userQuery = $connection->prepare("SELECT * FROM login where username = ?");
	$userQuery->execute(array($username));
	$query = $userQuery->fetch();

	if ($userQuery->rowCount() == 0) {
		$response['status'] = false;
		$response['message'] = "Username Tidak Terdaftar";
	} else {
		//ambil password di db

		$passwordDB = $query['password'];

		if (strcmp(md5($password), $passwordDB) === 0) {
			$response['status'] = true;
			$response['message'] = "Login Berhasil";
			$response['data'] = [
				'id_login' => $query['id_login'],
				'username' => $query['username'],
				'nama' => $query['nama'],
				'foto' => $query['foto'],
				'id_pegawai' => $query['id_pegawai'],
				'id_siswa' => $query['id_siswa']
			];
		}else{
			$response['status'] = false;
			$response['message'] = "Password anda salah";
		}
	}

	//data json
	$json = json_encode($response, JSON_PRETTY_PRINT);

	echo $json;
	
}

?>