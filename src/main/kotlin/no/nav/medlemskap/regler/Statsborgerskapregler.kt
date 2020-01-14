package no.nav.medlemskap.regler

import no.nav.medlemskap.domene.Regelavklaring
import no.nav.medlemskap.domene.Statsborgerskap

object Statsborgerskapregler {

    val erNordiskStatsborger = Regelbeskrivelse<Regelavklaring>(
            identifikator = "ST1",
            beskrivelse = "Nordik statsborger",
            operasjon = { harUtelukkendeNordiskeStatsborgerskap(it.personhistorikk.statsborgerskap) }
    )

    private fun harUtelukkendeNordiskeStatsborgerskap(statsborgerskap: List<Statsborgerskap>): Boolean? =
            statsborgerskap.all { it.erNordisk() }


    private fun Statsborgerskap.erNordisk() =
            this.landkode == "NOR" ||
                    this.landkode == "SWE" ||
                    this.landkode == "FIN" ||
                    this.landkode == "ISL" ||
                    this.landkode == "DNK"
}
