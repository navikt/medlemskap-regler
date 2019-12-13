package no.nav.medlemskap

enum class Resultat {
    JA, NEI, KANSKJE
}

data class Vurderingsresultat (val vurdering: Resultat)