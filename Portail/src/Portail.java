import java.util.*;

public class Portail {
	public ArrayList<Personne> utilisateurs;
	public ArrayList<Groupe> groupes;
	public Personne currentConnected;
	public Groupe currentGroupe;

	public static void main(String[] args) {
		Portail portail = new Portail();

		Document TxtFilm = new Document("DocFilmActions.txt", "C://Documents");
		Document JamesBondFilm = new Document("JamesBond.mkv", "C://Videos");
		ArrayList<Contenu> listeContenusRepertoire = new ArrayList<>();
		listeContenusRepertoire.add(JamesBondFilm);
		Repertoire repertoireFilms = new Repertoire("Repertoire Films", "C://Documents", listeContenusRepertoire);
		ArrayList<Contenu> listeContenusGroupe = new ArrayList<>();
		listeContenusGroupe.add(repertoireFilms);
		listeContenusGroupe.add(TxtFilm);

		Groupe filmsAction = new Groupe("Films d'action", "Tous les films d'action partagés.", listeContenusGroupe);
		Groupe filmsAmour = new Groupe("Films d'amour", "Tous les films d'amour partagés.");
		Groupe filmsComique = new Groupe("Films comiques", "Tous les films comiques partagés.");
		Groupe filmsHorreur = new Groupe("Films d'horreur", "Tous les films d'horreur partagés.");
		Groupe filmsFantastique = new Groupe("Films fantastiques", "Tous les films fantastiques partagés.");

		ArrayList<Groupe> listeGroupesPierre = new ArrayList<Groupe>();
		listeGroupesPierre.add(filmsAction);
		listeGroupesPierre.add(filmsAmour);
		listeGroupesPierre.add(filmsComique);
		Personne pierre = new Personne("PierreD", "mdppierre", "Duplot", "Pierre", listeGroupesPierre);

		ArrayList<Groupe> listeGroupesPaul = new ArrayList<Groupe>();
		listeGroupesPaul.add(filmsAction);
		listeGroupesPaul.add(filmsHorreur);
		listeGroupesPaul.add(filmsFantastique);
		Personne paul = new Personne("PaulL", "mdppaul", "Paul", "Longroi", listeGroupesPaul);

		portail.utilisateurs.add(pierre);
		portail.utilisateurs.add(paul);

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		String pseudo;
		String mdp;

		System.out.println("Bonjour et bienvenue sur NouilleBox, veulliez vous authentifier : ");
		do {
			System.out.println("Saisir pseudo : ");
			pseudo = sc.nextLine();
			System.out.println("Saisir mot de passe : ");
			mdp = sc.nextLine();
		} while (portail.seConnecter(pseudo, mdp) == false);

		do {
			
			int choix = -1;
			System.out.println(
					"Que voulez vous faire ? \n 1.Consulter mes groupes \n 2.Créer un groupe \n 3.Se déconnecter");
			choix = sc.nextInt();

			switch (choix) {
			case 1:
				System.out.println("Voici la liste des groupes auxquels vous participez : ");
				ArrayList<Groupe> listeGroupe = portail.consulterMesGroupes();
				int j = 0;
				for (Groupe groupe : listeGroupe) {
					System.out.println(j + ". " + groupe.toString());
					j++;
				}

				System.out.println("A quel groupe voulez-vous acceder ?");
				choix = sc.nextInt();
				portail.currentGroupe = portail.getGroupe(choix);
				System.out.println(portail.currentGroupe.toString());
				ArrayList<Contenu> listeContenu = portail.currentGroupe.getContenus();

				System.out.println("Voici le contenu du groupe : ");
				j = 0;
				for (Contenu contenu : listeContenu) {
					System.out.println(j + ". " + contenu.toString());
					j++;
				}
				portail.currentGroupe.currentRepertoire = new Repertoire("Root", "C://", portail.currentGroupe.contenus);
				boolean stopAcces = false;
				do {
					
					System.out.println("Que voulez-vous faire ? \n 1.Accéder à un répertoire \n 2.Ajouter contenu \n 3.Retourner au menu principal");
					System.out.println(portail.currentGroupe.currentRepertoire.toString());
					choix = sc.nextInt();
					switch (choix) {
					case 1:
						System.out.println("Quel répertoire ?");
						choix = sc.nextInt();
						System.out.println(portail.accederRepertoire(choix).toString());
						break;
					case 2:
						System.out.println(
								"Quel contenu voulez-vous créer ? \n 1. Un document \n 2. Un service \n 3. Un répertoire");
						choix = sc.nextInt();

						System.out.println("Le nom de votre contenu : ");
						String nom = sc.next();

						System.out.println("Le chemin de votre contenu : ");
						String chemin = sc.next();

						@SuppressWarnings("unused")
						Contenu contenu;
						if (choix == 1) {
							contenu = new Document(nom, chemin);
						} else if (choix == 2) {
							contenu = new Service(nom, chemin);
						} else if (choix == 3) {
							contenu = new Repertoire(nom, chemin);
						}

						portail.ajouterContenu(nom, chemin, choix);

						stopAcces = true;
						break;
					case 3:
						System.out.println("Retour au menu principal");
						stopAcces = true;
						break;
					default:
						System.out.println("Saisie incorrecte");
					}
				} while (stopAcces == false);

				break;

			case 2:
				System.out.println("Le nom de votre groupe : ");
				String nom = sc.next();
					
				System.out.println("La description de votre groupe : ");
				String description = sc.next();

				portail.creerGroupe(nom, description);
				break;

			case 3:
				System.out.println("Au revoir " + portail.currentConnected.getPrenom() + " "
						+ portail.currentConnected.getNom() + " !");
				break;
			default:
				System.out.println("Saisie incorrecte");
			}
		} while (true);
	}

	public boolean seConnecter(String pseudo, String motDePasse) {
		for (int i = 0; i < this.utilisateurs.size(); i++) {
			if (this.utilisateurs.get(i).getPseudo().equals(pseudo)
					&& this.utilisateurs.get(i).getMotDePasse().equals(motDePasse)) {
				this.currentConnected = this.utilisateurs.get(i);
				System.out.println("Vous etes connecte " + pseudo + ".");
				return true;
			}
		}
		System.out.println("Pseudo ou mot de passe incorrect.");
		return false;

	}

	public void ajouterContenu(String nomContenu, String cheminContenu, int choix) {
		this.currentGroupe.ajouterContenu(nomContenu, cheminContenu, choix);
	}

	public void creerGroupe(String nom, String description) {
		currentConnected.creerGroupe(nom, description);
		this.groupes.add(new Groupe(nom, description));
	}

	public ArrayList<Groupe> consulterMesGroupes() {
		return currentConnected.getGroupesParPersonne();
	}

	public Groupe getGroupe(int choix) {
		return currentConnected.getGroupesParPersonne().get(choix);
	}

	public Repertoire accederRepertoire(int choix) {
		return this.currentGroupe.accederRepertoire(choix);
	}

	public Portail() {
		super();
		this.utilisateurs = new ArrayList<Personne>();
		this.groupes = new ArrayList<Groupe>();
	}

}