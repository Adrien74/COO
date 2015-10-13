import java.util.*;

public class Groupe {
	private String nom;
	private String description;

	public ArrayList<Contenu> contenus;
	
	public Repertoire currentRepertoire;

	
	public Groupe(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
		this.contenus = new ArrayList<Contenu> ();
	}
	
	public Groupe(String nom, String description, ArrayList<Contenu> contenus) {
		super();
		this.nom = nom;
		this.description = description;
		this.contenus = contenus;
		this.currentRepertoire = new Repertoire("Root", "C://", this.contenus);
	}

	@Override
	public String toString() {
		return "Nom : " + nom + ", Description : " + description ;
	}

	public void ajouterContenu(String nomContenu, String cheminContenu, int choix) {
		Contenu contenu = null;
		if (choix == 1) {
			contenu = new Document(nomContenu, cheminContenu);
		} else if (choix == 2) {
			contenu = new Service (nomContenu, cheminContenu);
		} else if (choix == 3) {
			contenu = new Repertoire(nomContenu, cheminContenu);
		}
		
		if (contenu != null)
			this.currentRepertoire.contenusDuRepertoire.add(contenu);
	}

	public ArrayList<Contenu> getContenus() {
		return this.contenus;
	}

	public Repertoire accederRepertoire(int choix) {
		Repertoire rep = (Repertoire) this.currentRepertoire.contenusDuRepertoire.get(choix);
		this.currentRepertoire = rep; 
		return this.currentRepertoire;
	}
}