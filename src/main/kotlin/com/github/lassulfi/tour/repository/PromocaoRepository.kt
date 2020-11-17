package com.github.lassulfi.tour.repository

import com.github.lassulfi.tour.model.Promocao
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import javax.transaction.Transactional

@Repository
interface PromocaoRepository: PagingAndSortingRepository<Promocao, Long> {

    @Query(value = "select p from Promocao p where p.preco <= :value and p.qtde_de_dias >= :dias")
    fun findByPrecoLessThen(@Param("valor") valor: Double, @Param("dias") dias: Int): List<Promocao>

    @Query(value = "select p from Promocao p where p.local  in :names")
    fun findByLocal(@Param("names") names: List<String>) : List<Promocao>

    @Query(value = "update Promocao p set p.preco = :valor where p.local = :local")
    @Transactional
    @Modifying
    fun updateByLocal(@Param("valor") preco: Double, @Param("local") local: String)
}