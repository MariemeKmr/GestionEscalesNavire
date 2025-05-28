package sn.ucad.retel1jour.mariemekamara.interfaces;

import sn.ucad.retel1jour.mariemekamara.be.BonPilotage;
import sn.ucad.retel1jour.mariemekamara.interfaces.ICalculerPrestation;
import java.util.Date;
import java.util.List;

public interface ICalculerPrestation {
	int calculNbrJours(Date dateDebut, Date dateFin);
    double calculMontantBon(BonPilotage leBon);
    double calculMontantSejour();
	List<BonPilotage> listBon();
	double calculerMontantTotal();
}

