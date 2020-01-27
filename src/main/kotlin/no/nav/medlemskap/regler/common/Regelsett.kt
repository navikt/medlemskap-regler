package no.nav.medlemskap.regler.common

import no.nav.medlemskap.metrics.regelCounter
import no.nav.medlemskap.regler.common.Funksjoner.uavklart

abstract class Regelsett(val navn: String, val fakta: Fakta) {

    private var konklusjon: Resultat? = null

    abstract fun evaluer(): Resultat

    protected fun konkluderMed(resultat: Resultat): Resultat? {
        konklusjon = resultat
        return null
    }

    protected fun hentUtKonklusjon(underresultat: Resultat): Resultat {
        return (konklusjon?.copy(underresultat = mutableListOf(underresultat))
                ?: uavklart("Kom ikke til noen konklusjon")).apply { regelCounter.labels(navn, this.resultat.name).inc() }
    }

}
