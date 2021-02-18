<?php
require_once 'config.php';
require 'PHPMailer/PHPMailerAutoload.php';
require 'PHPMailer/class.phpmailer.php';
require 'PHPMailer/class.smtp.php';
require 'admin.php';

$email = $_POST['email'];
$sql = "SELECT * FROM login WHERE username ='$email'";
$query = mysqli_query($conn,$sql);
if (mysqli_num_rows($query) === 1) {
	$mail = new PHPMailer;

	$mail->isSMTP();                                      // Set mailer to use SMTP
	$mail->Host = 'smtp.gmail.com';  // Specify main and backup SMTP servers
	$mail->SMTPAuth = true;                               // Enable SMTP authentication
	$mail->Username = $adminemail;                 // SMTP username
	$mail->Password = $adminpass;                           // SMTP password
	$mail->SMTPSecure = 'tls';                            // Enable TLS encryption, `ssl` also accepted
	$mail->Port = 587;                                    // TCP port to connect to

	$mail->setFrom('gssshop07@gmail.com', 'GSS shop');
	$mail->addAddress($email);     // Add a recipient
	$mail->addReplyTo('gssshop07@gmail.com', 'GSS shop');

	$mail->Subject = 'Lupa Password';
	$mail->Body    = "Klik Link untuk merubah password:
	-http://www.sman5smg.com/$email";

	if(!$mail->send()) {
	    echo 'Message could not be sent.';
	    echo 'Mailer Error: ' . $mail->ErrorInfo;
	} else {
	    $msg["mail"] = "Send";
	    echo json_encode($msg);
	}
}else{
	echo "Enter A Valid Email";
}

?>