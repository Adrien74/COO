import java.util.*;

public class Personne {
	private String pseudo;
	private String motDePasse;
	private String nom;
	private String prenom;

	public ArrayList<Groupe> groupesParPersonne;
	public ArrayList<Groupe> groupesGeres;

	public Personne(String pseudo, String motDePasse, String nom, String prenom, ArrayList<Groupe> listeGroupesPierre) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.nom = nom;
		this.prenom = prenom;
		this.groupesGeres = new ArrayList<Groupe>();
		this.groupesParPersonne = listeGroupesPierre;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Groupe creerGroupe(String nom, String description) {
		Groupe newGrp = new Groupe(nom, description);
		this.groupesGeres.add(newGrp);
		this.groupesParPersonne.add(newGrp);
		return newGrp;
	}

	public ArrayList<Groupe> getGroupesParPersonne() {
		return this.groupesParPersonne;
	}
}