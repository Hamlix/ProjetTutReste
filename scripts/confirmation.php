<?php
include('./identifiant.php');
$connexion = new mysqli($host, $user, $password, $db);
if($connexion && isset($_GET['idU'])){
	mysqli_query($connexion, "UPDATE `utilisateur` SET valide=1 WHERE idU=".$_GET['idU']) or die("Erreur SQL");
	echo "inscription confirmee";

}
else
	"erreur connexion"

?>