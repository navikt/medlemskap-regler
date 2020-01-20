package no.nav.medlemskap.v1

enum class Resultattype {
    JA, NEI, UAVKLART
}

data class Resultat (
        val resultat: Resultattype,
        val konklusjon: String,
        val underresultat: Resultat? = null
) {
    fun eller(other: Resultat): Resultat {
        if (resultat == Resultattype.JA) {
            return this.copy()
        } else {
            return other.copy()
        }
    }
}
