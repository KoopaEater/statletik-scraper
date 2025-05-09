package org.example

class Event(val title: String, val results: List<Result>) {
    override fun toString(): String {
        var str = title;
        for (result in results) {
            str += "\n\t" + result;
        }
        return str;
    }
}