package megawatt;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Joueur {
	private int id;
	private int argent;
	private int nbrVilles;
	private List<Usine> usines;
	private List<Ville> villes;
	private int[] ressources;
	private Color couleur;
	
	public Joueur(int id, int argent, int nbrVilles, List<Usine> usines, List<Ville> villes, int[] ressources, Color couleur) {
		super();
		this.id = id;
		this.argent = argent;
		this.nbrVilles = nbrVilles;
		this.usines = usines;
		this.villes = villes;
		this.ressources = ressources;
		this.couleur=couleur;
	}
	/**
	 * demande aux joueurs de selectionner les usines à activer( 
	 * et demande les ressources necessaires en fonction des usines)
	 * vérifie si les usines choisient sont activables et retourne le 
	 * nombres de villes alimentées. 
	 * @return
	 */
	public int choisirUsines(){
		return 0;
	}
	/**
	 * actualise l'argent du joueur en fonction des villes alimentées
	 */
	//à supprimer
	public void remunererJoueur(int nbreVillesAlimentees){
		switch(nbreVillesAlimentees){
		case 0:
			this.argent=this.argent+10;
			break;
		case 1:
			this.argent=this.argent+22;
			break;
		case 2:
			this.argent=this.argent+33;
			break;
		case 3: 
			this.argent=this.argent+44;
			break;
		case 4:
			this.argent=this.argent+54;
			break;
		case 5:
			this.argent=this.argent+64;
			break;
		case 6:
			this.argent=this.argent+73;
			break;
		case 7:
			this.argent=this.argent+82;
			break;
		case 8:
			this.argent=this.argent+90;
			break;
		case 9:
			this.argent=this.argent+98;
			break;
		case 10:
			this.argent=this.argent+105;
			break;
		case 11:
			this.argent=this.argent+112;
			break;
		case 12:
			this.argent=this.argent+118;
			break;
		case 13:
			this.argent=this.argent+124;
			break;
		case 14:
			this.argent=this.argent+129;
			break;
		case 15:
			this.argent=this.argent+134;
			break;
		case 16:
			this.argent=this.argent+138;
			break;
		case 17:
			this.argent=this.argent+142;
			break;
		case 18:
			this.argent=this.argent+145;
			break;
		case 19:
			this.argent=this.argent+148;
			break;
		case 20:
			this.argent=this.argent+150;
			break;
		}
	}
	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getArgent() {
		return argent;
	}

	public void setArgent(int argent) {
		this.argent = argent;
	}

	public int getNbrVilles() {
		return nbrVilles;
	}

	public void setNbrVilles(int nbrVilles) {
		this.nbrVilles = nbrVilles;
	}

	public List<Usine> getUsines() {
		return usines;
	}

	public void setUsines(List<Usine> usines) {
		this.usines = usines;
	}

	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public int[] getRessources() {
		return ressources;
	}

	public void setRessources(int[] ressources) {
		this.ressources = ressources;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + argent;
		result = prime * result + id;
		result = prime * result + nbrVilles;
		result = prime * result + Arrays.hashCode(ressources);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Joueur other = (Joueur) obj;
		if (argent != other.argent)
			return false;
		if (id != other.id)
			return false;
		if (nbrVilles != other.nbrVilles)
			return false;
		if (!Arrays.equals(ressources, other.ressources))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Joueur [id=" + id + ", argent=" + argent + ", nbrVilles=" + nbrVilles + ", ressources="
				+ Arrays.toString(ressources) + "]";
	}
	public void acheterRessources(){
		
	}
	/**
	 * ajoute une usine au joueur tout en vérifiant ses ressources et
	 * son nombre d'usines.
	 * @param usine
	 */
	public void ajouterUsine(Usine usine){
		
	}
	
	
}
