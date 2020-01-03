package no.nav.medlemskap

import no.nav.medlemskap.domene.Adresse
import no.nav.medlemskap.domene.Personhistorikk
import no.nav.nare.core.evaluations.Evaluering
import no.nav.nare.core.evaluations.Evaluering.Companion.ja
import no.nav.nare.core.evaluations.Evaluering.Companion.nei
import no.nav.nare.core.specifications.Spesifikasjon
import java.time.LocalDate

internal val personErBosattINorge = Spesifikasjon<Personhistorikk>(
        identitet = "§ 2.1",
        beskrivelse = "Personer som er bosatt i Norge, er pliktige medlemmer i folketrygden.",
        implementasjon = { harGyldigAdresseSiste12Mndr(it.bostedsadresser) }
)

val bostedsvilkaar = personErBosattINorge

private fun harGyldigAdresseSiste12Mndr(adresser: List<Adresse>): Evaluering =
        if (adresser.any { it.erNorsk() && it.fom.elderEnn12Mndr() && it.tom.erGyldigIDag() }) {
            ja("Person har bostedsadresse i Norge siste 12 måneder")
        } else {
            nei("Person har ikke hatt gyldig bostedsadresse i Norge siste 12 måneder")
        }

private fun Adresse.erNorsk() =
        this.landkode == "NO"

private fun LocalDate?.elderEnn12Mndr() =
        this?.isBefore(LocalDate.now().minusMonths(12)) ?: true

private fun LocalDate?.erGyldigIDag() =
        this?.isAfter(LocalDate.now()) ?: true
