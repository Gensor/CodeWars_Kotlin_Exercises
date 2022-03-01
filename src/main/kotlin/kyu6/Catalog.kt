package kyu6

/*
    You are given a small extract of a catalog:

        s = "<prod><name>drill</name><prx>99</prx><qty>5</qty></prod>

        <prod><name>hammer</name><prx>10</prx><qty>50</qty></prod>

        <prod><name>screwdriver</name><prx>5</prx><qty>51</qty></prod>

        <prod><name>table saw</name><prx>1099.99</prx><qty>5</qty></prod>

        <prod><name>saw</name><prx>9</prx><qty>10</qty></prod>

        ...
    (prx stands for price, qty for quantity) and an article i.e "saw".

    The function catalog(s, "saw") returns the line(s) corresponding to the article with $ before
    the prices:

        "table saw > prx: $1099.99 qty: 5\nsaw > prx: $9 qty: 10\n..."
    If the article is not in the catalog return "Nothing".

    Notes
        There is a blank line between two lines of the catalog.
        The same article may appear more than once. If that happens return all the lines concerned
        by the article (in the same order as in the catalog). The line separator of results may
        depend on the language \nor \r\n. In Pascal \n is replaced by LineEnding.
        in Perl use "£" instead of "$" before the prices.
        You can see examples in the "Sample tests".
 */

fun main(){
    val s = """<prod><name>drill</name><prx>99</prx><qty>5</qty></prod>

<prod><name>saw for metal</name><prx>13.80</prx><qty>32</qty></prod>

<prod><name>saw for metal</name><prx>5050</prx><qty>32</qty></prod>
<prod><name>saw</name><prx>5050</prx><qty>32</qty></prod>

<prod><name>wood pallet</name><prx>65</prx><qty>21</qty></prod>

<prod><name>circular fan</name><prx>80</prx><qty>8</qty></prod>

<prod><name>exhaust fan</name><prx>62</prx><qty>8</qty></prod>

<prod><name>window fan</name><prx>62</prx><qty>8</qty></prod>"""

    println(catalog(s, "saw"))
}

fun catalog(s: String, article: String): String {
    if(!s.contains(article))return "Nothing"
    val products = arrayListOf<Map<String, String>>()

    val catalog = s.split("</prod>")

    val regexForProduct = "<name>(.+)<\\/name><prx>(.+)<\\/prx><qty>(.+)<\\/qty>".toRegex()

    for (itemInCatalog in catalog){
        if (itemInCatalog.isEmpty())continue

        val matchResult = regexForProduct.find(itemInCatalog)
        val name = matchResult?.groupValues?.get(1)
        val price = matchResult?.groupValues?.get(2)
        val quantity = matchResult?.groupValues?.get(3)

        if (name != null && price != null && quantity != null) {
            products.add(mapOf("name" to name, "price" to price, "quantity" to quantity))
        }
    }

    return products.filter { it.get("name")?.contains(article) ?: false }
        .map { "${it.get("name")} > prx: \$${it.get("price")} qty: ${it.get("quantity")}"}
        .joinToString ("\n")
}


