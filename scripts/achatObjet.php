<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
mysqli_set_charset($connexion,'UTF8');
if(isset($_GET['login'])){
	if($connexion){
		$idU=$_GET['idU'];
		$idO=$_GET['idO'];
		$qte = mysqli_query($connexion, "SELECT quantiteO FROM objetutilisateur WHERE idO ='".$idO."' AND idU='".$idU."';") or die ("ERREUR SQL");
		$res_qte=mysqli_num_rows($qte);
		if($qte>0){
			$insertion = myslqi_query($connexion, "UPDATE objetutilisateur SET quantiteO++ WHERE idO='".$idO."' AND idU='".$idU."';") or die ("ERREUR SQL");
			
		} else {
			$resultat= mysqli_query($connexion, "SELECT nomO, descriptionO, satieteO, moralO, propreteO, limitationNbO,  limitationUtilisationO, experience, boutonUtilO FROM objetboutique WHERE idO = '".$idO."';") or die ("ERREUR SQL");
			$objet=array();
			for($i=0;$resul=mysqli_fetch_assoc($resultat);$i++)
				$objet[$i]=$resul;
			$newO= mysqli_query($connexion,"INSERT INTO objetutilisateur(idO,nomO,descriptionO,satieteO,moralO,propreteO,experience,limiteNbO,quantiteO,limitationUtilisationO,nbUse,boutonUtilU,idU) VALUES ('".$idO."','".$objet[0]."','".$objet[1]."','".$objet[2]."','".$objet[3]."','".$objet[4]."','".$objet[7]."','".$objet[5]."',1,'".$objet[6]."',0,'".$objet[8]."','".$idU."');") or die ("ERREUR SQL");
			
		}
			echo "objet achete";
		//après faut appler les scripts test mission et test succes 
	}
	else 
		echo "pb dachat";
}
?>
