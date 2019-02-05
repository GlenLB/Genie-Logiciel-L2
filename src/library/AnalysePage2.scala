package library

/**
 *  Implémente l'interface library.AnalysePage /
 */
object AnalysePage2 extends library.AnalysePage {
    /**
     * A partir d'une URL de requête sur le site de référence et d'une expression exp,
     * retourne des pages issues de la requête et satisfaisant l'expression.
     *
     * @param url l'URL de la requête sur le site de référence
     * @param exp l'expression à vérifier sur les pages trouvées
     * @return la liste des couples (titre,ref) où ref est l'URL d'une page
     * satisfaisant l'expression et titre est son titre.
     */
    def resultats(url: String, exp: Expression): List[(String, String)] = {
        /* récupère le document HTML */
        val s: Html = UrlProcessor.fetch(url)
        var filtrageUrl = new FiltrageURL()
        /* on récupère les URL et on ne garde que celles qui nous intéressent */
        val lidt: List[String] = filtrageUrl.filtreAnnonce(s) 
        var list: List[Html] = List() 
        for (ruls <- lidt) {
          list = UrlProcessor.fetch(ruls) :: list 
          }
        var coup: List[(String, Html)] = List()
        var i: Int = 0
        for (htm <- lidt) { 
          if (FiltreHtml.filtreHtml(UrlProcessor.fetch(htm), exp)) {
            lidt.lift(i) match {
              case Some(c) => coup = (c, UrlProcessor.fetch(htm)) :: coup
              case None => println("pas d'url") 
              }
            }
          i += 1 
          }
        var cople: List[(String, String)] = List()
        for (coupl <- coup) {
            cople = (titreHtml(coupl._2), coupl._1) :: cople
        }
        cople
    }

    /**
     * @return le titre d'un document HTML
     */
    private def titreHtml(h: Html): String = {
        var res = ""
        h match {
            case Tag(title, _, List(Text(titre))) => if (title == "title") return titre
            case Tag(_, _, reste)                 => for (balise <- reste) { res += titreHtml(balise) };
            case Text(_)                          => res += ""
        }
        res
    }

}