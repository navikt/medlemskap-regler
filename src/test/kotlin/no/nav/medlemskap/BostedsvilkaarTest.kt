package no.nav.medlemskap

import no.nav.nare.core.evaluations.Resultat
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class BostedsvilkaarTest {

    @Test
    fun `sjekk bostedsvillkår for gyldig person`() {
        val testPerson = Person(Adresse(
                landkode = "NO",
                gyldigFra = LocalDate.now().minusMonths(20),
                gyldigTil = null
        ))

        val evaluering = bostedsvilkaar.evaluer(testPerson)
        assertEquals(Resultat.JA, evaluering.resultat)
    }

    @Test
    fun `sjekk bostedsvillkår for ugyldig person`() {
        val testPerson = Person(Adresse(
                landkode = "NO",
                gyldigFra = LocalDate.now().minusMonths(2),
                gyldigTil = null
        ))

        val evaluering = bostedsvilkaar.evaluer(testPerson)
        assertEquals(Resultat.NEI, evaluering.resultat)
    }
}