package no.nav.medlemskap.regler.v1

import com.google.gson.GsonBuilder
import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.common.Resultattype
import no.nav.medlemskap.regler.personer.enkelAmerikansk
import no.nav.medlemskap.regler.personer.enkelNorsk
import no.nav.medlemskap.regler.personer.enkelNorskMedArbeid
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RegelsettForEøsForordningenTest {

    @Test
    fun `person med ett norsk statsborgerskap gir resultat ja`() {
        val regelsett = RegelsettForEøsforordningen(initialiserFakta(enkelNorsk))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

    @Test
    fun `person med ett amerikansk statsborgerskap gir resuktat nei`() {
        val regelsett = RegelsettForEøsforordningen(initialiserFakta(enkelAmerikansk))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.NEI, resultat.resultat)
    }

    @Test
    fun test() {
        val res = RegelsettForMedlemskap(initialiserFakta(enkelNorskMedArbeid)).evaluer()

        val gson = GsonBuilder().setPrettyPrinting().create()
        print(gson.toJson(res))
    }

}
