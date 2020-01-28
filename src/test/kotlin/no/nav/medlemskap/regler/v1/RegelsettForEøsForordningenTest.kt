package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.common.Resultattype
import no.nav.medlemskap.regler.util.TestpersonLeser.Companion.lesNorskeDatagrunnlagFraFil
import no.nav.medlemskap.regler.util.TestpersonLeser.Companion.lesUtenlandskeDatagrunnlagFraFil
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RegelsettForEøsForordningenTest {

    @Test
    fun `person med ett norsk statsborgerskap gir resultat ja`() {
        val enkelNorsk = lesNorskeDatagrunnlagFraFil("enkel_norsk_uten_arbeid")
        val regelsett = RegelsettForEøsforordningen(initialiserFakta(enkelNorsk))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

    @Test
    fun `person med ett amerikansk statsborgerskap gir resuktat nei`() {
        val enkelAmerikansk = lesUtenlandskeDatagrunnlagFraFil("enkel_amerikansk_uten_arbeid")
        val regelsett = RegelsettForEøsforordningen(initialiserFakta(enkelAmerikansk))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.NEI, resultat.resultat)
    }
}
