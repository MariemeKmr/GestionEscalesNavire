package sn.ucad.retel1jour.mariemekamara.interfaces;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sn.ucad.retel1jour.mariemekamara.be.BonPilotage;
import sn.ucad.retel1jour.mariemekamara.be.Escale;
import sn.ucad.retel1jour.mariemekamara.be.Navire;
import sn.ucad.retel1jour.mariemekamara.be.TypeMouvement;


public class CalculPrestation implements ICalculerPrestation {
    private Escale myEscale;
    private Navire myNavire;
    private List<BonPilotage> mesBons;

    // Constructeur
    public CalculPrestation() {
        // Initialisation du navire avec les données de l'exemple
        this.myNavire = new Navire("P001", "ALINE SITEO DIATTA", 76, 16, 3867, 3);

        // Initialisation de l'escale
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date debutEscale = sdf.parse("2016/03/01");
            Date finEscale = sdf.parse("2016/03/12");
            this.myEscale = new Escale("2016001", debutEscale, finEscale, myNavire, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Initialisation de la liste des bons
        this.mesBons = listBon();
    }

    
    public int calculNbrJours(Date dateDebut, Date dateFin) {
        long CONST_DURATION_OF_DAY = 1000 * 60 * 60 * 24;
        long diff = Math.abs(dateFin.getTime() - dateDebut.getTime());
        return (int) (diff / CONST_DURATION_OF_DAY) + 1;
    }

    
    public double calculMontantBon(BonPilotage leBon) {
        // Montant = prix type mouvement * volume navire * tirant d'eau
        return leBon.getTypeMouvement().getPrixTypeMvt() * myNavire.getVolumeNavire() * myNavire.getTirantEauNavire();
    }

    public double calculMontantSejour() {
        int nombreDeJours = calculNbrJours(myEscale.getDebutEscale(), myEscale.getFinEscale());
        // Montant = nombre de jour * prix séjour * volume navire
        return nombreDeJours * myEscale.getPrixSejour() * myNavire.getVolumeNavire();
    }

    
    public List<BonPilotage> listBon() {
        List<BonPilotage> listBon = new ArrayList<>();

        // Création des types de mouvement
        TypeMouvement entree = new TypeMouvement("E", "Entrée au port", 5);
        TypeMouvement mouvement = new TypeMouvement("M", "Accostage", 15);
        TypeMouvement sortie = new TypeMouvement("S", "Sortie du port", 8);
        
        // Utilisation de la date de début de l’escale pour les bons (ou une autre date selon besoin)
        Date dateBon = myEscale.getDebutEscale();
        
        // Création des bons de pilotage
        listBon.add(new BonPilotage(101, myEscale, dateBon, "101", entree));
        listBon.add(new BonPilotage(102, myEscale, dateBon, "102", mouvement));
        listBon.add(new BonPilotage(103, myEscale, dateBon, "103", sortie));

        return listBon;
    }

    
    public double calculerMontantTotal() {
        double total = calculMontantSejour();
        for (BonPilotage bon : mesBons) {
            total += calculMontantBon(bon);
        }
        return total;
    }

    // Getters pour accéder aux données
    public Navire getNavire() {
        return myNavire;
    }

    public Escale getEscale() {
        return myEscale;
    }

    public List<BonPilotage> getMesBons() {
        return mesBons;
    }
}
