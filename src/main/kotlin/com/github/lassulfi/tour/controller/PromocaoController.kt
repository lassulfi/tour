package com.github.lassulfi.tour.controller

import com.github.lassulfi.tour.model.Promocao
import com.github.lassulfi.tour.responses.CreatedResponse
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
    fun getById(@PathVariable id: Long): ResponseEntity<Promocao?> {
        val promocao = this.promocaoService.getById(id)
        val status = if(promocao == null) HttpStatus.NOT_FOUND else HttpStatus.OK

        return ResponseEntity(promocao, status)
    }

    @PostMapping
    fun create(@RequestBody promocao: Promocao): ResponseEntity<CreatedResponse> {
        promocaoService.create(promocao)

        val uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(promocao.id)
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
    fun getAll(@RequestParam(required = false, defaultValue = "", value = "local") localFilter: String):
            ResponseEntity<List<Promocao>> {
        val promocoes = promocaoService.getByLocal(localFilter)
        var status = if(promocoes.size == 0) HttpStatus.NO_CONTENT else HttpStatus.OK

        return ResponseEntity(promocoes, status)

    }
}