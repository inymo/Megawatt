package megawatt;

import java.awt.Color;
import java.util.Arrays;

public class Initialisation {
	//Il est � noter que le jeu ne concentre pour l'instant que sur le plateau am�ricain
	
	private int[][][] paquets_reapprovisionnement_ressources; //int[nb_de_joueurs (3 � 6)][nb_d'�tapes (3)][quantite_par_ressource (4)]
	private int[][] paquet_reapprovisionnement_du_jeu; //paquet qui sera utilis�: int[nb_d'�tapes (3)][quantite_par_ressource (4)]
	public int[][] villes_et_interconnexions_total; /*sur la diagonale: nombre de villes occup�es:
													0 : 0 villes occup�es
													1 : ville de prix 10 occup�e
													2 : villes de prix 10 et 15 occup�es
													3 : toutes les villes occup�es
													
												sur les autres cases:
													-1 : pas de connexion directe entre la ville i et j
													autre nombre : connexion dont le prix est la valeur du nombre
													
												int [n][n+1] : la derni�re colonne permet de connaitre la zone � laquelle appartient la ville de la ligne i
												*/
	private int[][] villes_et_interconnexions_du_jeu;  //d�termin� en fonction du nombre de joueurs
	private double[][] position_ville; //int[49][4] avec int[i]=[posX,posY,largeur,hauteur]  la position est d�finie par rapport au coin sup�rieur gauche du rectangle, les donn�es sont en pourcentage (taille ville par rapport au plateau)
	private boolean[] zones_choisies; //length=7, la valeur int[i]=0 si la zone i n'a pas �t� choisi, 1 sinon
	private boolean[][] zones_et_interconnexions_total; //int[7][7]   mettre juste la valeur 1 s'il y a une connexion directe entre 2 zones
	private int[][] zones_et_interconnexions_du_jeu; //qui sera en fonction du nombre et des choix des joueurs
	private int nb_joueurs;
	private String[] nom_villes;   //permet de retrouver le nom de la ville attach� � son num�ro
	private int[] ressources_debut;
	private Color[] couleurs_joueurs;
	
