package library

class Filtrage extends FiltrageURLs {
    def filtreAnnonce(h: Html): List[String] = {
        var listUrl: List[String] = List()
        h match {
            case Tag(x, y, List(Text(t), reste)) =>
                if (x == "a") {
                    listUrl = listUrl :+ "<a href=" + y(0)._1 + ">" + t + "</a>"
                }
                filtreAnnonce(reste)
            case Tag(x, y, reste) =>
                for(x <- reste) {
                    filtreAnnonce(x)
                }
            case Text(content) => Nil
        }
        listUrl
    }
}