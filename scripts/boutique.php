<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$resu = mysqli_query($connexion, "SELECT lvlMaxEU, nbMissionsU FROM utilisateur WHERE idU='".$idU."';") or die ("ERREUR SQL");
		$solution=array();
		for($i=0;$resul=mysqli_fetch_assoc($resu);$i++)
			$solution[$i]=$resul;
		$maxEU=$solution[0];
		$missU=$solution[1];
		$objet = mysqli_query($connexion, "SELECT idO, nomO, descriptionO, satieteO, moralO, propreteO,   limitationNbO, prixO, limitationUtilisationO, experience FROM objetboutique WHERE lvlMinPoO<= '".$maxEU."' OR missionMinO= '".$missU."';") or die ("ERREUR SQL");
		$affichage=array();
		for($i=0;$re=mysqli_fetch_assoc($objet);$i++)
			$affichage[$i]=$re;
		echo json_encode($affichage);
	}
	else 
		echo "pb dobjet de la boutique";
}
?>
