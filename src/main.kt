var DATA = hashMapOf<String, String>()

fun main(args: Array<String>) {
    var command: String? = null
    var args: List<String>

    while (true) {
        command = readLine()

        if (command != null) {
            args = command.split(" ")
            when (args[0]) {
                "set" -> println(setPair(args[1], args[2]))

                "remove" -> {
                    var key: String? = null
                    var value: String? = null
                    for (arg in args.slice(1 until args.size)) {
                        val kwargs = arg.split("=")

                        if (kwargs[0] == "key") key = kwargs[1]
                        else if (kwargs[0] == "value") value = kwargs[1]
                    }
                    println(removePair(key, value))
                }

                "find" -> {
                    var key: String? = null
                    var value: String? = null
                    for (arg in args.slice(1 until args.size)) {
                        val kwargs = arg.split("=")

                        if (kwargs[0] == "key") key = kwargs[1]
                        else if (kwargs[0] == "value") value = kwargs[1]
                    }
                    println(findPair(key, value))
                }
            }

        } else println("Wrong command. Try again.")
    }
}


fun setPair(key: String, value: String): String {
    DATA[key] = value
    return "Pair '$key-$value' was added."
}

fun removePair(key: String? = null, value: String? = null): String {
    var del: String? = null

    if (key != null && value != null) {
        val r = DATA.remove(key, value)
        if (r) del = "$key-$value"

    } else if (key != null) {
        val r = DATA.remove(key)
        if (r != null) del = "$key-$r"

    } else {
        for (e in DATA.entries) {
            if (e.value == value) {

                del = "${e.key}-${DATA.remove(e.key)}"
            }
        }
    }
    return if (del != null) "Pair '$del' was deleted."
    else "Key or value was not found."

}

fun findPair(key: String? = null, value: String? = null): String {

    for (e in DATA.entries) {
        if (key != null && value != null) {
            if (e.key.startsWith(key) && e.value.startsWith(value)) {
                return "Pair '${e.key}-${e.value}' was found"
            }
        } else if (key != null) {
            if (e.key.startsWith(key)) {
                return "Pair '${e.key}-${e.value}' was found"
            }
        } else {
            if (e.value.startsWith(value!!)) {
                return "Pair '${e.key}-${e.value}' was found"
            }
        }

    }

    return "Pair for that fragment was not found"
}