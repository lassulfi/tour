package com.github.lassulfi.tour.model

import javax.persistence.*

@Entity
@Table(name = "TBL_PROMOCAO")
data class Promocao (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 1L,
        val descricao: String = "",
        val local: String = "",
        val isAllInclusive: Boolean = false,
        @Column(name = "qtde_de_dias") val qtdDias: Int = 1,
        val preco: Double = 0.0
)