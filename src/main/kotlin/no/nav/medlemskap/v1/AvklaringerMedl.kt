package no.nav.medlemskap.v1

import no.nav.medlemskap.domene.Regelavklaring

object AvklaringerMedl {

    val avklarOmPersonHarTidligereVedtak = Avklaring (
            identifikator = "MEDL-1",
            avklaring = "Bruker har et manuelt vedtak",
            beskrivelse = "Sjekk dette i MEDL",
            operasjon = { personHarDataIMedl(it) }
    )


    private fun personHarDataIMedl(ra: Regelavklaring): Resultat {
        return Resultat(Resultattype.JA, "Person har manuelt vedtak")
    }
}
