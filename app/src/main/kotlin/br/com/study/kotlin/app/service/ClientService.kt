package br.com.study.kotlin.app.service

import br.com.study.kotlin.app.dao.ClientDao
import br.com.study.kotlin.app.model.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService(@Autowired val clientDao: ClientDao) {

    fun save(client: Client): Client{
        return clientDao.save(client)
    }

    fun edit(client: Client): Client {
        val actualClient = Client(id = client.id,
                name = client.name,
                age = client.age)
        return clientDao.save(actualClient)
    }

    fun delete(client: Client){
        clientDao.delete(client)
    }

    fun findById(id: Long): Client{
        return clientDao.getOne(id)
    }

    fun listAll():MutableList<Client> {
        return clientDao.findAll()
    }
}