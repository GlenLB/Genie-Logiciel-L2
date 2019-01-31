package library

object Html2StringClass extends library.Html2String {

    def process(h: Html): String = {
        var a = "" // variable utilise par les attributs
        var b = "" // variable utilise par les fils
        h match {
            case Tag(name, attributes, children) =>
                a = createAttr(attributes)
                for (fils <- children) { // parcourir children
                    b += process(fils) // appel de process avec le fils
                }
                "<" + name + a + ">" + "\n" + b + "</" + name + ">" + "\n" //cree le document html
            case Text(content) => content
        }
    }


    private def createAttr(attr: List[(String, String)]): String = {
        var res = ""
        for (a <- attr) {
            res += " " + a._1 + "=" + "\"" + a._2 + "\""
        }
        res
    }
    
}