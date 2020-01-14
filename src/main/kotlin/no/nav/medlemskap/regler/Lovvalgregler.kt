package no.nav.medlemskap.regler

import no.nav.medlemskap.domene.Regelavklaring

object Lovvalgregler {

    val alleredeAvklartLovvalg = Regelbeskrivelse<Regelavklaring> (
            identifikator = "LOVVALG1",
            beskrivelse = "Sjekk om person allerede har fått avklart lovvalg",
            operasjon = { harAlleredeAvklartLovval(it) }
    )

    val erLovalgAvklartSomNorsk = Regelbeskrivelse<Regelavklaring> (
            identifikator = "LOVVALG2",
            beskrivelse = "Sjekk om lovvalg er norsk",
            operasjon = { erLovvalgNorsk(it) }
    )

    private fun harAlleredeAvklartLovval(avk: Regelavklaring): Boolean? = true

    private fun erLovvalgNorsk(avk: Regelavklaring): Boolean? = true
}
