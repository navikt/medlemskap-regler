package no.nav.medlemskap.v1

enum class Resultattype {
    JA, NEI, UAVKLART
}

data class Resultat (
        val resultat: Resultattype,
        val konklusjon: String,
        val underresultat: Resultat? = null
)
