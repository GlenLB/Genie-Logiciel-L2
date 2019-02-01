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

    /**
     * @param e l'expression entrée par l'utilisateur
     * @return la liste des mots clés tapés par l'utilisateur
     */
    def motsCles(e: Expression): String = {
        var res = ""
        e match {
            case Or(mot1, mot2)  =>
                res += motsCles(mot1); res += motsCles(mot2)
            case And(mot1, mot2) =>
                res += motsCles(mot1); res += motsCles(mot2)
            case Word(mot)       => res += mot + ";"
        }
        res
    }

    /**
     * @param requrequeteUtilisateur l'Expression entrée par l'utilisateur
     * @return le morceau d'URL à insérer dans le champ keywords de l'URL
     */
    def formerRequeteMotsCles(requeteUtilisateur: Expression): String = {
        val mots: String = motsCles(requeteUtilisateur)
        var listeMots: Array[String] = mots.split(";")
        var motsClesAInsererURL = ""
        for (mot <- listeMots) {
            motsClesAInsererURL += mot + "+"
        }
        // enleve le dernier "+" en trop
        motsClesAInsererURL = motsClesAInsererURL.dropRight(1)
        return motsClesAInsererURL
    }

}