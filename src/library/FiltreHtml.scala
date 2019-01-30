package library

object FiltreHtml extends library.FiltrageHtml {
    /**
     * A partir d'un document Html h et d'une requête e, dit si le document
     * satisfait l'expression e
     *
     * @param h le document Html
     * @param e l'expression
     * @return true si le document satisfait l'expression e
     */
    def filtreHtml(h: Html, e: Expression): Boolean = {
        filtre1(htmltostring(h), e)
    }

    private def filtre1(s: String, e: Expression): Boolean = {
        e match {
            case Or(k, l)  => filtre1(s, k) || filtre1(s, l)
            case And(k, l) => filtre1(s, k) && filtre1(s, l)
            case Word(d)   => s.contains(d)
        }
    }

    private def htmltostring(h: Html): String = {
        var res: String = ""
        h match {
            case Text(n) => n
            case Tag(n, j, k) =>
                for (e <- k) {
                    res += htmltostring(e)
                }
                res
        }
    }

}