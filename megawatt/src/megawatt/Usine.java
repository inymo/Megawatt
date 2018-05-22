package megawatt;

import java.util.Arrays;

public class Usine {
	private int[] ressources;// ressources dont les usines ont besoin pour fonctionner.
	private int prixInitial;
	private int villeAlimentable;
	private int couleurs; //0 clair et 1 foncé
	public Usine(int[] ressources, int prixInitial, int villeAlimentable, int couleurs) {
		super();
		this.ressources = ressources;
		this.prixInitial = prixInitial;
		this.villeAlimentable = villeAlimentable;
		this.couleurs = couleurs;
	}
	public int[] getRessources() {
		return ressources;
	}
	public void setRessources(int[] ressources) {
		this.ressources = ressources;
	}
	public int getPrixInitial() {
		return prixInitial;
	}
	public void setPrixInitial(int prixInitial) {
		this.prixInitial = prixInitial;
	}
	public int getVilleAlimentable() {
		return villeAlimentable;
	}
	public void setVilleAlimentable(int villeAlimentable) {
		this.villeAlimentable = villeAlimentable;
	}
	public int getCouleurs() {
		return couleurs;
	}
	public void setCouleurs(int couleurs) {
		this.couleurs = couleurs;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + couleurs;
		result = prime * result + prixInitial;
		result = prime * result + Arrays.hashCode(ressources);
		result = prime * result + villeAlimentable;
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
		Usine other = (Usine) obj;
		if (couleurs != other.couleurs)
			return false;
		if (prixInitial != other.prixInitial)
			return false;
		if (!Arrays.equals(ressources, other.ressources))
			return false;
		if (villeAlimentable != other.villeAlimentable)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Usine [ressources=" + Arrays.toString(ressources) + ", prixInitial=" + prixInitial
				+ ", villeAlimentable=" + villeAlimentable + ", couleurs=" + couleurs + "]";
	}
	
}
