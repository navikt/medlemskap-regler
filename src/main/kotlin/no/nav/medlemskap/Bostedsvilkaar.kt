package no.nav.medlemskap

import no.nav.nare.core.evaluations.Evaluering.Companion.ja
import no.nav.nare.core.evaluations.Evaluering.Companion.nei
import no.nav.nare.core.specifications.Spesifikasjon

internal val personHarNorskAdresse = Spesifikasjon<Person>(
        identitet = " § 2.1 første ledd",
        beskrivelse = "Person har norsk adresse",
        implementasjon = { adresseErNorsk(it.adresse) }
)

internal val personSinAdresseErMerEnn12Mndr = Spesifikasjon<Person>(
        identitet = "§ 2.1 andre ledd",
        beskrivelse = "Person sin adresse er eldre enn 12 måneder",
        implementasjon = { adresseErEldreEnn12Mndr(it.adresse) }
)

internal val personErBosattINorge = (personHarNorskAdresse og personSinAdresseErMerEnn12Mndr).med(
        identitet = "§ 2.1 ",
        beskrivelse = "Personer som er bosatt i Norge, er pliktige medlemmer i folketrygden."
)

val bostedsvilkaar = personErBosattINorge

internal fun adresseErNorsk(adresse: Adresse) =
        when {
            adresse.erNorsk() -> ja("Adressen er i Norge")
            else -> nei("Adresse er ikke i Norge")
        }

internal fun adresseErEldreEnn12Mndr(adresse: Adresse) =
        when {
            adresse.erEldreEnn12Mndr() -> ja("Adressen er eldre enn 12 måneder")
            else -> nei("Adresse er ikke eldre enn 12 måneder")
        }