	public Initialisation(){
		//� initialiser
		int[][][] paquet={{{2,2,3,2},{5,2,1,1},{2,4,3,2}},  	//�tapes 1,2,3 pour 2 joueurs
						  {{2,2,3,2},{5,2,1,1},{2,4,3,2}},		//�tapes 1,2,3 pour 3 joueurs
						  {{3,3,3,2},{6,3,2,1},{3,5,4,2}},		//�tapes 1,2,3 pour 4 joueurs
						  {{4,3,4,3},{7,3,2,2},{4,6,5,3}},		//�tapes 1,2,3 pour 5 joueurs
						  {{5,4,5,3},{8,4,3,2},{5,7,6,4}}};		//�tapes 1,2,3 pour 6 joueurs
		this.paquets_reapprovisionnement_ressources=paquet;
		this.position_ville=new double[][]
				{{0.440,0.799,0.067,0.027}, //MEXICO CITY SOUTH
				 {0.438,0.830,0.069,0.027}, //MEXICO CITY NORTH
				 {0.338,0.785,0.068,0.030}, //GUADALAJARA
				 {0.365,0.721,0.069,0.027}, //MONTERREY
				 {0.256,0.673,0.070,0.029}, //CHIHUAHUA
				 {0.240,0.589,0.070,0.027}, //JUAREZ
				 {0.240,0.521,0.070,0.027}, //ALBUQUERQUE
				 {0.088,0.567,0.070,0.031}, //SAN DIEGO
				 {0.044,0.508,0.072,0.030}, //LOS ANGELES
				 {0.015,0.391,0.068,0.029}, //SAN FRANCISCO
				 {0.029,0.265,0.068,0.028}, //PORTLAND
				 {0.137,0.425,0.068,0.027}, //LAS VEGAS
				 {0.178,0.325,0.070,0.028}, //SALT LAKE CITY
				 {0.256,0.385,0.070,0.028}, //DENVER
				 {0.033,0.201,0.067,0.028}, //SEATTLE
				 {0.029,0.138,0.068,0.026}, //VANCOUVER
				 {0.107,0.079,0.068,0.030}, //CALGARY
				 {0.150,0.021,0.068,0.027}, //EDMONTON
				 {0.256,0.139,0.071,0.028}, //REGINA
				 {0.396,0.140,0.068,0.029}, //WINNIPEG
				 {0.440,0.261,0.066,0.027}, //MINNEAPOLIS
				 {0.544,0.278,0.068,0.028}, //MILWAUKEE
				 {0.558,0.329,0.067,0.026}, //CHICAGO
				 {0.579,0.383,0.07,0.027}, //INDIANAPOLIS
				 {0.500,0.428,0.069,0.029}, //SAINT LOUIS
				 {0.410,0.398,0.069,0.029}, //KANSAS CITY
				 {0.394,0.503,0.068,0.027}, //OKLAHOMA CITY
				 {0.526,0.521,0.067,0.028}, //MEMPHIS
				 {0.382,0.663,0.068,0.027}, //SAN ANTONIO
				 {0.450,0.613,0.069,0.028}, //HOUSTON
				 {0.415,0.561,0.068,0.027}, //DALLAS/FORT WORTH
				 {0.550,0.616,0.070,0.028}, //NEW ORLEANS
				 {0.644,0.559,0.070,0.028}, //ATLANTA
				 {0.676,0.629,0.070,0.025}, //JACKSONVILLE
				 {0.682,0.697,0.069,0.028}, //MIAMI
				 {0.718,0.520,0.070,0.027}, //CHARLOTTE
				 {0.591,0.451,0.067,0.029}, //NASHVILLE
				 {0.754,0.450,0.068,0.029}, //WASHINGTON
				 {0.672,0.405,0.070,0.027}, //COLUMBUS
				 {0.733,0.364,0.070,0.028}, //PITTSBURGH
				 {0.677,0.322,0.069,0.026}, //DETROIT
				 {0.713,0.279,0.069,0.027}, //TORONTO
				 {0.820,0.406,0.07,0.027}, //PHILADELPHIA
				 {0.830,0.354,0.070,0.026}, //NEW YORK SOUTH
				 {0.830,0.322,0.070,0.026}, //NEW YORK NORTH
				 {0.797,0.249,0.070,0.028}, //OTTAWA
				 {0.916,0.311,0.070,0.028}, //BOSTON
				 {0.895,0.253,0.066,0.027}, //MONTREAL
				 {0.912,0.196,0.068,0.029}  //QUEBEC
				};
		
		this.nom_villes=new String[]{"MEXICO CITY SOUTH","MEXICO CITY NORTH","GUADALAJARA","MONTERREY","CHIHUAHUA","JUAREZ","ALBUQUERQUE", //zone 1 (violette)
						 			 "SAN DIEGO","LOS ANGELES","SAN FRANCISCO","PORTLAND","LAS VEGAS","SALT LAKE CITY","DENVER",		   //zone 2 (bleue)	
						 			 "SEATTLE","VANCOUVER","CALGARY","EDMONTON","REGINA","WINNIPEG","MINNEAPOLIS",						   //zone 3 (jaune)	
						 			 "MILWAUKEE","CHICAGO","INDIANAPOLIS","SAINT LOUIS","KANSAS CITY","OKLAHOMA CITY","MEMPHIS",		   //zone 4 (verte)
						 			 "SAN ANTONIO","HOUSTON","DALLAS/FORT WORTH","NEW ORLEANS","ATLANTA","JACKSONVILLE","MIAMI",		   //zone 5 (orange)
						 			 "CHARLOTTE","NASHVILLE","WASHINGTON","COLUMBUS","PITTSBURGH","DETROIT","TORONTO",					   //zone 6 (rouge)
						 			 "PHILADELPHIA","NEW YORK SOUTH","NEW YORK NORTH","OTTAWA","BOSTON","MONTREAL","QUEBEC"};			   //zone 7 (cyan)
		int[][] villes_et_interconnexions_total=new int[][]{
			{ 0, 0,10,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //MEXICO CITY SOUTH
			{ 0, 0,10,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //MEXICO CITY NORTH
			{10,10, 0,16,23,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //GUADALAJARA
			{17,17,16, 0,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //MONTERREY
			{-1,-1,23,14, 0, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //CHIHUAHUA
			{-1,-1,-1,-1, 8, 0, 9,22,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,19,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //JUAREZ
			{-1,-1,-1,-1,-1, 9, 0,-1,25,-1,-1,18,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,1}, //ALBUQUERQUE
			{-1,-1,-1,-1,-1,-1,-1, 0, 3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //SAN DIEGO
			{-1,-1,-1,-1,-1,-1,-1,-1, 0,12,-1, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //LOS ANGELES
			{-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,20,16,22,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //SAN FRANCISCO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,22,-1, 5,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //PORTLAND
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,14,25,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //LAS VEGAS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,14,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //SALT LAKE CITY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,24,26,23,-1,-1,-1,-1,19,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,2}, //DENVER
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //SEATTLE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,16,-1,28,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //VANCOUVER
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 5,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //CALGARY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //EDMONTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,11,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //REGINA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,13,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //WINNIPEG
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,10,-1,-1,-1,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,3}, //MINNEAPOLIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //MILWAUKEE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 6, 9,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,10,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,4}, //CHICAGO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 9,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //INDIANAPOLIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,-1, 9,-1,-1,-1,-1,-1,-1,-1,-1, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //SAINT LOUIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,10,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //KANSAS CITY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,15,-1,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //OKLAHOMA CITY
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,15,14,12,-1,-1,-1, 7,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4}, //MEMPHIS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 6, 9,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //SAN ANTONIO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //HOUSTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,16,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //DALLAS/FORT WORTH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,16,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //NEW ORLEANS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,10,-1, 8, 8,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //ATLANTA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,12,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //JACKSONVILLE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,5}, //MIAMI
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,12,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,6}, //CHARLOTTE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,22,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,6}, //NASHVILLE
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,12, 7,-1,-1, 5,-1,-1,-1,-1,-1,-1,6}, //WASHINGTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 6, 7,-1,-1,-1,-1,-1,-1,-1,-1,6}, //COLUMBUS
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,11, 9,11,11,-1,-1,-1,-1,6}, //PITTSBURGH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 8,-1,-1,-1,-1,-1,-1,-1,6}, //DETROIT
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1,14,14, 8,-1,-1,-1,6}, //TORONTO
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 3, 3,-1,-1,-1,-1,7}, //PHILADELPHIA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 0,13, 7,12,-1,7}, //NEW YORK SOUTH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,13, 7,12,-1,7}, //NEW YORK NORTH
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,-1, 3,-1,7}, //OTTAWA
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 9,-1,7}, //BOSTON
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0, 5,7}, //MONTREAL
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 0,7}  //QUEBEC
		}; 
		for(int i=0;i<49;i++){
			for(int j=i+1;j<49;j++){
				villes_et_interconnexions_total[j][i]=villes_et_interconnexions_total[i][j];
			}
		}
		this.villes_et_interconnexions_total=villes_et_interconnexions_total;
		this.zones_et_interconnexions_total=new boolean[][]
				{{false,true ,false,true ,true ,false,false},   //zone 1 (violette)
				 {true ,false,true ,true ,false,false,false},	//zone 2 (bleue)	
				 {false,true ,false,true,false,false,false},	//zone 3 (jaune)	
				 {true ,true ,true ,false,true ,true ,false},	//zone 4 (verte)
				 {true ,false,false,true ,false,true ,false},	//zone 5 (orange)
				 {false,false,false,true ,true ,false,true },	//zone 6 (rouge)
				 {false,false,false,false,false,true ,false}};	//zone 7 (cyan)
		this.zones_choisies=new boolean[]{false,false,false,false,false,false,false};
		this.ressources_debut=new int[]{23,18,14,2};
		this.nb_joueurs=2;
		this.couleurs_joueurs=new Color[6];
	}
	
	public void paquet_reapprovisionnement_du_jeu(int nb_joueurs){
		this.paquet_reapprovisionnement_du_jeu=this.paquets_reapprovisionnement_ressources[nb_joueurs-2]; //parce qu'il peut pas y avoir 0 ou 1 joueur (#lonelyness)
	}
	
	public void mettre_a_jour_villes_et_zones(boolean[] zones_choisies){
		int i,ligne,colonne;
		
		
		//on d�termine le nombre de zones choisies
		int nb_zones=0;
		for(i=0;i<zones_choisies.length;i++){
			if(zones_choisies[i]) nb_zones++;
		}
		
		
		//PARTIE MISE A JOUR DES VILLES
		int[][] villes_et_interconnexions_du_jeu=new int[nb_zones*7][nb_zones*7+1];
		//initialisation de toute la matrice � -1
		for(ligne=0;ligne<villes_et_interconnexions_du_jeu.length;ligne++){
			for(colonne=0;colonne<villes_et_interconnexions_du_jeu.length;colonne++){
				villes_et_interconnexions_du_jeu[ligne][colonne]=-1;
			}
		}
		int index=0;
		for(i=0;i<zones_choisies.length;i++){
			if(zones_choisies[i]){
				for(ligne=0;ligne<7;ligne++){
					for(colonne=0;colonne<7;colonne++){
						villes_et_interconnexions_du_jeu[index*7+ligne][index*7+colonne]=this.villes_et_interconnexions_total[i*7+ligne][i*7+colonne];
					}
				}
				index++;
				System.out.println(index);
			}
		}
		this.villes_et_interconnexions_du_jeu=villes_et_interconnexions_du_jeu;
	}
	
	
	/**
	 * Permet de savoir si les zones qui ont �t� s�lectionn�es
	 * par l'utilisateur sont toutes adjacentes.
	 * @return boolean
	 */
	public boolean zones_choisies_valables(){
		int i,j;
		int nb_zones=0;
		for(i=0;i<zones_choisies.length;i++){
			if(zones_choisies[i]) nb_zones++;
		}
		
		int[] zones=new int[nb_zones];
		int index=0;
		for(i=0;i<zones_choisies.length;i++){
			if(zones_choisies[i]){
				zones[index]=i;
				index++;
			}
		}
		System.out.println(Arrays.toString(zones));
		boolean validation=false,bien_rentrer=false;
		
		
		//ici, on teste si le zone i est adjacente en n coups � au moins une autre zone choisie
		boolean[][] zones_et_interconnexions_choisies_ref=new boolean[7][7];
		boolean[][] zones_et_interconnexions_choisies=new boolean[7][7];
		//zones_et_interconnexions_choisies_ref=this.zones_et_interconnexions_total;
		//System.arraycopy(this.zones_et_interconnexions_total, 0, zones_et_interconnexions_choisies_ref, 0, 7);
		
		//on rend inaccessible les zones non choisies en mettant les interconnexions � false
		for(i=0;i<zones_choisies.length;i++){
			if(!zones_choisies[i]){
				for(j=0;j<7;j++){
					zones_et_interconnexions_choisies_ref[i][j]=false;
					zones_et_interconnexions_choisies_ref[j][i]=false;
				}
			}
		}
		//System.out.println("REFERENCE\n"+boolean_toString(zones_et_interconnexions_choisies_ref));
		zones_et_interconnexions_choisies=zones_et_interconnexions_choisies_ref;
		
		for(int n=1;n<=nb_zones-1;n++){
			//on calcule la matrice qui permet de savoir quelles zones on atteint en n coups
			for(i=0;i<n-1;i++) zones_et_interconnexions_choisies=produitBoolean(zones_et_interconnexions_choisies, zones_et_interconnexions_choisies_ref);
			//System.out.println(boolean_toString(zones_et_interconnexions_choisies));
			//on test s'il existe des chemins possible entre une zone choisie et une autre zone choisie
			for(i=0;i<nb_zones;i++){
			
				validation=false;
				bien_rentrer=false;
				for(j=0;j<nb_zones;j++){
					if(i-j>n-1||j-i>n-1){
						validation=validation||(zones_et_interconnexions_choisies[zones[i]][zones[j]]);
						bien_rentrer=true;
					}
				}
				//si il n'existe aucune interconnexion et qu'on est bien rentr� dans la boucle de test (i-j>n-1||j-i>n-1) (parce que si on n'y rentre pas, le programme retourne 
				//faux parce qu'il dit par exemple qu'une ville qui n'est connect� que par n+1 aux autres villes n'est pas connect�e � celles-ci en n+2 ce qui est con
				if(!validation&&bien_rentrer) return false;
			}
		}
		
		return validation;
	}
	
	/**
	 * La m�thode pour savoir si les zones sont adjacentes est la suivante:
	 * On part de la premi�re zone choisie, puis on regarde qu'elles sont les zones voisines choisies adjacentes.
	 * On valide les zones proches, puis on regarde si toutes les autres zones choisies non valid�es sont adjacentes aux pr�c�dentes zones valider
	 * S'il n'y a aucune nouvelle zone choisie valid�e lors d'une boucle et que toutes les zones choisies n'ont pas �t� valid�es, c'est que les zones ne sont pas
	 * adjacentes.
	 * @return
	 */
	public boolean zones_valables(){
		boolean au_moins_une_nouvelle_zone_valide=true;
		
		boolean[] zones_choisies_valides=new boolean[7];   //copie de zones_choisies pour pr�server le tableau initial
		//on ne garde valid� que la premi�re zone choisie au d�part donc on met toutes les autres � false
		boolean premiere_zone_valide=false;
		for(int i=0;i<7;i++){
			if(!premiere_zone_valide){
				if(this.zones_choisies[i]){
					zones_choisies_valides[i]=this.zones_choisies[i];
					premiere_zone_valide=true;
				}else zones_choisies_valides[i]=false;
			}else zones_choisies_valides[i]=false;
		}
		
		while(au_moins_une_nouvelle_zone_valide){
			boolean[] zones_choisies_valides_suivant=copy_boolean(zones_choisies_valides); //ce tableau servira � savoir s'il y a eu un changement
			au_moins_une_nouvelle_zone_valide=false;
			
	
			for(int i=0;i<zones_choisies_valides.length;i++){
				//si la zone est valid�e, on regarde si elle est connect�e avec d'autres zones choisies pas encore valid�es
				if(zones_choisies_valides[i]){
					for(int j=0;j<this.zones_et_interconnexions_total[0].length;j++){
						//si la zone i est adjacente � la zone j et que la zone j n'a pas encore �t� valid�e et que la zone j �tait choisie, on la valide
						if(zones_et_interconnexions_total[i][j]&&!zones_choisies_valides[j]&&this.zones_choisies[j]){
							zones_choisies_valides_suivant[j]=true;
							au_moins_une_nouvelle_zone_valide=true;
						}
					}
				}
			}
			
			//si on a valid� au moins une nouvelle zone,il faudra r�it�rer l'op�ration donc on prend une nouvelle r�f�rence des zones valides
			zones_choisies_valides=copy_boolean(zones_choisies_valides_suivant);
		}
		
		//si � la fin, on a valid� toutes les zones qui avaient �t� choisies,c'est qu'� partir d'une zone, on pouvait atteindre toutes les autres donc c'est bon
		if(boolean_equal(this.zones_choisies, zones_choisies_valides)) return true;
		
		return false;
	}
	
	
	
	public boolean[][] produitBoolean(boolean[][] matrice1,boolean[][] matrice2){
		boolean[][] res=new boolean[matrice1.length][matrice1[0].length];
		boolean temp;
		for(int i=0;i<res.length;i++){
			for(int j=0;j<res.length;j++){
				temp=false;
				for(int k=0;k<res.length;k++) temp|=matrice1[i][k]&&matrice2[k][j];
				res[i][j]=temp;
			}
		}
		return res;
	}
	
	
	public int nombre_de_zones_choisies(){
		int nb=0;
		for(int i=0;i<this.zones_choisies.length;i++){
			if(this.zones_choisies[i]) nb++;
		}
		return nb;
	}
	
	public String villes_et_interconnexions_toString(int[][] villes_et_interconnexions) {
		String s="";
		for(int i=0;i<villes_et_interconnexions.length;i++){
			s=s+Arrays.toString(villes_et_interconnexions[i])+"\n";
		}
		return s;
	}
	
	public String boolean_toString(boolean[][] matrice) {
		String s="";
		for(int i=0;i<matrice.length;i++){
			s=s+Arrays.toString(matrice[i])+"\n";
		}
		return s;
	}

	
	
	
	public int[][][] getPaquets_reapprovisionnement_ressources() {
		return paquets_reapprovisionnement_ressources;
	}

	public void setPaquets_reapprovisionnement_ressources(int[][][] paquets_reapprovisionnement_ressources) {
		this.paquets_reapprovisionnement_ressources = paquets_reapprovisionnement_ressources;
	}

	public int[][] getPaquet_reapprovisionnement_du_jeu() {
		return paquet_reapprovisionnement_du_jeu;
	}

	public void setPaquet_reapprovisionnement_du_jeu(int[][] paquet_reapprovisionnement_du_jeu) {
		this.paquet_reapprovisionnement_du_jeu = paquet_reapprovisionnement_du_jeu;
	}

	public int[][] getVilles_et_interconnexions_total() {
		return villes_et_interconnexions_total;
	}

	public void setVilles_et_interconnexions_total(int[][] villes_et_interconnexions_total) {
		this.villes_et_interconnexions_total = villes_et_interconnexions_total;
	}

	public int[][] getVilles_et_interconnexions_du_jeu() {
		return villes_et_interconnexions_du_jeu;
	}

	public void setVilles_et_interconnexions_du_jeu(int[][] villes_et_interconnexions_du_jeu) {
		this.villes_et_interconnexions_du_jeu = villes_et_interconnexions_du_jeu;
	}

	public double[][] getPosition_ville() {
		return position_ville;
	}

	public void setPosition_ville(double[][] position_ville) {
		this.position_ville = position_ville;
	}

	public boolean[] getZones_choisies() {
		return zones_choisies;
	}

	public void setZones_choisies(boolean[] zones_choisies) {
		this.zones_choisies = zones_choisies;
	}

	public boolean[][] getZones_et_interconnexions_total() {
		return zones_et_interconnexions_total;
	}

	public void setZones_et_interconnexions_total(boolean[][] zones_et_interconnexions_total) {
		this.zones_et_interconnexions_total = zones_et_interconnexions_total;
	}

	public int[][] getZones_et_interconnexions_du_jeu() {
		return zones_et_interconnexions_du_jeu;
	}

	public void setZones_et_interconnexions_du_jeu(int[][] zones_et_interconnexions_du_jeu) {
		this.zones_et_interconnexions_du_jeu = zones_et_interconnexions_du_jeu;
	}

	public int getNb_joueurs() {
		return nb_joueurs;
	}

	public void setNb_joueurs(int nb_joueurs) {
		this.nb_joueurs = nb_joueurs;
	}

	public String[] getNom_villes() {
		return nom_villes;
	}

	public void setNom_villes(String[] nom_villes) {
		this.nom_villes = nom_villes;
	}
	
	

	public Color[] getCouleurs_joueurs() {
		return couleurs_joueurs;
	}

	public void setCouleurs_joueurs(Color[] couleurs_joueurs) {
		this.couleurs_joueurs = couleurs_joueurs;
	}

	public int[] getRessources_debut() {
		return ressources_debut;
	}

	public void setRessources_debut(int[] ressources_debut) {
		this.ressources_debut = ressources_debut;
	}
	

	@Override
	public String toString() {
		return "Initialisation [zones_choisies=" + Arrays.toString(zones_choisies) + "]";
	}
	
	public boolean boolean_equal(boolean[] t1,boolean[] t2){
		for(int i=0;i<t1.length;i++){
			if(t1[i]!=t2[i]) return false;
		}
		return true;
	}
	
	public boolean[] copy_boolean(boolean[] src){
		boolean[] dest=new boolean[src.length];
		for(int i=0;i<src.length;i++) dest[i]=src[i];
		return dest;
	}

	public static void main(String[] args) {
		Initialisation jeu=new Initialisation();
		//System.out.println(jeu.villes_et_interconnexions_toString(jeu.villes_et_interconnexions_total));
		jeu.setZones_choisies(new boolean[]{true,false,false,false,false,false,true});
		//jeu.mettre_a_jour_villes_et_zones(test);
		//System.out.println(jeu.villes_et_interconnexions_toString(jeu.villes_et_interconnexions_du_jeu));
		//System.out.println("m�thode 1: "+jeu.zones_choisies_valables());
		System.out.println(jeu.toString());
		System.out.println("m�thode 2: "+jeu.zones_valables());
		
		/**/
		jeu.setZones_choisies(new boolean[]{false,true,false,true,false,true,false});
		//System.out.println("m�thode 1: "+jeu.zones_choisies_valables());
		System.out.println("m�thode 2: "+jeu.zones_valables());
		//*/
		
	}
}
