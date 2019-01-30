package library

import java.io.FileWriter

object Main extends App {
    //
    //    var listeURL = List(("a", "http://url.fr"), ("a2", "http://url.fr"))
    //    var obj = new Html2StringClass
    //    var product = new library.ProductRes
    //    var HTML = product.resultat2html(listeURL)
    //    println(obj.process(HTML))
    //

//    var listeURL = List(("a", "http://url.fr"), ("a2", "http://url.fr"))
//    var obj = new Html2StringClass
//    var product = new library.ProductRes
//    var HTML = product.resultat2html(listeURL)
//    var resultat = obj.process(HTML)
//
//    val fichier = new FileWriter("htmlListeUrl.txt")
//    try {
//        fichier.write(resultat)
//    } finally fichier.close
//    
//    println("ok")
    
    var filtre = new Filtrage()
    println(filtre.filtreAnnonce(ExempleHtml.exemple))
}