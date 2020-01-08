package no.nav.medlemskap

import no.nav.medlemskap.domene.Personstatus
import no.nav.medlemskap.domene.Regelavklaring
import no.nav.nare.core.evaluations.Evaluering
import no.nav.nare.core.specifications.Spesifikasjon

val utvandretVilkaar = Spesifikasjon<Regelavklaring>(
        identitet = "§ 2.1",
        beskrivelse = "...",
        implementasjon = {
            harIkkeStatusUtvandret(it.personhistorikk.personstatuser)
        }
)

fun harIkkeStatusUtvandret(personstatuser: List<Personstatus>): Evaluering =
        if (personstatuser.any { it.erUtvandret() }) {
            Evaluering.nei("Person har utvandret")
        } else {
            Evaluering.ja("Person har ikke utvandret")
        }

private fun Personstatus.erUtvandret() = personstatus == "UTVA"