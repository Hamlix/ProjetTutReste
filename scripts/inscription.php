<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
if($connexion){
	$login=$_GET['login'];
	//Pour crypter le mot de passe
	$mdp = hash('sha512', $_GET['mdp']);
	//$mdp=$_GET['mdp'];
	$email=$_GET['email'];
	$inscription = mysqli_query($connexion, "INSERT INTO `utilisateur`(`idU`, `nomU`, `mdpU`, `emailU`, `orU`, `nbJourCoU`, `dernierJConnec`, `nbSuccesU`, `nbMissionsU`, `jaugeXPU`, `lvlMaxEU`, `titreSuccesU`) VALUES (0,'".$login."','".$mdp."','".$email."',150,1, Now(),0,1,0,0,'Aucun')") or die("Erreur SQL ");
	$recupId = mysqli_query($connexion, "SELECT idU FROM utilisateur WHERE nomU='".$login."';") or die("Erreur SQL2");
	$resultat = mysqli_fetch_assoc($recupId); 
	echo $resultat['idU'];
	}
else
	echo "Erreur de co";
?>