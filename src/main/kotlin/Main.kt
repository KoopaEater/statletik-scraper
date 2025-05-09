package org.example

import org.jsoup.nodes.Document

fun main() {
    val scraper: Scraper = Scraper("https://www.statletik.eu/db/atden.php?Sex=1&ID=310533");
    val document: Document = scraper.getContent();
    val dataGrapper: StatletikDataGrapper = StatletikDataGrapper(document);
    val bestResults = dataGrapper.getBestResults() ?: return;
    for (event in bestResults) {
        println("\n" + event);
    }
}