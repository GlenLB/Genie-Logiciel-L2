package library

object ProductRes extends library.ProductionResultat {

    /**
     * @param l une liste de (String, String) contenant des titres et des liens (titre, lien)
     * @return un élément Html réprésentant une page qui contient la liste des liens présents dans l
     */
    def resultat2html(l: List[(String, String)]): Html = {
        /* construit préalablement la liste des liens */
        var listeURL: List[Html] = List()
        for (ligne <- l) {
            listeURL = listeURL :+ Tag("li", List(), List(Tag("a", List(("href", ligne._2)), List(Text(ligne._1)))))
        }
        /* construit le Html à retourner, on y insère la liste de liens préalablement créée */
        val html = Tag("html", List(),
            List(
                Tag("head", List(),
                    List(
                        Tag("meta", List(("content", "text/html"), ("charset", "utf8")), List()),
                        Tag("title", List(), List(Text("Résultats"))))),
                Tag("body", List(), List(
                    Tag("ol", List(), listeURL)))))
        /* retourne le Html complet avec la liste de liens */
        html
    }
}