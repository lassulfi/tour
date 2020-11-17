package com.github.lassulfi.tour.service.impl

import com.github.lassulfi.tour.model.Promocao
import com.github.lassulfi.tour.repository.PromocaoRepository
import com.github.lassulfi.tour.service.PromocaoService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

@Component
class PromocaoServiceImpl(val repository: PromocaoRepository): PromocaoService {

    override fun getAll(offset: Int, limit: Int, sort: String?): List<Promocao> {
        val pages: Pageable = if(sort != null)
            PageRequest.of(offset, limit, Sort.by(sort).ascending()) else
            PageRequest.of(offset, limit)
        return this.repository.findAll(pages).toList()
    }

    override fun getById(id: Long): Promocao? = this.repository.findById(id).orElseGet(null)

    override fun create(promocao: Promocao): Promocao = this.repository.save(promocao)

    override fun delete(id: Long) {
        this.repository.deleteById(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        this.repository.save(promocao)
    }

    override fun getByLocal(local: String): List<Promocao> = listOf()

    override fun count(): Long = this.repository.count()
}

