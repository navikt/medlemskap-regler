package no.nav.medlemskap.v1

import no.nav.medlemskap.domene.Regelavklaring

object AvklaringerKapittel2 {

    val avklarOmPersonErMedlemIhhtKapittel2 = Avklaring (
            identifikator = "KAP2-1",
            avklaring = "Personen er medlem i.h.h.t Folketrygden",
            beskrivelse = "Begrunnelse",
            operasjon = { brukerErMedlem(it) }
    )

    private fun brukerErMedlem(ra: Regelavklaring): Resultat {
        return Resultat(Resultattype.JA, "Person er medlem")
    }
}
