package no.nav.medlemskap.v1

import no.nav.medlemskap.domene.Regelavklaring

object AvklaringerEos {

    val avklarOmPersonErOmfattetAvEosavtalen = Avklaring (
            identifikator = "EØS-1",
            avklaring = "Person er omfattet av EØS-avtalen",
            beskrivelse = "Begrunnelse",
            operasjon = { personErEosStatsborger(it) }
    )

    private fun personErEosStatsborger(ra: Regelavklaring): Resultat {
        return Resultat(Resultattype.JA, "Person er statsborger i EØS")
    }
}
