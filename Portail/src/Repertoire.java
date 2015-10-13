import java.util.*;

public class Repertoire extends Contenu {
	public ArrayList<Contenu> contenusDuRepertoire;

	public Repertoire(String nom, String chemin, ArrayList<Contenu> contenusDuRepertoire) {
		super(nom, chemin);
		this.contenusDuRepertoire = contenusDuRepertoire;
	}
	
	public Repertoire(String nom, String chemin) {
		super(nom, chemin);
		this.contenusDuRepertoire = new ArrayList<Contenu>();
	}

	public ArrayList<Contenu> getContenus() {
		return contenusDuRepertoire;
	}

	@Override
	public String toString() {
		return super.toString() + "/";
	}
	
	
}