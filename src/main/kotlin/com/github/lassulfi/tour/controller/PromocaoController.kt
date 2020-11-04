package com.github.lassulfi.tour.controller

import com.github.lassulfi.tour.model.Promocao
import com.github.lassulfi.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.ConcurrentHashMap

@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = promocaoService.getById(id)

    @PostMapping
    fun create(@RequestBody promocao: Promocao) {
        promocaoService.create(promocao)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        promocaoService.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao) {
        promocaoService.update(id, promocao)
    }

    @GetMapping
    fun getAll(@RequestParam(required = false, defaultValue = "", value = "local") localFilter: String) =
            promocaoService.getByLocal(localFilter)
}