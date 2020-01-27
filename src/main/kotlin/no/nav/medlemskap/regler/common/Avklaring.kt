package no.nav.medlemskap.regler.common

data class Avklaring(
        val identifikator: String,
        val avklaring: String,
        val beskrivelse: String,
        val operasjon: (f: Fakta) -> Resultat
) {
    infix fun evaluerMed(fakta: Fakta): Resultat {
        val resultat = operasjon.invoke(fakta)
        return resultat.copy(identifikator = identifikator, avklaring = avklaring)
    }
}
