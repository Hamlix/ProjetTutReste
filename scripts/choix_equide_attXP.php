<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$po=mysqli_query($connexion,"SELECT idE, nomE, experienceE, niveauE FROM equide WHERE idU='".$idU."' AND etatE=1;") or die ("ERREUR SQL");
		$affichage=array();
		for($i=0;$resul=mysqli_fetch_assoc($po);$i++)
			$affichage[$i]=$resul;
		echo json_encode($affichage);
	}
	else 
		echo "pb choix equide";
}
?>
