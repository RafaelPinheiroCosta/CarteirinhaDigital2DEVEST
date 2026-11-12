package com.rafaelcosta.carteirinhadigital2devest.feature.admin.domain.model

data class TurmaCatalogo(
    val codigo: String,
    val curso: String,
    val unidadesCurriculares: List<String>
)
