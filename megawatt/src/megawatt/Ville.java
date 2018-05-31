package megawatt;

import java.util.Arrays;
import java.util.HashMap;

public class Ville {
	private String Nom;
	private HashMap<Ville, Integer> adjacent;
	private Integer[] espace={-1,-1,-1};
	@Override
	public String toString() {
		return this.getNom();
			}
	public Ville(String nom, HashMap<Ville, Integer> adjacent, Integer[] espace) {
		super();
		Nom = nom;
		this.adjacent = adjacent;
		this.espace = espace;
	}
		public Ville(String nom) {
		Nom = nom;
		this.adjacent = new HashMap<Ville, Integer>();

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
	public HashMap<Ville, Integer> getAdjacent() {
		return adjacent;
	}
		public void ajouterVilleAdjacente(Ville u, int cout) {
		this.adjacent.put(u, cout);
		u.adjacent.put(this, cout);

	}
	

	public Integer[] getEspace() {
		return espace;
	}
	public void setEspace(Integer[] espace) {
		this.espace = espace;
	}
		//////////
	public boolean estAdjacent(Ville u) {
		return this.adjacent.containsKey(u);
	}

	public int getCoutAdjacent(Ville u) {
		if (this.estAdjacent(u)) {

			return this.adjacent.get(u);
		}
		return -1;
	}
	
}
