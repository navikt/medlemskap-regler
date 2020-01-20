package no.nav.medlemskap.v1

import no.nav.medlemskap.domene.Regelavklaring

object AvklaringerTrygdeavtaler {

    val avklarOmPersonErOmfattetAvNorskTrygdeavtale = Avklaring (
            identifikator = "TRYGDEAVTALER-1",
            avklaring = "Person er omfattet av norsk trygdeavtale",
            beskrivelse = "Begrunnelse",
            operasjon = { personOmfattetAvNorskTrygdeavtale(it) }
    )

    private fun personOmfattetAvNorskTrygdeavtale(ra: Regelavklaring): Resultat {
        return Resultat(Resultattype.JA, "Personen er omfattet av norsk trygdeavtale")
    }
}
