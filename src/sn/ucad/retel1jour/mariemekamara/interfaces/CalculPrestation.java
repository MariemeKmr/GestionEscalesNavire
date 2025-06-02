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
import sn.ucad.retel1jour.mariemekamara.be.Consignataire;

public class CalculPrestation implements ICalculerPrestation {

    private Escale myEscale;
    private Navire myNavire;
    private List<BonPilotage> mesBons;
    private static final double TARIF_ZONE_RADE = 100.0;
    private static final double TARIF_ZONE_INTERIEUR = 200.0;
    private static final double TARIF_PAR_MOUVEMENT = 50.0; // ou toute autre valeur pertinente

    public double getTarifParJour() {
        return myEscale.getZone().equalsIgnoreCase("rade") ? TARIF_ZONE_RADE : TARIF_ZONE_INTERIEUR;
    }

    public double getTarifParMouvement(BonPilotage bon) {
        return bon.getTypeMouvement().getPrixTypeMvt();
    }


    // Constructeur
    public CalculPrestation() {
        // Initialisation du consignataire
        Consignataire consignataire = new Consignataire(1, "Société Maritime Dakar", "123, Rue du Port, Dakar", "+221 77 123 45 67");
        // Initialisation du navire
        this.myNavire = new Navire("P001", "ALINE SITEO DIATTA", 76, 16, 3867, 3, consignataire);

        // Initialisation de l’escale
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date debutEscale = sdf.parse("2016/03/01");
            Date finEscale = sdf.parse("2016/03/12");
            this.myEscale = new Escale("2016001", debutEscale, finEscale, myNavire, 10, consignataire, "intérieur");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Initialisation des bons de pilotage
        this.mesBons = listBon();
    }

    public void afficherDetailsTarification() {
        System.out.println("=== Tarif Pilotage ===");
        for (BonPilotage bon : mesBons) {
            TypeMouvement tm = bon.getTypeMouvement();
            System.out.println("- " + tm.getLibelleTypeMvt() + " ➔ " +
                    "code type mouvement = " + tm.getCodeTypeMvt() + ", " +
                    "libellé type mouvement = " + tm.getLibelleTypeMvt() + ", " +
                    "poste à quai = " + bon.getPosteAQuai() + ", " +
                    "prix type mouvement = " + tm.getPrixTypeMvt() + " (CFA)");
        }

        System.out.println("\n=== Tarif Séjour ===");
        int jours = calculNbrJours(myEscale.getDebutEscale(), myEscale.getFinEscale());
        double tarifZone = myEscale.getZone().equalsIgnoreCase("rade") ? 100 : 200;
        System.out.println("Zone = " + myEscale.getZone());
        System.out.println("Tarif par jour = " + tarifZone + " CFA");
        System.out.println("Durée du séjour = " + jours + " jour(s)");
        System.out.println("Montant total séjour = " + (jours * tarifZone) + " CFA");
    }

    public int calculNbrJours(Date dateDebut, Date dateFin) {
        long CONST_DURATION_OF_DAY = 1000 * 60 * 60 * 24;
        long diff = Math.abs(dateFin.getTime() - dateDebut.getTime());
        return (int) (diff / CONST_DURATION_OF_DAY) + 1;
    }

    public double calculMontantBon(BonPilotage leBon) {
        return leBon.getTypeMouvement().getPrixTypeMvt() * myNavire.getVolumeNavire() * myNavire.getTiranEauNavire();
    }

    public double calculMontantSejour() {
        int nombreDeJours = calculNbrJours(myEscale.getDebutEscale(), myEscale.getFinEscale());
        return nombreDeJours * getTarifParJour();
    }


    public List<BonPilotage> listBon() {
        List<BonPilotage> listBon = new ArrayList<>();

        TypeMouvement entree = new TypeMouvement("E", "Entrée au port", 5);
        TypeMouvement mouvement = new TypeMouvement("M", "Accostage", 15);
        TypeMouvement sortie = new TypeMouvement("S", "Sortie du port", 8);

        Date dateDebut = myEscale.getDebutEscale();
        Date dateFin = myEscale.getFinEscale();

        listBon.add(new BonPilotage(101, myEscale, dateDebut, dateDebut, "101", entree));
        listBon.add(new BonPilotage(102, myEscale, dateDebut, dateFin, "102", mouvement));
        listBon.add(new BonPilotage(103, myEscale, dateFin, dateFin, "103", sortie));

        return listBon;
    }

    public double calculerMontantTotal() {
        double total = calculMontantSejour();
        for (BonPilotage bon : mesBons) {
            total += calculMontantBon(bon);
        }
        return total;
    }

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
