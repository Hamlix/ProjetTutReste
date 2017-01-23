<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$mission = mysqli_query($connexion, "SELECT nbMissionsU FROM utilisateur WHERE idU='".$idU."';") or die ("ERREUR SQL");
		$objet = mysqli_query($connexion, "SELECT idONecessaire, expM, orM FROM mission WHERE idNiveauM='".$mission."';") or die ("ERREUR SQL");
		$res=array();
			for($i=0;$resul=mysqli_fetch_assoc($objet);$i++)
				$res[$i]=$resul;
		$res = mysqli_query($connexion, "SELECT * FROM objetutilisateur WHERE idO='".$res[0]."';") or die ("ERREUR SQL");
		$res_qte=mysqli_num_rows($res);
		if($res_qte>0){
			$upU= mysqli_query($connexion, "UPDATE orU+='".$res[2]."', nbMissionsU++, jaugeXPU+='".$res[1]."' WHERE idU='".$idU."';") or die ("ERREUR SQL");
			echo "mission up";
		}else 
			echo "mission non valide";
	}
	else 
		echo "pb dobjet dans linventaire";
}
?>