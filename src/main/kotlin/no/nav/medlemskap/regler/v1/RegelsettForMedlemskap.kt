package no.nav.medlemskap.regler.v1

import no.nav.medlemskap.regler.common.Fakta
import no.nav.medlemskap.regler.common.Regelsett
import no.nav.medlemskap.regler.common.Resultat
import no.nav.medlemskap.regler.common.Resultat.Companion.avklar

class RegelsettForMedlemskap(fakta: Fakta) : Regelsett(fakta) {

    private val manuelleVedtakFraNav = RegelsettForVedtak(fakta)
    private val eøsforordningen = RegelsettForEøsforordningen(fakta)
    private val lovvalgNorge = RegelsettForNorskLovvalg(fakta)

    override fun evaluer(): Resultat {
        val resultat =
                avklar {
                    manuelleVedtakFraNav.evaluer()
                } hvisJa {
                    konkluderMed(uavklart("Personen har et manuelt vedtak om medlemskap fra NAV"))
                } hvisUavklart {
                    konkluderMed(uavklart("Kan ikke vurdere manuelle vedtak på grunn av mangelfulle data"))
                } hvisNei {
                    avklar {
                        eøsforordningen.evaluer()
                    } hvisNei {
                        konkluderMed(uavklart("Personen er ikke omfattet av EØS-ordningen"))
                    } hvisUavklart {
                        konkluderMed(uavklart("Kan ikke vurdere EØS på grunn av mangelfulle data"))
                    } hvisJa {
                        avklar {
                            lovvalgNorge.evaluer()
                        } hvisNei {
                            konkluderMed(uavklart("Lovvalg er ikke Norge"))
                        } hvisUavklart {
                            konkluderMed(uavklart("Kan ikke vurdere lovvalg på grunn av mangelfulle data"))
                        } hvisJa {
                            konkluderMed(ja("Personen er omfattet av norsk lovvalg, og dermed medlem"))
                        }
                    }
                }

        return hentUtKonklusjon(resultat)
    }

}
