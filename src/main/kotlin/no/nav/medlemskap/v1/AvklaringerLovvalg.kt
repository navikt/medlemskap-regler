package no.nav.medlemskap.v1

import no.nav.medlemskap.domene.Regelavklaring

object AvklaringerLovvalg {

    val avklarOmLovvalgErNorsk = Avklaring (
            identifikator = "LOVVALG-1",
            avklaring = "Personen er omfattet av norsk lov",
            beskrivelse = "Begrunnelse",
            operasjon = { personLovvalg(it) }
    )

    private fun personLovvalg(ra: Regelavklaring): Resultat {
        return Resultat(Resultattype.JA, "Personen er omfattet av norsk lov")
    }
}
