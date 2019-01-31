package library

class FiltrageURL {

    def listeUrl(l: List[(String, String)]): List[String] = {
        l match {
            case Nil        => Nil
            case x :: suite => if (x._1 == "href") x._2 :: listeUrl(suite) else listeUrl(suite)
        }
    }
    def contient9Chiffres(s: String): Boolean = {
        var count: Int = 0;
        for (n <- s) {
            if (n <= '9' && n >= '0') {
                count += 1

            }
        }
        if (count >= 9) {
            true
        } else {
            false
        }
    }

    def filtrage(l: List[String]): List[String] = {
        l match {
            case Nil    => Nil
            case x :: y => if (x.contains("https://www.vivastreet.com/") && contient9Chiffres(x)) { x :: filtrage(y) } else { filtrage(y) }

        }

    }
    def filtreAnnonceBis(h: List[Html]): List[String] = {
        h match {
            case Nil    => Nil
            case x :: r => filtreAnnonce(x) ++ filtreAnnonceBis(r)
        }

    }
    def filtreAnnonce(h: Html): List[String] = {
        h match {
            case Text(_)      => Nil
            case Tag(_, x, y) => filtrage(listeUrl(x)) ++ filtreAnnonceBis(y)
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