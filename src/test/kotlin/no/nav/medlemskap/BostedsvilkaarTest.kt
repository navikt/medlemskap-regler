package no.nav.medlemskap

import com.google.gson.GsonBuilder
import no.nav.medlemskap.domene.Personhistorikk
import no.nav.medlemskap.domene.Regelavklaring
import no.nav.nare.core.evaluations.Resultat
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class BostedsvilkaarTest {

    private val gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, localDateDeserializer).create()

    @Test
    fun `sjekk bostedsvillkår for gyldig person`() {
        val testPerson = "enkel_bosted_norge.json".asDatagrunnlag()

        val evaluering = bostedsvilkaar.evaluer(testPerson)
        assertEquals(Resultat.JA, evaluering.resultat)
    }

    @Test
    fun `sjekk bostedsvillkår for ugyldig person`() {
        val testPerson = "enkel_bosted_utland.json".asDatagrunnlag()

        val evaluering = bostedsvilkaar.evaluer(testPerson)
        assertEquals(Resultat.NEI, evaluering.resultat)
    }

    private fun String.asDatagrunnlag(): Regelavklaring =
            gson.fromJson(BostedsvilkaarTest::class.java.getResource("/testpersoner/${this}").readText(),
                    Regelavklaring::class.java)
}
