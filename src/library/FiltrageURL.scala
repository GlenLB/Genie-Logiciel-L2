package library

class FiltrageURL {

    /**
     * @param l une liste de [(String, String)]
     * @return la liste des URL trouvées
     */
    def listeUrl(l: List[(String, String)]): List[String] = {
        l match {
            case Nil        => Nil
            case x :: suite => if (x._1 == "href") x._2 :: listeUrl(suite) else listeUrl(suite)
        }
    }

    /**
     * @param s un String
     * @return true ssi s contient au moins 9 chiffres
     */
    def contient9Chiffres(s: String): Boolean = {
        /* compteur */
        var count: Int = 0;
        for (n <- s) {
            /* pour chaque chiffre trouvé dans s, le compteur est incrémenté */
            if (n <= '9' && n >= '0') {
                count += 1
            }
        }
        /* return true ssi s contient au moins 9 chiffres */
        count >= 9
    }

    /**
     * @param l une liste de String
     * @return une liste de String correspondant à tous les liens valides d'une recherche
     * c'est a dire un lien du site web vivastreet contenant au moins 9 chiffres
     */
    def filtrage(l: List[String]): List[String] = {
        l match {
            case Nil    => Nil
            case x :: y => if (x.contains("https://www.vivastreet.com/") && contient9Chiffres(x)) { x :: filtrage(y) } else { filtrage(y) }

        }

    }

    /**
     * @param h un Html
     * @return une liste de String filtrée pour vérifier que le tag est un lien
     */
    def filtreAnnonce(h: Html): List[String] = {
        h match {
            case Text(_)      => Nil
            case Tag(_, x, y) => filtrage(listeUrl(x)) ++ filtreAnnonceBis(y)
        }
    }

    /**
     * Fonction annexe pour utile à filtreAnnonce
     * @param h une liste d'Html
     * @return la liste en envoyant les Html un par un à filtreAnnonce
     */
    def filtreAnnonceBis(h: List[Html]): List[String] = {
        h match {
            case Nil    => Nil
            case x :: r => filtreAnnonce(x) ++ filtreAnnonceBis(r)
        }

    }

    //Test --------------------------------------------------------------------------------------------------------------------
    // val lll :List[String] = filtreAnnonce(UrlProcessor.fetch("https://www.vivastreet.com/voiture-occasion/fr"))
    // println(lll)
    //val listee : List[(String, String)] = List(("href","https://www.vivastreet.com/voiture-occasion/nice-ouest-06200/land-rover-freelander-ii-td4-se-mark-iv/195140192"), ("htef","https://www.vivastreet.com/voiture-occasion/nice-ouest-06200/land-rover-freelander-ii-td4-se-mark-iv/195140192"),("href","https://www.vivastreet.com/"),("href","https://www.vivastreet.com/voiture-occasion/mamers-72600/leon-style-tdi-105-avec-129-706-km/187116327"))
    //val ll : List[String] = filtrage(listeUrl(listee))
    //println(ll)
    // println(UrlProcessor.fetch("https://www.vivastreet.com/voiture-occasion/mamers-72600/leon-style-tdi-105-avec-129-706-km/187116327"))
    //val l2 : Html = UrlProcessor.fetch("https://www.vivastreet.com/voiture-occasion/fr")
    //  println(l2)
    //println(filtreAnnonce(UrlProcessor.fetch("https://www.vivastreet.com/voiture-occasion/fr")))
}