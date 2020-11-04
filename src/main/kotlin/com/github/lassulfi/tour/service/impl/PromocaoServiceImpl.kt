package com.github.lassulfi.tour.service.impl

import com.github.lassulfi.tour.model.Promocao
import com.github.lassulfi.tour.service.PromocaoService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromocaoServiceImpl: PromocaoService {
    companion object {
        val initialPromocoes = arrayOf(
                Promocao(1, "Maravilhosa viagem para Cancun", "Cancun", true, 7, 4999.99),
                Promocao(2, "Viagem Radical com Rapel e Escalada", "Nova Zelândia", false, 12, 12000.00),
                Promocao(3, "Viagem Espiritual", "Thailandia", false, 17, 15000.00),
                Promocao(4, "Viagem com a Família", "Gramado", true, 5, 3499.99)
        )
    }

    var promocoes = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))

    override fun getById(id: Long): Promocao? = promocoes[id]

    override fun create(promocao: Promocao) {
        promocoes[promocao.id] = promocao
    }

    override fun delete(id: Long) {
        promocoes.remove(id)
    }

    override fun update(id: Long, promocao: Promocao) {
        this.delete(id)
        this.create(promocao)
    }

    override fun getByLocal(local: String): List<Promocao> = promocoes
            .filter { it.value.local.contains(local, true) }
            .map (Map.Entry<Long, Promocao>::value)
            .toList()
}