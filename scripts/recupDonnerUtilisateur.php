<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
if(isset($_GET['login']))
	if($connexion){
		$login = $_GET['login'];
		$resultat = mysqli_query($connexion, "Select * from utilisateur join equide on utilisateur.idU = equide.idU where nomU='".$login."';") or die("Erreur SQL");
		$affichage = array();
		for($i=0; $resul = mysqli_fetch_assoc($resultat); $i++)
			$affichage[$i] = $resul;
		echo json_encode($affichage);
		}
	else
		echo "pb";
?>