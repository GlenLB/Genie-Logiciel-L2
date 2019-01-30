package library

class AnalysePage2 extends library.AnalysePage {
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

    //rend titre d un html
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

    //il faut qu'un texte ds la balise Title
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