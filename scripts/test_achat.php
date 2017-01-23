<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$idO=$_GET['idO'];
		$prixO=$_GET['prixO'];
		$limitO=$_GET['limitationNbO'];
		$orU = mysqli_query($connexion, "SELECT orU FROM utilisateur WHERE  idU='".$idU."';") or die ("ERREUR SQL");
		if ($orU >= $prixO){
			$res_qte = mysqli_query($connexion,"SELECT quantiteO FROM objetutilisateur WHERE idU='".$idU."' AND idO= '".$idO."';")or die ("ERREUR SQL");
			$qte=mysqli_num_rows($res_qte);
			if($qte==0)
				echo "achat OK";
			// si achat OK faut passer au script achat objet
			else if( $res_qte==$limitO )
				echo "achat impossible nbre objet";
			else 
				echo "achat OK"
		} else 
			echo "achat impossible or"
	}
	else 
		echo "pb dobjet dans linventaire";
}
?>

