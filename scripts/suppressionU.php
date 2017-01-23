<?php
include('./identifiant.php');
if(isset($_GET['idU'])){
	$connexion = mysqli_connect($host, $user, $password, $db);
	if($connexion){
		$idU=$_GET['idU'];
		mysqli_query($connexion, "Delete from utilisateur where idU=".$idU."") or die("Erreur suppression");
		echo "Suppression effectuee";
	}
}
?>