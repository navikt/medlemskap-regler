package no.nav.medlemskap.v1

import no.nav.medlemskap.domene.Regelavklaring

class Avklaring (
        val identifikator: String,
        val avklaring: String,
        val beskrivelse: String,
        val operasjon: (ra: Regelavklaring) -> Resultat
) {
    var regelVedJa: Avklaring? = null
    var regelVedNei: Avklaring? = null
    var regelVedUavklart: Avklaring? = null

    fun evaluer(ra: Regelavklaring): Resultat {
        val resultat = operasjon.invoke(ra)

        return if (resultat.resultat == Resultattype.JA && regelVedJa != null) {
            val underresultat = regelVedJa!!.evaluer(ra)
            resultat.copy(underresultat = underresultat)
        } else if (resultat.resultat == Resultattype.NEI && regelVedNei != null) {
            val underresultat = regelVedNei!!.evaluer(ra)
            resultat.copy(underresultat = underresultat)
        } else if (resultat.resultat == Resultattype.UAVKLART && regelVedUavklart != null) {
            val underresultat = regelVedUavklart!!.evaluer(ra)
            resultat.copy(underresultat = underresultat)
        } else {
            resultat
        }
    }

    fun hvisJa(neste: Avklaring): Avklaring {
        regelVedJa = neste
        return this
    }

    fun hvisNei(neste: Avklaring): Avklaring {
        regelVedNei = neste
        return this
    }

    fun hvisUavklart(neste: Avklaring): Avklaring {
        regelVedUavklart = neste
        return this
    }
}
