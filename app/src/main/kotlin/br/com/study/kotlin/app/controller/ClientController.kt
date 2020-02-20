package br.com.study.kotlin.app.controller

import br.com.study.kotlin.app.model.Client
import br.com.study.kotlin.app.service.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("client")
class ClientController(@Autowired val clientService: ClientService){

    @PostMapping()
    fun saveClient(@RequestBody client: Client): Client{
        clientService.save(client)
        return client
    }

    @PutMapping("edit")
    fun editClient(@RequestBody client: Client): Client{
        clientService.edit(client)
        return client
    }

    @DeleteMapping("delete")
    fun deleteClient(@RequestBody client: Client){
        clientService.delete(client)
    }

    @GetMapping("{id}")
    fun findClientById(@PathVariable("id") id: Long): Client{
        return clientService.findById(id)
    }

    @GetMapping("all")
    fun listAllClients(): MutableList<Client>{
        return clientService.listAll()
    }
}