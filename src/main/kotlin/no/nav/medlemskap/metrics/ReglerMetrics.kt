package no.nav.medlemskap.metrics

import io.prometheus.client.Counter

val regelCounter: Counter = Counter.build()
        .name("regel_calls_total")
        .labelNames("regel", "status")
        .help("counter for ja, nei, uavklart for regel calls")
        .register()