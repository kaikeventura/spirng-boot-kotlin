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
    fun updateClient(@RequestBody client: Client): Client{
        clientService.update(client)
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

    @GetMapping("{name}")
    fun findClientByName(@PathVariable("name") name: String): Array<Client>{
        return clientService.findByName(name)
    }

    @GetMapping("{age}")
    fun findClientByAge(@PathVariable("age") age: Int): Array<Client>{
        return clientService.findByAge(age)
    }

    @GetMapping("all")
    fun listAllClients(): MutableList<Client>{
        return clientService.listAll()
    }
}