package sn.ucad.retel1jour.mariemekamara.be;

import java.util.Date;

public class Escale {
    private String numeroEscale;
    private Date debutEscale;
    private Date finEscale;
    private Navire myNavire;
    private double prixSejour;

    
    // Constructeurs
    public Escale() {}
    
    public Escale(String numeroEscale, Date debutEscale, Date finEscale, Navire myNavire, double prixSejour) {
        this.numeroEscale = numeroEscale;
        this.debutEscale = debutEscale;
        this.finEscale = finEscale;
        this.myNavire = myNavire;
        this.prixSejour = prixSejour;

    }

	// Getters et Setters
    public String getNumeroEscale() { 
    	return numeroEscale; 
    	}
    public void setNumeroEscale(String numeroEscale) {
    	this.numeroEscale = numeroEscale; 
    	}
    
    public Date getDebutEscale() { 
    	return debutEscale; 
    	}
    public void setDebutEscale(Date debutEscale) {
    	this.debutEscale = debutEscale;
    	}
    
    public Date getFinEscale() { 
    	return finEscale; 
    	}
    public void setFinEscale(Date finEscale) { 
    	this.finEscale = finEscale; 
    	}
    
    public Navire getMyNavire() { 
    	return myNavire; 
    	}
    public void setMyNavire(Navire myNavire) { 
    	this.myNavire = myNavire; 
    	}
    
    public double getPrixSejour() { 
    	return prixSejour; 
    	}
    public void setPrixSejour(double prixSejour) {
    	this.prixSejour = prixSejour; 
    	}
}

