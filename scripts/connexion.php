<?php
include('./identifiant.php');
$connexion = mysqli_connect($host, $user, $password, $db);

if(isset($_GET['login']))
	if(isset($_GET['mdp'])){
		if($connexion){
			$login = $_GET['login'];
			//Pour crypter le mot de passe
			$mdp = hash('sha512', $_GET['mdp']);
			//$mdp = $_GET['mdp'];
			$resultat = mysqli_query($connexion, "Select nomU,dernierJConnec,valide from utilisateur where nomU='"
				.$login."' and mdpU='".$mdp."';") or die("Erreur SQL");
			$valeur = mysqli_num_rows($resultat);
			if($valeur == 1){
				$r = mysqli_fetch_assoc($resultat);
				if($r['dernierJConnec'] != date("Y-m-d")){
					mysqli_query($connexion, "UPDATE utilisateur SET nbJourCoU = nbJourCoU +1, dernierJConnec = '".date("Y-m-d")."' WHERE nomU='".$login."';");
					$o= mysqli_query($connexion, "SELECT orU,nbJourCoU, lvlMaxEU FROM utilisateur WHERE idU='".$idU."';") or die ("ERREUR SQL");
					$infU = array();
					for($i=0;$re=mysqli_fetch_assoc($o);$i++)
						$infU[$i]=$re;
					$equide = mysqli_query($connexion, "SELECT idE FROM equide WHERE idU='".$idU."';") or die ("ERREUR SQL");
					$nbE=mysqli_num_rows($equide);
					$addOr=$infU[0] + 100*$infU[2]/$nbE + $infU[1]/$infU[2];
					mysqli_query($connexion, "UPDATE utilisateur SET orU='".$addOr."' WHERE idU='".$idU."';") or die ("ERREUR SQL");
					
					// perte stats poney
					mysqli_query($sonnexion, "UPDATE equide SET moralE=30, satieteE=60, propreteE=75 WHERE idU=".$idU." AND etatE=1 AND niveauE<=3;") or die ("ERREUR SQL");
					mysqli_query($sonnexion, "UPDATE equide SET moralE=30, satieteE=50, propreteE=75 WHERE idU=".$idU." AND etatE=1 AND niveauE<=6 AND niveauE>3;") or die ("ERREUR SQL");
					mysqli_query($sonnexion, "UPDATE equide SET moralE=50, satieteE=75, propreteE=100 WHERE idU=".$idU." AND etatE=1 AND niveauE<=9 AND niveau>6;") or die ("ERREUR SQL");
					mysqli_query($sonnexion, "UPDATE equide SET moralE=150, satieteE=195, propreteE=200 WHERE idU=".$idU." AND etatE=1 AND niveauE=10;") or die ("ERREUR SQL");
				}
				if($r['valide'] == 1)
					echo "ok";
				else
					echo "inscription non valide";
				} 
			else
				echo "pas ok";
			}
		else
			echo "pb";
	}
?>
