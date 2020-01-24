package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.*
import no.nav.medlemskap.regler.common.Resultat.Companion.avklar
import no.nav.medlemskap.regler.common.Verdier.inneholder
import no.nav.medlemskap.regler.common.Verdier.ja
import no.nav.medlemskap.regler.common.Verdier.nei
import no.nav.medlemskap.regler.common.Verdier.uavklart

class RegelsettForEøsforordningen(fakta: Fakta) : Regelsett(fakta) {

    override fun evaluer(): Resultat {
        val resultat =
                avklar { erPersonOmfattetAvEøsforordningen evaluerMed fakta }
                        .hvisJa { konkluderMed(ja("Personen er omfattet av EØS-ordningen")) }
                        .hvisNei { konkluderMed(uavklart("Personen er ikke omfattet av EØS-ordningen")) }

        return hentUtKonklusjon(resultat)
    }

    private val eøsland = listOf("NOR", "SVE", "DEN", "FIN", "ISL", "GER", "FRA") // TODO Osv...

    val erPersonOmfattetAvEøsforordningen = Avklaring (
            identifikator = "4",
            avklaring = "Er personen omfattet av EØS-forordningen?",
            beskrivelse = "",
            operasjon = { sjekkStatsborgerskap(it) }
    )

    private fun sjekkStatsborgerskap(fakta: Fakta): Resultat {
        return if (eøsland inneholder fakta.personensSisteStatsborgerskap()) {
            ja("Personen er statsborger i et EØS-land.")
        } else {
            nei("Personen er ikke statsborger i et EØS-land.")
        }
    }

}
