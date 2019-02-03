package library

object Html2StringClass extends library.Html2String {

    /**
     * @param h un élément Html
     * @return le document Html sous forme de String représentant un Html valide avec balises
     */
    def process(h: Html): String = {
        /* variable utilise par les attributs */
        var a = ""
        /* variable utilise par les fils */
        var b = ""
        h match {
            case Tag(name, attributes, children) =>
                /* forme correctement les attributs */
                a = createAttr(attributes)
                for (fils <- children) { 
                    /* appel récursif avec le reste */
                    b += process(fils)
                }
                /* créé le document Html sous forme de String */
                "<" + name + a + ">" + "\n" + b + "</" + name + ">" + "\n"
            case Text(content) => content
        }
    }

    /**
     * @param attr une liste de (String,String) correspondant aux attributs d'une balise
     * @return une String composée des attributs bien formés
     */
    private def createAttr(attr: List[(String, String)]): String = {
        var res = ""
        for (a <- attr) {
            res += " " + a._1 + "=" + "\"" + a._2 + "\""
        }
        res
    }

}