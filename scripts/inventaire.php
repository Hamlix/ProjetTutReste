<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$objet = mysqli_query($connexion, "SELECT * FROM objetutilisateur WHERE idU='".$idU."';") or die ("ERREUR SQL");
		$affichage=array();
		for($i=0;$resul=mysqli_fetch_assoc($objet);$i++)
			$affichage[$i]=$resul;

		//Si l'attribut boutonUtilU est a 1 cad que l'on doit pouvoir utiliser l'objet depuis l'inventaire. Donc mettre un bouton utiliser 
		// le script pour utiliser l'objet sera bientot fait ;) 
		echo json_encode($affichage);
	}
	else 
		echo "pb dobjet dans linventaire";
}
?>

