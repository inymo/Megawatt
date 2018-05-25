package megawatt;

import java.awt.List;
import java.util.ArrayList;
import java.util.Scanner;

public class MarcheU {
	private ArrayList<Usine> piocheBlanche;
	private ArrayList<Usine> piocheSombre;
	private ArrayList<Usine> pioche;
	private ArrayList<Usine> usinesDispo;

	
	//problème : return une array list
	//			Lorsqu'on choisi l'usine, on est obligé d'enchérie
	//			Soit on saute le tours du joueur après nous, soit on est obligé de réencherir
	public ArrayList<Joueur> lancerEnchere(ArrayList<Joueur> joueurs, int etape, ArrayList<Usine> usines) {
		ArrayList<Joueur> joueurfin = joueurs; // liste de joueur a retourner et
												// donc update
		ArrayList<Joueur> joueurencours = joueurs; // liste secondaire pour
													// permettre de savoir qui a
													// une usine ou n'en veut
													// pas
		int prix = 0;
		while (!joueurencours.isEmpty()) { // début des enchere avec choix de
											// l'usine

			Usine usineencours = this.choixUsine(joueurencours.get(0), usines); // le
																			// premier
																			// joueur
																			// choisie
																			// une
																			// usine
			if (usineencours == null) { // si il ne choisi pas d'usine, ils sort
										// des enchères
				joueurencours.remove(0);
			} else { // sinon on continue normalement
				prix = usineencours.getPrixInitial(); // on créer le prix des usines
				System.out.println("début des enchères, le prix de départ est " + prix); // previent les joueurs
				ArrayList<Joueur> enjeu = (ArrayList<Joueur>) joueurencours.clone(); // création d'une seconde liste pour savoir qui est en jeu
				boolean premiertour = true;		//pour l'achat de cette usine
				boolean passer = false;
		

				
				while (enjeu.size() >1) {// tant qu'il ne reste pas une personne en jeu pour l'achat de lusine
						for (int i = (premiertour?1:0); i < enjeu.size(); i++) {// on parcours la liste
							premiertour=false;
							System.out.println("c'est au joueur " + enjeu.get(i).getId() + " d'enchérir, tu as "+enjeu.get(i).getArgent());
							System.out.println("le prix est a " + prix + " combien veux tu rajouter?");
							System.out.println("Attention, si le prix de l'usine et la mise que tu rajoute dépassent ton argent, tu passeras automatiquement");
							System.out.println("si tu ne veux pas de cette usine,tape 0");
							Scanner sc = new Scanner(System.in);
							int nb = sc.nextInt(); // pour demander un int
							if (nb == 0 || prix + nb > enjeu.get(i).getArgent()) {
								enjeu.remove(i);
								i--;
								if(enjeu.size() ==1){
									break;
								}
								}else {
								prix = nb + prix; // on met a jour le prix jusqu'a ce qu'il n'en reste qu'un
							}
							//}while(passer);
					}
				
					

				}
				System.out.println("felicitation, le joueur "+enjeu.get(0).getId()+" remporte l'usine !");
				System.out.println("te voila dépouillé de "+prix+" !!");
				for(int i =0; i<joueurfin.size();i++){
					if(joueurfin.get(i).getId() == enjeu.get(0).getId()){ // on cherche le joueur dans la liste final
						int argentfin = joueurfin.get(i).getArgent()-prix; // on lui retire le prix de l'usine
						joueurfin.get(i).setArgent(argentfin); // on met a jours son argent
						joueurfin.get(i).ajouterUsine(usineencours); // on lui ajoute l'usine
						joueurencours.remove(joueurfin.get(i)); // on l'enleve de l'enchère generale
						//ajouter enlever usine et atualiser marché
					}
				}
			}
		}
		return joueurfin;
	}

	/**
	 * tire une carte de la pioche et on trie le marché dans l'ordre croissant.
	 * 
	 * @param etape
	 */
	public void actualiserMarcheEnchere() {
		int place = this.usinesDispo.size()-1;;
		for(int i = 0; i<this.usinesDispo.size();i++){
			if(this.usinesDispo.get(i).getPrixInitial() > this.pioche.get(0).getPrixInitial() ){
				place = i-1;
				break;
			}
		}
		this.usinesDispo.add(place, this.pioche.get(0));
		this.pioche.remove(0);
	}

	public void actualiserMarcheEtape(int etape) {

	}

	/**
	 * initialise les trois listes de pioche
	 */
	public void initialiserPoche() {

	}

	public ArrayList<Usine> getUsinesAchetables(int etape) {
		return null;
	}

	/**
	 * Affiche les usines du marché et demande à joueur de choisir parmi les
	 * usines achetable
	 * 
	 * @param joueur
	 * @param usines
	 * @return
	 */
	public Usine choixUsine(Joueur joueur, ArrayList<Usine> usines) {
		Usine usinechoisie = null;
		System.out.println("c'est au joueur " + joueur.getId() + " de jouer");
		System.out.println("Choisissez une usine a mettre en enchere parmis les suivante :");
		System.out.println("Usine 1 : " + usines.get(0).toString());
		System.out.println("Usine 2 : " + usines.get(1).toString());
		System.out.println("Usine 3 : " + usines.get(2).toString());
		System.out.println("Usine 4 : " + usines.get(3).toString());
		System.out.println("Tapez 1,2,3 ou 4 pour choisir une Usine ou 0 pour passer");

		boolean choix = true;
		while (choix) {
			Scanner sc = new Scanner(System.in);
			int nb = sc.nextInt(); // pour demander un int
			if (nb < 0 || nb > 4) {
				System.out.println("faites attentions s'il vous plait, retaper un chiffre entre 0 et 4");
				nb = sc.nextInt(); // pour demander un int
			} else {
				if (nb != 0) {
					usinechoisie = usines.get(nb - 1);
				}
				choix = false;
			}

		}
		return usinechoisie;

	}

	/**
	 * enlève une usine du marché et update en tirant une usine de la pioche
	 * 
	 * @param usine
	 */
	public void enleverUsine(Usine usine) {
		this.usinesDispo.remove(usine);
	}
}
