package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.common.Resultattype
import no.nav.medlemskap.regler.personer.Personleser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RegelsettForEøsForordningenTest {

    private val personleser = Personleser()

    @Test
    fun `person med ett norsk statsborgerskap gir resultat ja`() {
        val regelsett = RegelsettForEøsforordningen(initialiserFakta(personleser.enkelNorsk()))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

    @Test
    fun `person med ett amerikansk statsborgerskap gir resuktat nei`() {
        val regelsett = RegelsettForEøsforordningen(initialiserFakta(personleser.enkelAmerikansk()))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.NEI, resultat.resultat)
    }

}
