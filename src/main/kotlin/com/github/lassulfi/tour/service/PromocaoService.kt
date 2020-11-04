package com.github.lassulfi.tour.service

import com.github.lassulfi.tour.model.Promocao

interface PromocaoService {
    fun create(promocao: Promocao)
    fun getById(id: Long): Promocao?
    fun delete(id: Long)
    fun update(id: Long, promocao: Promocao)
    fun getByLocal(local: String): List<Promocao>
}