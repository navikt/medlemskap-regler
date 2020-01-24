package no.nav.medlemskap.regler.common

import no.nav.medlemskap.regler.common.Verdier.uavklart

abstract class Regelsett(val fakta: Fakta) {

    private var konklusjon: Resultat? = null

    abstract fun evaluer(): Resultat

    protected fun konkluderMed(resultat: Resultat): Resultat? {
        konklusjon = resultat
        return null
    }

    protected fun hentUtKonklusjon(underresultat: Resultat): Resultat {
        return konklusjon?.copy(underresultat = mutableListOf(underresultat))
                ?: uavklart("Kom ikke til noen konklusjon")
    }

}
