<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$resultat = mysqli_query($connexion, "SELECT satieteO,nomO,descriptionO,moralO,propreteO,lvlMinPoO FROM ObjetUtilisateur WHERE idU='".$idU."' AND  satieteO>0;") or die ("ERREUR SQL");
		$affichage=array();
		for($i=0;$resul=mysqli_fetch_assoc($resultat);$i++)
			$affichage[$i]=$resul;
		echo json_encode($affichage);
	}
	else 
		echo "pb affichage objet nourrir";
}
?>