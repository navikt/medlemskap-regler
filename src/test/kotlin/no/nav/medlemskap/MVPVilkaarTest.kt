package no.nav.medlemskap

import com.google.gson.GsonBuilder
import mu.KotlinLogging
import no.nav.medlemskap.domene.Personhistorikk
import no.nav.medlemskap.domene.Regelavklaring
import no.nav.nare.core.evaluations.Resultat
import org.junit.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class MVPVilkaarTest {

    private val logger = KotlinLogging.logger { }

    private val gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, localDateDeserializer).create()

    @Test
    fun `sjekk MVPVilkaar for gyldig person`() {
        val testPerson = "enkel_bosted_norge.json".asDatagrunnlag()

        val evaluering = mvpVilkaar.evaluer(testPerson)
        assertEquals(Resultat.JA, evaluering.resultat)
        logger.info("Resultat: $evaluering")
    }

    private fun String.asDatagrunnlag(): Regelavklaring =
            gson.fromJson(MVPVilkaarTest::class.java.getResource("/testpersoner/${this}").readText(),
                    Regelavklaring::class.java)
}
