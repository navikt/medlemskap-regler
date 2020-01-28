package no.nav.medlemskap.regler.metrics

import assertk.assertThat
import assertk.assertions.*
import io.prometheus.client.Collector
import io.prometheus.client.CollectorRegistry
import no.nav.medlemskap.regler.common.Fakta.Companion.initialiserFakta
import no.nav.medlemskap.regler.util.TestpersonLeser.Companion.lesUtenlandskeDatagrunnlagFraFil
import no.nav.medlemskap.regler.v1.RegelsettForEøsforordningen
import org.junit.jupiter.api.Test


class RegelMetricsTest {

    @Test
    fun `evaluering av regelsett for eøs forordningen for amerikansk statsborgerskap gir to metrikker`() {
        RegelsettForEøsforordningen(initialiserFakta(lesUtenlandskeDatagrunnlagFraFil("enkel_amerikansk_uten_arbeid"))).evaluer()

        val sampleList = CollectorRegistry.defaultRegistry.metricFamilySamples().toList().flatMap { it.samples.toList() }

        assertThat(sampleList.map { it.name }.distinct()).containsOnly("regel_calls_total")
        assertThat(sampleList.map { it.name }.toList()).size().isEqualTo(2)

        assertThat(sampleList).extracting(Collector.MetricFamilySamples.Sample::labelNames, Collector.MetricFamilySamples.Sample::labelValues).contains(listOf("regel", "status") to listOf("Er personen statsborger i et EØS land?", "NEI"))
        assertThat(sampleList).extracting(Collector.MetricFamilySamples.Sample::labelNames, Collector.MetricFamilySamples.Sample::labelValues).contains(listOf("regel", "status") to listOf("Regelsett for EØS forordningen", "NEI"))

        assertThat(sampleList).extracting(Collector.MetricFamilySamples.Sample::labelNames, Collector.MetricFamilySamples.Sample::value).contains(listOf("regel", "status") to 1.0)
        assertThat(sampleList).extracting(Collector.MetricFamilySamples.Sample::labelNames, Collector.MetricFamilySamples.Sample::value).contains(listOf("regel", "status") to 1.0)
    }

}
