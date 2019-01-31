package library

/**
 *  Implémente l'interface library.AnalysePage /
 */
class AnalysePage2 extends library.AnalysePage {
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
        val s: Html = UrlProcessor.fetch(url)
        var filtrageUrl = new FiltrageURL()
        val lidt: List[String] = filtrageUrl.filtreAnnonce(s)
        var list: List[Html] = List()
        for (ruls <- lidt) {
            list = UrlProcessor.fetch(ruls) :: list
        }
        var coup: List[(String, Html)] = List()
        var i: Int = 0
        for (htm <- list) {
            if (FiltreHtml.filtreHtml(htm, exp)) {
                lidt.lift(i) match {
                    case Some(c) => coup = (c, htm) :: coup
                    case None    => println("pas d'url")
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
        var res: String = ""
        h match {
            case Text(n)            => ""
            case Tag("title", b, v) => titreRec(v)
            case Tag(b, g, j) => for (htm <- j) {
                res += titreHtml(htm)
            }
        }
        res
    }

    /**
     * Méthode utile à la méthode titreHtml()
     * @return le premier texte rencontré dans une liste de HTML
     */
    private def titreRec(n: List[Html]): String = {
        var res: String = ""
        for (f <- n) {
            f match {
                case Text(w)      => res += w
                case Tag(_, _, _) => println("tag")
            }
        }
        res
    }

}
