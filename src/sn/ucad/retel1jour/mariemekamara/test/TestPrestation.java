package sn.ucad.retel1jour.mariemekamara.test;

import sn.ucad.retel1jour.mariemekamara.interfaces.CalculPrestation;
import sn.ucad.retel1jour.mariemekamara.be.*;
import java.text.SimpleDateFormat;

public class TestPrestation {
    public static void main(String[] args) {
        CalculPrestation calculer = new CalculPrestation();
        
        System.out.println("=== FACTURE DE PRESTATION ===");
        System.out.println();
        
        // Informations du navire
        Navire navire = calculer.getNavire();
        System.out.println("NAVIRE: " + navire.getNomNavire() + " (" + navire.getNumeroNavire() + ")");
        System.out.println("Longueur: " + navire.getLongueurNavire() + " - Largeur " + navire.getLargeurNavire() + ")");
        System.out.println("Volume: " + navire.getVolumeNavire() + " - Tirant d'eau: " + navire.getTirantEauNavire());
        System.out.println();
        
        // Informations de l'escale
        Escale escale = calculer.getEscale();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("ESCALE N°: " + escale.getNumeroEscale());
        System.out.println("Période: du " + sdf.format(escale.getDebutEscale()) + 
                          " au " + sdf.format(escale.getFinEscale()));
        System.out.println();
        
        // Détail des bons
        System.out.println("DETAIL DES BONS DE PILOTAGE:");
        double totalBons = 0;
        for (BonPilotage bon : calculer.getMesBons()) {
            double montant = calculer.calculMontantBon(bon);
            totalBons += montant;
            System.out.println("- " + bon.getTypeMouvement().getLibelleTypeMvt() + 
                             " (Poste " + bon.getPosteQuai() + "): " + 
                             String.format("%.0f", montant) + " CFA");
        }
        System.out.println("Sous-total bons: " + String.format("%.0f", totalBons) + " CFA");
        System.out.println();
        
        // Coût du séjour
        double montantSejour = calculer.calculMontantSejour();
        System.out.println("COUT DU SEJOUR: " + String.format("%.0f", montantSejour) + " CFA");
        System.out.println();
        
        // Total général
        double total = calculer.calculerMontantTotal();
        System.out.println("TOTAL GENERAL: " + String.format("%.0f", total) + " CFA");
    }
}

