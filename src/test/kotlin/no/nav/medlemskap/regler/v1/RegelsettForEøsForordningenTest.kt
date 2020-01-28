package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.Fakta
import no.nav.medlemskap.regler.common.Resultattype
import no.nav.medlemskap.regler.personer.enkelAmerikansk
import no.nav.medlemskap.regler.personer.enkelNorsk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegelsettForEøsForordningenTest {

    @Test
    fun `person med ett norsk statsborgerskap gir resultat ja`() {
        val regelsett = RegelsettForEøsforordningen(Fakta.initialiserFakta(enkelNorsk))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

    @Test
    fun `person med ett amerikansk statsborgerskap gir resuktat nei`() {
        val regelsett = RegelsettForEøsforordningen(Fakta.initialiserFakta(enkelAmerikansk))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.NEI, resultat.resultat)
    }
}
