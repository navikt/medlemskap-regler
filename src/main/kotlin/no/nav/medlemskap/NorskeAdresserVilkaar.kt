package no.nav.medlemskap

import no.nav.medlemskap.domene.Adresse
import no.nav.medlemskap.domene.Regelavklaring
import no.nav.nare.core.evaluations.Evaluering
import no.nav.nare.core.specifications.Spesifikasjon

val norskeAdresserVilkaar = Spesifikasjon<Regelavklaring>(
        identitet = "§ 2.1",
        beskrivelse = "..",
        implementasjon = { harUtelukkendeNorskeAdresser(it.personhistorikk.bostedsadresser + it.personhistorikk.midlertidigAdresser + it.personhistorikk.postadresser) }
)

private fun harUtelukkendeNorskeAdresser(adresser: List<Adresse>): Evaluering =
        if (adresser.any { it.erIkkeNorsk() }) {
            Evaluering.nei("Person har ikke kun norske adresser")
        } else {
            Evaluering.ja("Person har kun norske adresser")
        }

private fun Adresse.erNorsk() =
        this.landkode == "NO" || this.landkode == "NOR"

private fun Adresse.erIkkeNorsk() = !erNorsk()