package sn.ucad.retel1jour.mariemekamara.test;

import sn.ucad.retel1jour.mariemekamara.interfaces.CalculPrestation;
import sn.ucad.retel1jour.mariemekamara.be.*;
import java.text.SimpleDateFormat;
import sn.ucad.retel1jour.mariemekamara.be.Consignataire;

public class TestPrestation {
    public static void main(String[] args) {
        CalculPrestation calculer = new CalculPrestation();

        System.out.println("=== FACTURE DE PRESTATION ===");
        System.out.println();

        // Informations du navire
        Navire navire = calculer.getNavire();
        System.out.println("NAVIRE: " + navire.getNomNavire() + " (" + navire.getNumeroNavire() + ")");
        System.out.println("Longueur: " + navire.getLongueurNavire() + " - Largeur " + navire.getLargeurNavire());
        System.out.println("Volume: " + navire.getVolumeNavire() + " - Tirant d'eau: " + navire.getTiranEauNavire());
        System.out.println();

        // Informations de l'escale
        Escale escale = calculer.getEscale();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("ESCALE N°: " + escale.getNumeroEscale());
        System.out.println("Période: du " + sdf.format(escale.getDebutEscale()) +
                          " au " + sdf.format(escale.getFinEscale()));
        System.out.println();

        // Informations du consignataire
        Consignataire c = escale.getConsignataire();
        System.out.println("CONSIGNATAIRE:");
        System.out.println("- ID : " + c.getIdConsignataire());
        System.out.println("- Raison sociale : " + c.getRaisonSociale());
        System.out.println("- Adresse        : " + c.getAdresse());
        System.out.println("- Téléphone      : " + c.getTelephone());
        System.out.println();

        // Détail des bons
        System.out.println("DETAIL DES BONS DE PILOTAGE:");
        double totalBons = 0;
        for (BonPilotage bon : calculer.getMesBons()) {
            double montant = calculer.calculMontantBon(bon);
            String typeMouvement = bon.getTypeMouvement().getLibelleTypeMvt();

            if (typeMouvement.equalsIgnoreCase("Sortie du port")) {
                System.out.println("- " + typeMouvement
                        + " (Poste " + bon.getPosteAQuai() + " libéré): "
                        + String.format("%.0f", montant) + " CFA");
            } else {
                System.out.println("- " + typeMouvement
                        + " (Poste " + bon.getPosteAQuai() + "): "
                        + String.format("%.0f", montant) + " CFA");
            }

            totalBons += montant;
        }


        System.out.println("SOUS-TOTAL BONS: " + String.format("%.0f", totalBons) + " CFA");
        System.out.println();

        System.out.println("TARIFS APPLIQUÉS:");
        for (BonPilotage bon : calculer.getMesBons()) {
            double tarif = calculer.getTarifParMouvement(bon);
            System.out.println("- " + bon.getTypeMouvement().getLibelleTypeMvt() + ": " +
                    String.format("%.0f", tarif) + " CFA");
        }
        System.out.println("- Tarif par jour = " + calculer.getTarifParJour() + " CFA");
        int jours = calculer.calculNbrJours(escale.getDebutEscale(), escale.getFinEscale());
        System.out.println("- Nombre de jours de séjour : " + jours + " jours");
        // Coût du séjour
        System.out.println("COUT DU SEJOUR : " + (jours * calculer.getTarifParJour()) + " CFA");
        System.out.println();

        // Total général
        double total = calculer.calculerMontantTotal();
        System.out.println("TOTAL GENERAL: " + String.format("%.0f", total) + " CFA");
    }
}

