package com.devsatish.vocabo.Repository

class HardRepository {
    private val EasywordList = listOf(

        "अहंकारी" to "Megalomaniac", "संक्षिप्तीकरण" to "Abbreviation","अभिव्यक्ति" to "Munifestation","डगमगाना" to "Vacillate",
        "मनोरंजन करना" to "Amuse", "स्पष्ट" to "Apparent", "सिंहपर्णी" to "Dandelion", "तीव्र गति" to "Dart","उपयाजक" to "Deacon",
        "डेस्कटॉप" to "Desktop","डिपो" to "Depot","पवित्रता का हनन" to "Desecration",

// Changed - 31-10-2025


    )

    fun getList(): List<Pair<String, String>> = EasywordList
}