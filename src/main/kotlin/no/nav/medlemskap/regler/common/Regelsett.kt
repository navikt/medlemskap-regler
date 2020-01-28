package no.nav.medlemskap.regler.common

abstract class Regelsett(val fakta: Fakta) {

    private var konklusjon: Resultat? = null
    private val delresultat: MutableList<Resultat> = mutableListOf()

    protected abstract val KONKLUSJON_IDENTIFIKATOR: String
    protected abstract val KONKLUSJON_AVKLARING: String

    abstract fun evaluer(): Resultat

    protected fun avklar(metode: () -> Resultat): Resultat {
        val resultat = metode.invoke()
        delresultat.add(resultat)
        return resultat
    }

    protected fun konkluderMed(resultat: Resultat): Resultat? {
        konklusjon = resultat.copy (
                identifikator = KONKLUSJON_IDENTIFIKATOR,
                avklaring = KONKLUSJON_AVKLARING,
                delresultat = delresultat
        )
        return null
    }

    protected fun hentUtKonklusjon(underresultat: Resultat): Resultat {
        return konklusjon ?: uavklart("Kom ikke til noen konklusjon")
    }

    protected fun ja(begrunnelse: String): Resultat = Resultat (
            resultat= Resultattype.JA,
            beskrivelse = begrunnelse
    )

    protected fun nei(begrunnelse: String): Resultat = Resultat (
            resultat = Resultattype.NEI,
            beskrivelse = begrunnelse
    )

    protected fun uavklart(begrunnelse: String): Resultat = Resultat (
            resultat = Resultattype.UAVKLART,
            beskrivelse = begrunnelse
    )

}
