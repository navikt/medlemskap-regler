package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.Fakta
import no.nav.medlemskap.regler.common.Regelsett
import no.nav.medlemskap.regler.common.Resultat
import no.nav.medlemskap.regler.common.Resultat.Companion.avklar
import no.nav.medlemskap.regler.common.Verdier.ja
import no.nav.medlemskap.regler.common.Verdier.uavklart

class RegelsettForMedlemskap(fakta: Fakta) : Regelsett(fakta) {

    private val vedtak = RegelsettForVedtak(fakta)
    private val eøsforordningen = RegelsettForEøsforordningen(fakta)
    private val lovvalgNorge = RegelsettForNorskLovvalg(fakta)

    override fun evaluer(): Resultat {
        val resultat =
                avklar {
                    vedtak.evaluer()
                } hvisJa {
                    konkluderMed(uavklart("Personen har vedtak"))
                } hvisUavklart {
                    konkluderMed(uavklart("Kan ikke vurdere vedtak"))
                } hvisNei {
                    avklar {
                        eøsforordningen.evaluer()
                    } hvisNei {
                        konkluderMed(uavklart("Personen er ikke omfattet av EØS-ordningen"))
                    } hvisUavklart {
                        konkluderMed(uavklart("Kan ikke vurdere EØS"))
                    } hvisJa {
                        avklar {
                            lovvalgNorge.evaluer()
                        } hvisNei {
                            konkluderMed(uavklart("Lovvalg er ikke Norge"))
                        } hvisUavklart {
                            konkluderMed(uavklart("Kan ikke vurdere lovvalg"))
                        } hvisJa {
                            konkluderMed(ja("Personen er medlem"))
                        }
                    }
                }

        return hentUtKonklusjon(resultat)
    }

}
