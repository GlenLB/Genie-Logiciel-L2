package library

 
/** Les documents HTML, vus comme des objets de type Html sont structurés de la façon suivante:
 *  Un noeud HTML de la forme: <NomTag att1="val1" att2="val2"> n1 n2 ... </NomTag>, sera représenté par un objet
 *  Tag("NomTag",List(("att1","val1"),("att2","val2")),List(n1,n2,...)) 
 *
 *  Un élément de texte simple sera représenté par un objet Text(s) où s est une chaîne de caractères        
 */

sealed trait Html
case class Tag(name:String,attributes:List[(String,String)],children:List[Html]) extends Html
case class Text(content:String) extends Html


/** Un exemple de document html qui correspond au code HTML suivant:
 *  <html>
 *    <head>
 *      <meta content="text/html; charset=ISO-8859-1"></meta>
 *      <title> MyPage </title>
 *    </head>
 *    <body>
 *      &nbsp
 *      <center>
 *        <a href="http://www.irisa.fr"> Lien <img> </img> </a>
 *      </center>
 *    </body>
 *  </html>
 */

object ExempleHtml{
  val exemple= Tag("html",List(),
                   List(Tag("head",List(),
                            List(Tag("meta",List(("content","text/html"),("charset","iso-8859-1")),List()),
                            		Tag("title",List(),List(Text("MyPage"))))),
                        Tag("body",List(),List(
                            Text("&nbsp"),
                            Tag("center",List(),List(
                            		Tag("a", List(("href","http://www.irisa.fr")),	
                            				List(Text("Lien"),Tag("img",List(),List())))))))))
}
