<!DOCTYPE html>
<html>
	<head>
		<title>
		</title>
	</head>
	<body>
<?php
$servername = "localhost";
$username = "root";
$password = "";
$database = "sman5semarang";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $database);

$sql = "SELECT * FROM login";
$result = mysqli_query ($conn, $sql);

$json_array = array();

while($row = mysqli_fetch_assoc($result))
{
	$json_array[] = $row;
}

echo json_encode($json_array);
/*echo '<pre>';
print_r($json_array);
echo '</pre>';*/

// Check connection
if (!$conn) {
  die("Connection failed: " . mysqli_connect_error());
}
echo "Connected successfully";
?>
</body>
</html>