package megawatt;

import java.util.Arrays;
import java.util.HashMap;

public class Ville {
	private String Nom;
	private HashMap<String, Integer> adjacent;
	private Integer[] espace={-1,-1,-1};
	@Override
	public String toString() {
		return "Ville [Nom=" + Nom + ", adjacent=" + adjacent + ", espace=" + Arrays.toString(espace) + "]";
	}
	public Ville(String nom, HashMap<String, Integer> adjacent, Integer[] espace) {
		super();
		Nom = nom;
		this.adjacent = adjacent;
		this.espace = espace;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Nom == null) ? 0 : Nom.hashCode());
		result = prime * result + ((adjacent == null) ? 0 : adjacent.hashCode());
		result = prime * result + Arrays.hashCode(espace);
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
		Ville other = (Ville) obj;
		if (Nom == null) {
			if (other.Nom != null)
				return false;
		} else if (!Nom.equals(other.Nom))
			return false;
		if (adjacent == null) {
			if (other.adjacent != null)
				return false;
		} else if (!adjacent.equals(other.adjacent))
			return false;
		if (!Arrays.equals(espace, other.espace))
			return false;
		return true;
	}
	public String getNom() {
		return Nom;
	}
	public void setNom(String nom) {
		Nom = nom;
	}
	public HashMap<String, Integer> getAdjacent() {
		return adjacent;
	}
	public void setAdjacent(HashMap<String, Integer> adjacent) {
		this.adjacent = adjacent;
	}
	public Integer[] getEspace() {
		return espace;
	}
	public void setEspace(Integer[] espace) {
		this.espace = espace;
	}
	
	
}
