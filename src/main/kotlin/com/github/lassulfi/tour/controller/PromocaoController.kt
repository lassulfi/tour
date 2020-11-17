package com.github.lassulfi.tour.controller

import com.github.lassulfi.tour.exception.PromocaoNotFoundException
import com.github.lassulfi.tour.model.Promocao
import com.github.lassulfi.tour.responses.CreatedResponse
import com.github.lassulfi.tour.responses.ErrorMessage
import com.github.lassulfi.tour.service.PromocaoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*
import java.util.concurrent.ConcurrentHashMap
@RestController
@RequestMapping(value = ["/promocoes"])
class PromocaoController {

    @Autowired
    lateinit var promocaoService: PromocaoService

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Any> {
        val promocao = this.promocaoService.getById(id)
        return if (promocao != null)
            ResponseEntity(promocao, HttpStatus.OK)
        else
            ResponseEntity(ErrorMessage("Not Found", "Promoção ${id} não encontrada"),
                    HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao): ResponseEntity<CreatedResponse> {
        val p = promocaoService.create(promocao)

        val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.id)
                .toUri();

        val body = CreatedResponse("success", Date())

        return ResponseEntity.created(uri).body(body)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(promocaoService.getById(id) != null) {
            status = HttpStatus.NO_CONTENT
            promocaoService.delete(id)
        }
        return ResponseEntity(Unit, status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody promocao: Promocao): ResponseEntity<Unit> {
        var status = HttpStatus.NOT_FOUND
        if(promocaoService.getById(id) != null) {
            status = HttpStatus.NO_CONTENT
            promocaoService.update(id, promocao)
        }

        return ResponseEntity(Unit, status)
    }

    @GetMapping
    fun getAll(
            @RequestParam(required = false, defaultValue = "1", value = "_offset") offset: Int,
            @RequestParam(required = false, defaultValue = "3", value = "_limit") limit: Int,
            @RequestParam(required = false, defaultValue = "", value = "sort") sortBy: String, @RequestParam(required = false) maiorPreco: String):
            ResponseEntity<List<Promocao>> {
        val promocoes = if (sortBy != null) promocaoService.getAll(offset, limit, sortBy) else promocaoService.getAll(offset, limit, null)
        var status = if(promocoes.isEmpty()) HttpStatus.NO_CONTENT else HttpStatus.PARTIAL_CONTENT

        return ResponseEntity(promocoes, status)
    }

    @GetMapping("/total-registros")
    fun count(): ResponseEntity<Map<String, Long>> = ResponseEntity.ok(mapOf("total_registros" to this.promocaoService.count()))
}