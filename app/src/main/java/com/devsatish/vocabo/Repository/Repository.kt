package com.devsatish.vocabo.Repository

class Repository() {
    private val EasywordList = listOf(

        "शेर" to "Lion", "फूल" to "Flower", "सब्जी" to "Vegetable", "लड़का" to "Boy", "बेटा" to "Son",
        "अंडा" to "Egg", "सूरज" to "Sun", "सेब" to "Apple", "आदमी" to "Man", "लड़की" to "Girl",
        "बेटी" to "Daughter", "घर" to "House", "पेड़" to "Tree", "किताब" to "Book", "औरत" to "Woman",
        "भाई" to "Brother", "चाचा" to "Uncle", "बच्चा" to "Child", "बहन" to "Sister", "चाची" to "Aunt",
        "डॉक्टर" to "Doctor", "पिता" to "Father", "मित्र" to "Friend", "शिक्षक" to "Teacher", "माता" to "Mother",
        "किसान" to "Farmer", "जानवर" to "Animal", "फिर से" to "Again", "गाँव" to "Village", "राज्य" to "State",
        "शहर" to "City", "देश" to "Country", "गाय" to "Cow", "चिंटी" to "Ant", "बिल्ली" to "Cat",
        "बाघ" to "Tiger", "साँप" to "Snake", "कुत्ता" to "Dog", "चावल" to "Rice", "आम" to "Mango",
        "दूध" to "Milk", "लाल" to "Red", "हरा" to "Green", "सफ़ेद" to "White", "काला" to "Black",
        "पीला" to "Yellow", "तेल" to "Oil", "गुलाब" to "Rose", "आकाश" to "Sky", "दिन" to "Day",
        "रात" to "Night", "नदी" to "River", "पानी" to "Water", "सोना" to "Gold", "रोटी" to "Bread",
        "स्याही" to "Ink", "चाय" to "Tea", "चीनी" to "Sugar", "कागज" to "Paper", "अच्छा" to "Good",
        "ख़राब" to "Bad", "बड़ा" to "Big", "छोटा" to "Small", "लंबा" to "Tall", "सुंदर" to "Beautiful",
        "कुरूप" to "Ugly", "प्रसन्न" to "Happy", "उदास" to "Sad", "धनी" to "Rich", "गरीब" to "Poor",
        "ठंडा" to "Cold", "गरम" to "Hot", "मोटा" to "Fat", "पतला" to "Thin", "ईमानदार" to "Honest",
        "बेईमान" to "Dishonest", "युवा" to "Young", "बूढ़ा" to "Old", "नया" to "New", "पुराना" to "Old",
        "दयालु" to "Kind", "क्रूर" to "Cruel", "मीठा" to "Sweet", "तीखा" to "Bitter", "मुलायम" to "Soft",
        "कड़ा" to "Hard", "मजबूत" to "Strong", "कमजोर" to "Weak", "गंदा" to "Dirty", "साफ" to "Clean",
        "उजला" to "Bright", "नीला" to "Blue", "भूरा" to "Brown", "बहुत" to "Very",



        "गेंद" to "Ball", "केला" to "Banana", "घंटी" to "Bell", "जन्मदिन" to "Birthday", "काला" to "Black",
        "नाव" to "Boat", "शरीर" to "Body", "किताब" to "Book", "बोतल" to "Bottle", "डिब्बा" to "Box",
        "लड़का" to "Boy", "लाना" to "Bring", "भाई" to "Brother", "भूरा" to "Brown", "जलाना" to "Burn",
        "मक्खन" to "Butter", "बटन" to "Button", "खरीदना" to "Buy", "बाल्टी" to "Bucket",


        "कार" to "Car", "कुर्सी" to "Chair", "बंद" to "Close", "साफ करना" to "Clean", "घड़ी" to "Clock",
        "खाना बनाना" to "Cook", "रोना" to "Cry", "रंग" to "Color", "गोला" to "Circle", "नकल करना" to "Copy",
        "आना" to "Come", "पकड़ना" to "Catch", "बदलना" to "Change", "काटना" to "Cut", "गिनना" to "Count",
        "देश" to "Country", "मोमबत्ती" to "Candle", "साइकिल" to "Cycle", "कक्षा" to "Class", "नियंत्रण" to "Control",
        "कपड़ा" to "Cloth", "भीड़" to "Crowd", "जोड़ना" to "Connect", "कोना" to "Corner", "गाड़ी" to "Car",
        "सस्ता" to "Cheap", "कंघी" to "Comb", "पकाना" to "Cook",


        "खतरा" to "Danger", "अंधेरा" to "Dark", "तारीख" to "Date", "दिन" to "Day", "प्रिय" to "Dear",
        "मौत" to "Death", "गहरा" to "Deep", "हार" to "Defeat", "मरना" to "Die", "आहार" to "Diet",
        "अंतर" to "Difference", "खोदना" to "Dig", "रात का खाना" to "Dinner", "बीमारी" to "Disease", "दूरी" to "Distance",
        "डॉक्टर" to "Doctor", "कुत्ता" to "Dog", "दरवाजा" to "Door", "नीचे" to "Down", "घसीटना" to "Drag",
        "नाटक" to "Drama", "खींचना" to "Draw", "सपना" to "Dream", "पीना" to "Drink", "चलाना" to "Drive",
        "सूखा" to "Dry", "धूल" to "Dust", "बिंदु" to "Dot", "चित्र बनाना" to "Draw", "बतख" to "Duck",
        "दरवाज़ा" to "Door",

// Changed - 27-10-2025.


    )

    fun getList(): List<Pair<String, String>> = EasywordList
}

