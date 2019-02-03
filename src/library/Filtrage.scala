package library

class Filtrage extends FiltrageURLs {
    /**
     * @param h un document HTML
     * @return une liste de String qui contient les liens hypertextes de la page h
     */
    def filtreAnnonce(h: Html): List[String] = {
        /* création d'une liste de String pour stocker les URL */
        var listUrl: List[String] = List()
        h match {
            case Tag(x, y, List(Text(t), reste)) =>
                /* x est une URL */
                if (x == "a") {
                    listUrl = listUrl :+ "<a href=" + y(0)._1 + ">" + t + "</a>"
                }
                filtreAnnonce(reste)
            case Tag(x, y, reste) =>
                /* appel récursif pour savoir si reste peut contenir des URL */
                for (x <- reste) {
                    filtreAnnonce(x)
                }
            case Text(content) => Nil
        }
        /* retourne la liste de liens */
        listUrl
    }
}