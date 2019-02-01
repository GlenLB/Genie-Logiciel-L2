package library

object ProductRes extends library.ProductionResultat {

    def resultat2html(l: List[(String, String)]): Html = {
        var listeURL: List[Html] = List()
        for (ligne <- l) {
            listeURL = listeURL :+ Tag("li", List(), List(Tag("a", List(("href", ligne._2)), List(Text(ligne._1)))))
        }
        val html = Tag("html", List(),
            List(
                Tag("head", List(),
                    List(
                        Tag("meta", List(("content", "text/html"), ("charset", "utf8")), List()),
                        Tag("title", List(), List(Text("Résultats"))))),
                Tag("body", List(), List(
                   Tag("ol", List(), listeURL)))))
        html
    }
}