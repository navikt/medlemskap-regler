package no.nav.medlemskap.regler.common

import no.nav.medlemskap.metrics.regelCounter

data class Avklaring(
        val identifikator: String,
        val avklaring: String,
        val beskrivelse: String,
        val operasjon: (f: Fakta) -> Resultat
) {
    infix fun evaluerMed(fakta: Fakta): Resultat {
        val resultat = operasjon.invoke(fakta).apply {  regelCounter.labels(avklaring, this.resultat.name).inc() }
        return resultat.copy(identifikator = identifikator, avklaring = avklaring)
    }
}
