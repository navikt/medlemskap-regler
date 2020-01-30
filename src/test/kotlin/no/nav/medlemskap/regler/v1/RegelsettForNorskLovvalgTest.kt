package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.domene.Datagrunnlag
import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.common.Resultattype
import no.nav.medlemskap.regler.personer.Personleser
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class RegelsettForNorskLovvalgTest {

    private val personleser = Personleser()

    @Test
    fun `person med en norsk arbeidsgiver og kun arbeid i Norge, ikke maritim eller pilot, får ja`() {
        assertEquals(Resultattype.JA, evaluer(personleser.enkelNorskArbeid()))
    }

    @Test
    fun `person med en norsk arbeidsgiver og kun arbeid i Norge, maritimt på norsk skip, får ja`() {
        assertEquals(Resultattype.JA, evaluer(personleser.enkelNorskMaritim()))
    }

    private fun evaluer(datagrunnlag: Datagrunnlag): Resultattype {
        val regelsett = RegelsettForNorskLovvalg(initialiserFakta(datagrunnlag))
        return regelsett.evaluer().resultat
    }

}
