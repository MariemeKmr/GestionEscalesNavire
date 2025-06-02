package sn.ucad.retel1jour.mariemekamara.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import sn.ucad.retel1jour.mariemekamara.interfaces.CalculPrestation;
import sn.ucad.retel1jour.mariemekamara.be.*;

public class InterfaceGraphique extends JFrame {
    private JTextArea resultArea;
    private CalculPrestation calculer;
    
    public InterfaceGraphique() {
        calculer = new CalculPrestation();wxxxxxwq<>wxwxxq
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Gestion des Escales - Port de Dakar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Titre
        JLabel titre = new JLabel("FACTURATION DES PRESTATIONS NAVIRES", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 16));
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titre, BorderLayout.NORTH);
        
        // Zone de texte pour les résultats
        resultArea = new JTextArea(20, 50);
        resultArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
        
        // Bouton AFFICHER
        JButton btnAfficher = new JButton("AFFICHER");
        btnAfficher.setFont(new Font("Arial", Font.BOLD, 14));
        btnAfficher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                afficherResultat();
            }
        });
        
        JPanel panelBouton = new JPanel();
        panelBouton.add(btnAfficher);
        add(panelBouton, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
    }

    private void afficherResultat() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== FACTURE DE PRESTATION ===\n\n");

        // Informations du navire
        Navire navire = calculer.getNavire();
        sb.append("NAVIRE: ").append(navire.getNomNavire()).append(" (").append(navire.getNumeroNavire()).append(")\n");
        sb.append("Longueur: ").append(navire.getLongueurNavire()).append(" - Largeur: ").append(navire.getLargeurNavire()).append("\n");
        sb.append("Volume: ").append(navire.getVolumeNavire()).append(" - Tirant d'eau: ").append(navire.getTiranEauNavire()).append("\n\n");

        // Informations de l'escale
        Escale escale = calculer.getEscale();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sb.append("ESCALE N°: ").append(escale.getNumeroEscale()).append("\n");
        sb.append("Période: du ").append(sdf.format(escale.getDebutEscale()))
                .append(" au ").append(sdf.format(escale.getFinEscale())).append("\n\n");

        // Informations du consignataire
        Consignataire c = escale.getConsignataire();
        if (c != null) {
            sb.append("CONSIGNATAIRE:\n");
            sb.append("- ID              : ").append(c.getIdConsignataire()).append("\n");
            sb.append("- Raison sociale  : ").append(c.getRaisonSociale()).append("\n");
            sb.append("- Adresse         : ").append(c.getAdresse()).append("\n");
            sb.append("- Téléphone       : ").append(c.getTelephone()).append("\n\n");
        }

        // Détail des bons
        sb.append("DETAIL DES BONS DE PILOTAGE:\n");
        double totalBons = 0;
        for (BonPilotage bon : calculer.getMesBons()) {
            double montant = calculer.calculMontantBon(bon);
            totalBons += montant;
            sb.append("- ").append(bon.getTypeMouvement().getLibelleTypeMvt())
                    .append(" (Poste ").append(bon.getPosteAQuai()).append("): ")
                    .append(String.format("%.0f", montant)).append(" CFA\n");
        }
        sb.append("Sous-total bons: ").append(String.format("%.0f", totalBons)).append(" CFA\n\n");

        // TARIFS APPLIQUÉS (sans modifier totalBons)
        sb.append("TARIFS APPLIQUÉS:\n");
        for (BonPilotage bon : calculer.getMesBons()) {
            double montant = calculer.calculMontantBon(bon);
            double tarifMouvement = calculer.getTarifParMouvement(bon);
            sb.append("- ").append(bon.getTypeMouvement().getLibelleTypeMvt())
                    .append(" (Poste ").append(bon.getPosteAQuai()).append("): ")
                    .append(String.format("%.0f", montant)).append(" CFA")
                    .append(" (Tarif: ").append(String.format("%.0f", tarifMouvement)).append(" CFA)\n");
        }

        sb.append("- Tarif par jour de séjour : ").append(String.format("%.0f", calculer.getTarifParJour())).append(" CFA\n\n");

        // Coût du séjour
        double montantSejour = calculer.calculMontantSejour();
        sb.append("COUT DU SEJOUR: ").append(String.format("%.0f", montantSejour)).append(" CFA\n\n");

        // Total général
        double total = calculer.calculerMontantTotal();
        sb.append("TOTAL GENERAL: ").append(String.format("%.0f", total)).append(" CFA\n");

        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfaceGraphique().setVisible(true);
            }
        });
    }
}
