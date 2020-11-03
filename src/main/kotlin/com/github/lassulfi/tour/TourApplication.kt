package com.github.lassulfi.tour

import com.github.lassulfi.tour.model.Promocao
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class TourApplication {
	companion object {
		val initialPromocoes = arrayOf(
				Promocao(1, "Maravilhosa viagem para Cancun", "Cancun", true, 7, 4999.99),
				Promocao(2, "Viagem Radical com Rapel e Escalada", "Nova Zelândia", false, 12, 12000.00),
				Promocao(3, "Viagem Espiritual", "Thailandia", false, 17, 15000.00),
				Promocao(4, "Viagem com a Família", "Gramado", true, 5, 3499.99)
		)
	}

	@Bean
	fun promocoes() = ConcurrentHashMap<Long, Promocao>(initialPromocoes.associateBy(Promocao::id))
}

fun main(args: Array<String>) {
	runApplication<TourApplication>(*args)
}
