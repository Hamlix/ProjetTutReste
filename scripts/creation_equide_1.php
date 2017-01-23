<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);
if($connexion){
	$idU=$_GET['idU'];
	//$id=$_GET['id'];
	$nom=$_GET['nom'];
	$robe=$_GET['robe'];
	$crin=$_GET['crin'];
	$yeux = rand(1,3);
	$equide = mysqli_query($connexion, "INSERT INTO equide(idE, nomE, stadeE, couleurRobeE, couleurCrinE, couleurYeuxE, experienceE, niveauE, moralE, satieteE, propreteE, etatE, nbNourrit, nbLave, nbCaresse, nbJoue, nbMinijeu, nbMort, idU) VALUES (null,'".$nom."','poulain',".$robe.",".$crin.",".$yeux.",0,1,50,50,100,1,0,0,0,0,0,0,".$idU.");") or die("Erreur SQL EQUIDE");
	$majU = mysqli_query($connexion, "UPDATE utilisateur SET lvlMaxEU=1 WHERE idU='".$idU."';") or die("Erreur SQL MAJ U 1");
	$reussite= mysqli_query($connexion, "INSERT INTO reussir(idReu, idU, idS, etatS) VALUES (null,'".$idU."',1,1);") or die("Erreur SQL reussite succes");
	$maj = mysqli_query($connexion, "UPDATE utilisateur SET nbSuccesU=1 WHERE idU='".$idU."';") or die ("Erreur SQL MAJ U 2");
	
	echo "OK";
	}
else
	echo "Erreur de co";
?>