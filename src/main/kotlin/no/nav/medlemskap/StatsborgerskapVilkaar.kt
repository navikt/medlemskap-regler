package no.nav.medlemskap

import no.nav.medlemskap.domene.Regelavklaring
import no.nav.medlemskap.domene.Statsborgerskap
import no.nav.nare.core.evaluations.Evaluering
import no.nav.nare.core.specifications.Spesifikasjon

val statsborgerskapVilkaar = Spesifikasjon<Regelavklaring>(
        identitet = "§ 2.1",
        beskrivelse = "...",
        implementasjon = {
            harUtelukkendeGyldigStatsborgerskap(it.personhistorikk.statsborgerskap)
        }
)

private fun harUtelukkendeGyldigStatsborgerskap(statsborgerskap: List<Statsborgerskap>): Evaluering =
        if (statsborgerskap.any { it.erIkkeNordisk() }) {
            Evaluering.nei("Person har ikke utelukkende nordisk statsborgerskap")
        } else {
            Evaluering.ja("Person har nordisk statsborgerskap")
        }

private fun Statsborgerskap.erNordisk() =
        this.landkode == "NOR" ||
                this.landkode == "SWE" ||
                this.landkode == "FIN" ||
                this.landkode == "ISL" ||
                this.landkode == "DNK"

private fun Statsborgerskap.erIkkeNordisk() = !erNordisk()