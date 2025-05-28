package sn.ucad.retel1jour.mariemekamara.be;

import java.util.Date;

public class BonPilotage {
	private int idMouvement;
	private Escale monEscale;
    private Date  dateDeBon;
    private String posteaQuai;
    private TypeMouvement typeMouvement;
    
    // Constructeurs
    public BonPilotage() {}
    
    public BonPilotage(int idMouvement, Escale monEscale, Date dateDeBon, String posteaQuai, TypeMouvement typeMouvement) {
        this.idMouvement=idMouvement;
        this.monEscale=monEscale;
        this.dateDeBon =dateDeBon;
    	this.posteaQuai = posteaQuai;
        this.typeMouvement = typeMouvement;
    }
    
    // Getters et Setters
    public int getIdMouvement() { 
    	return idMouvement; 
    	}
    public void setIdMouvement(int idMouvement) { 
    	this.idMouvement = idMouvement; 
    	}
    
    
    public Escale getMonEscale() { 
    	return monEscale;
    	}
    public void setMonEscale(Escale monEscale) { 
    	this.monEscale = monEscale;
    	}
    
    
    public Date getDateDeBon() {
    	return dateDeBon; 
    	}
    public void setDateDeBon(Date dateDeBon) { 
    	this.dateDeBon = dateDeBon;     
    	}
    
    
    public String getPosteQuai() {
    	return posteaQuai; 
    	}
    public void setPosteQuai(String posteQuai) {
    	this.posteaQuai = posteQuai; 
    	}
    
    
    public TypeMouvement getTypeMouvement() { 
    	return typeMouvement; 
    	}
    public void setTypeMouvement(TypeMouvement typeMouvement) { 
    	this.typeMouvement = typeMouvement; 
    	}
}

