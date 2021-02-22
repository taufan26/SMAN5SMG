<?php

include('DataBaseConfig.php');

$stmt = $conn->mysqli_connect("SELECT * FROM 'md_siswa' WHERE 'nama_siswa'");


$stmt ->execute();
$stmt ->bind_result($nama, $foto);

$md_siswa = array();

while($stmt->fetch()){

    $temp = array();

    $temp['nama_siswa'] = $nama;
    // $temp['nisn'] = $nisn;
    // $temp['alamat'] = $alamat;

    array_push($md_siswa,$temp);
}
echo json_encode($md_siswa)
?>