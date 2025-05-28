# GestionEscalesNavire
# Description
Ce projet Java permet de gérer la facturation des redevances pour les navires pilotables (NP > 1500) au port autonome de Dakar.
Il inclut la gestion des escales, des mouvements de pilotage et le calcul des montants à facturer.

# Fonctionnalités
•	Gestion des informations sur les navires (numéro, nom, volume, tirant d'eau, etc.)
•	Gestion des escales (date début/fin, numéro, prix de séjour)
•	Gestion des bons de pilotage (type de mouvement, poste à quai, prix unitaire)
•	Calcul automatique des montants des bons, du séjour, et du total de la prestation
•	Affichage console détaillé de la facture
•	Interface graphique (Swing) pour visualiser les résultats (optionnel)
Architecture du projet
•	Package sn.ucad.retel1jour.mariemekamara.be : Classes métiers (Navire, Escale, BonPilotage, TypeMouvement)
•	Package sn.ucad.retel1jour. mariemekamara.interfaces : Interface ICalculerPrestation et classe CalculPrestation
•	Package sn.ucad.retel1jour. mariemekamara.test : Classe de test TestPrestation avec affichage console
•	Package sn.ucad.retel1jour. mariemekamara.gui : Interface graphique Swing

# Comment lancer le projet
1.	Compiler les classes
Dans le terminal, place-toi dans le dossier racine du projet et lance :
javac -d bin src/sn/ucad/retel1jour/mariemekamara/**/*.java
 
2.	Exécuter la classe de test
> java -cp bin sn.ucad.retel1jour.mariemekamara.test.TestPrestation 
3.	Lancer l’interface graphique si disponible.
> java -cp bin sn.ucad.retel1jour.mariemekamara.gui.InterfaceGraphique

# Remarques
•	Le calcul du nombre de jours entre deux dates utilise la formule suivante :
long CONST_DURATION_OF_DAY = 1000 * 60 * 60 * 24;
long diff = Math.abs(dateFin.getTime() - dateDebut.getTime());
long numberOfDay = diff / CONST_DURATION_OF_DAY + 1;
•	Les prix unitaires des mouvements et du séjour, ainsi que les caractéristiques du navire sont initialisés dans la classe CalculPrestation.

# Auteur
Nom : Marieme Kamara
Email : mariemekamara@esp.sn

