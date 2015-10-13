
public abstract class Contenu {
	private String nom;
	private String chemin;

	public Contenu(String nom, String chemin) {
		super();
		this.nom = nom;
		this.setChemin(chemin);
	}

	@Override
	public String toString() {
		return nom;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	
	

}