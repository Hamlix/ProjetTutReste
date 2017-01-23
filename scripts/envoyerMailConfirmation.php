<?php
function envoyerMail($destinataire,$sujet,$body){
	require './PHPMailer-master/PHPMailerAutoload.php';

	$mail = new PHPMailer;

	$mail->isSMTP();
	$mail->Host = 'smtp.gmail.com';
	$mail->SMTPAuth = true;
	$mail->Port = 465;
	$mail->Username = 'jeuxgestionequide@gmail.com ';
	$mail->Password = 'appgestion';
	$mail->SMTPSecure = 'ssl';
	$mail->From = 'appgestionderendezvous@gmail.com';
	$mail->FromName = 'ApplicationGestionEquide';
	$mail->addAddress($destinataire, 'Utilisateur');
	$mail->WordWrap = 50;
	$mail->isHTML(true);
	$mail->SMTPDebug = 0;
	$mail->Subject = $sujet;
	$mail->Body    = $body;

	if(!$mail->send()) {
		echo 'Message could not be sent.';
		echo 'Mailer Error: ' . $mail->ErrorInfo;
	} else {
		echo 'Message bien envoye';
	}
}

if(isset($_GET['destinataire']) && isset($_GET['idU'])){
	$sujet = "Mail de confirmation";
	$body = "Bonjour, merci de vous être inscrit <br> Afin de valider votre inscription merci de cliquer sur le lien suivant<br>http://192.168.1.21/projet/ScriptJeu/confirmation.php?idU=".$_GET['idU'];
	envoyerMail($_GET['destinataire'],$sujet,$body);

}
?>