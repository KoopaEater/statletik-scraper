package org.example

class Result(val result: String, val indoors: Boolean, val location: String, val date: String) {
    override fun toString(): String {
        return "${result.padStart(8)} ${if (indoors) "i" else " "} ${location.padEnd(20)} ${date.padStart(8)}"
    }
}