package no.nav.medlemskap.regler.common

abstract class Regelsett(val fakta: Fakta) {

    private var konklusjon: Resultat? = null

    private val delresultat: MutableList<Resultat> = mutableListOf()

    abstract fun evaluer(): Resultat

    protected fun konkluderMed(resultat: Resultat): Resultat? {
        konklusjon = resultat
        return null
    }

    protected fun hentUtKonklusjon(underresultat: Resultat): Resultat {
        return konklusjon?.copy(delresultat = mutableListOf(underresultat) + delresultat)
                ?: uavklart("Kom ikke til noen konklusjon")
    }

    protected fun ja(begrunnelse: String): Resultat = Resultat (
            resultat= Resultattype.JA,
            beskrivelse = begrunnelse
    ).also { delresultat.add(it) }

    protected fun nei(begrunnelse: String): Resultat = Resultat (
            resultat = Resultattype.NEI,
            beskrivelse = begrunnelse
    ).also { delresultat.add(it) }

    protected fun uavklart(begrunnelse: String): Resultat = Resultat (
            resultat = Resultattype.UAVKLART,
            beskrivelse = begrunnelse
    ).also { delresultat.add(it) }

}
