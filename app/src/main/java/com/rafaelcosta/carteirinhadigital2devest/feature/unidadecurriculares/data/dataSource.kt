package com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.data

import com.rafaelcosta.carteirinhadigital2devest.feature.unidadecurriculares.domain.model.UnidadeCurricular

fun dataSource (): List<UnidadeCurricular> {
    return listOf(
        UnidadeCurricular(id = "1",nome = "Matemática",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "2",nome = "Portugues",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "3",nome = "Banco de Dados",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "4",nome = "Ciências",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "5",nome = "Fisica",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "6",nome = "Biologia",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "7",nome = "Matemática 2",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
        UnidadeCurricular(id = "8",nome = "Matemática 3",professor = "Dr. Silva",nota1 = 8.5,nota2 = 7.0,media = 7.75,faltas = 2),
    )
}