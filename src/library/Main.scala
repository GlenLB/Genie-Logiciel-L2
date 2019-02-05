package library

import java.io.FileWriter
import java.io._
import sys.process._

/**
 * Lire une requête de recherche au clavier
 * Construire l'URL à passer à Vivastreet
 * Obtenir la liste des tuples (titre, URL) qui satisfont la requete
 * Produire le document HTML qui liste les liens obtenus
 * Convertir le HTML en chaine de caractères
 * Ecrire cette chaine de caractères dans un fichier .html qui est la réponse attendue
 */
object Main extends App {
  /**
   * 2 requêtes :
   * maison
   * rennes or (pierre and saint)
   */

  /* invite l'utilisateur à renseigner une requête et lit une requête de recherche au clavier */
  val requeteUtilisateur: Expression = ExpressionParser.readExp
  println(requeteUtilisateur)
  /* construit l'URL à passer à Vivastreet */
  val motsClesAInsererURL = FiltreHtml.formerRequeteMotsCles(requeteUtilisateur)
  var URL = "https://search.vivastreet.com/annonces/fr?lb=new&search=1&start_field=1&keywords=" + motsClesAInsererURL + "&cat_1=&geosearch_text=&searchGeoId=0"
  println(URL)
  /* Obtenir la liste des tuples (titre, URL) qui satisfont la requete */
  var listeTitreURL: List[(String, String)] = AnalysePage2.resultats(URL, requeteUtilisateur)
  var tabPage = "/t+2?"
  var i = 2
  URL = "https://search.vivastreet.com/annonces/fr" + tabPage + "lb=new&search=1&start_field=1&keywords=" + motsClesAInsererURL + "&cat_1=&geosearch_text=&searchGeoId=0"
  while (i <= 5) {
    println(i)
    listeTitreURL = listeTitreURL ++ AnalysePage2.resultats(URL, requeteUtilisateur)
    println(URL)
    i += 1
    tabPage = "/t+" + i + "?"
    URL = "https://search.vivastreet.com/annonces/fr" + tabPage + "lb=new&search=1&start_field=1&keywords=" + motsClesAInsererURL + "&cat_1=&geosearch_text=&searchGeoId=0"
  }
  println(listeTitreURL)
  /* Produire le document HTML qui liste les liens obtenus */
  val HTML: Html = ProductRes.resultat2html(listeTitreURL)
  println(HTML)
  /* Convertir le HTML en chaine de caractères */
  val chaineHTML: String = Html2StringClass.process(HTML)
  println(chaineHTML)
  /* Ecrire cette chaine de caractères dans un fichier .html qui est la réponse attendue */
  val writer = new PrintWriter(new File("listeURL.html"))
  writer.write(chaineHTML)
  writer.close
  println("Fichier créé")
  val retour = "firefox listeURL.html"
  retour !
}