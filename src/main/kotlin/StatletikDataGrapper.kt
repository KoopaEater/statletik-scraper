package org.example

import okhttp3.internal.toImmutableList
import org.jsoup.nodes.Document

class StatletikDataGrapper(val document: Document) {
    fun getBestResults(): List<Event>? {
        val tbody = document.select(".container > .row > div > .row > div > .card > .card-body > .table-responsive > table > tbody").first();
        if (tbody === null) {
            return null;
        }
        val trows = tbody.select("tr");
        val events: MutableList<Event> = mutableListOf();
        var nameOfEvent: String = "";
        val results: MutableList<Result> = mutableListOf();
        for (tr in trows) {
            val bs = tr.select("b");
            if (bs.size > 0) {
                events.add(Event(nameOfEvent, results.toImmutableList()));
                results.clear();
                continue;
            }

            if (tr.childrenSize() == 2) continue;
            if (tr.child(0).attr("colspan") == "5") continue;

            val hrs = tr.select("hr");
            if (hrs.size > 0) {
                continue;
            }

            val classOfElement: String = tr.attr("class");
            if (classOfElement == "event") {
                nameOfEvent = tr.child(0).text();
                continue;
            }

            val rawResult = tr.child(0).text();
            val result = rawResult.replace("i","").trim();
            val indoors = rawResult.contains("i");
            val location = tr.child(4).text();
            val date = tr.child(5).text();
            results.add(Result(result, indoors, location, date));
        }
        return events;
    }
}