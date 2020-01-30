package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.common.Resultattype
import no.nav.medlemskap.regler.personer.Personleser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RegelsettForVedtakTest {

    private val personleser = Personleser()

    @Test
    fun `person uten medl, gosys eller joark data får nei på manuelle vedtak`() {
        val regelsett = RegelsettForVedtak(initialiserFakta(personleser.enkelNorsk()))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.NEI, resultat.resultat)
    }

    @Test
    fun `person med vedtak i medl får ja på manuelle vedtak`() {
        val regelsett = RegelsettForVedtak(initialiserFakta(personleser.amerikanskMedl()))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

    @Test
    fun `person med oppgave i gosys får ja på manuelle vedtak`() {
        val regelsett = RegelsettForVedtak(initialiserFakta(personleser.amerikanskGosys()))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

    @Test
    fun `person med dokument i joark får ja på manuelle vedtak`() {
        val regelsett = RegelsettForVedtak(initialiserFakta(personleser.amerikanskJoark()))

        val resultat = regelsett.evaluer()

        assertEquals(Resultattype.JA, resultat.resultat)
    }

}
