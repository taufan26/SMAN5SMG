<?php
require "DataBase.php";
$db = new DataBase();
if (isset($_POST['username']) && isset($_POST['password'])) {
    if ($db->dbConnect()) {
        if ($db->logIn("login", $_POST['username'], $_POST['password'])) {
            echo "Login Success";
        } else echo "Username atau Password salah";
    } else echo "Error: Database connection";
} else echo "All fields are required";
?>
