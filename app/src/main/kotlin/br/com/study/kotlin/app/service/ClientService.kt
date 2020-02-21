package br.com.study.kotlin.app.service

import br.com.study.kotlin.app.dao.ClientDao
import br.com.study.kotlin.app.exception.client.ClientNotSaveException
import br.com.study.kotlin.app.exception.client.ClientNotUpdateException
import br.com.study.kotlin.app.model.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientService(@Autowired val clientDao: ClientDao) {

    fun save(client: Client): Client {
        if (client.name == null || client.age == null){
            throw ClientNotSaveException("[ClientNotSaveException] Parameters cannot be null!")
        }
        if (client.id != null){
            throw ClientNotSaveException("[ClientNotSaveException] The id parameter must be null!")
        }
        return clientDao.save(client)
    }

    fun update(client: Client): Client {
        return if (client.id == null || client.name == null || client.age == null){
            throw ClientNotUpdateException("[ClientNotUpdateException] Parameters cannot be null!")
        }
        else clientDao.save(client)
    }

    fun delete(client: Client) = clientDao.delete(client)

    fun findById(id: Long): Client = clientDao.getOne(id)

    fun listAll(): MutableList<Client> = clientDao.findAll()

    fun findByName(name: String): Array<Client> = clientDao.findByName(name)

    fun findByAge(age: Int): Array<Client> = clientDao.findByAge(age)
}