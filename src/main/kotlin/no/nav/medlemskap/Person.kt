package no.nav.medlemskap

import java.time.LocalDate

data class Person(val adresse: Adresse)

data class Adresse(val landkode: String, val gyldigFra: LocalDate, val gyldigTil: LocalDate?) {

    fun erNorsk() = landkode == "NO"

    fun erEldreEnn12Mndr() = gyldigFra < LocalDate.now().minusMonths(12)
}