package no.nav.medlemskap

import no.nav.medlemskap.domene.*
import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.common.Resultat
import no.nav.medlemskap.regler.v1.RegelsettForMedlemskap

class Regelkjøring(datagrunnlag: Datagrunnlag) {

    private val fakta = initialiserFakta(datagrunnlag)

    fun regelkjøring(): Resultat = RegelsettForMedlemskap(fakta).evaluer()

}
