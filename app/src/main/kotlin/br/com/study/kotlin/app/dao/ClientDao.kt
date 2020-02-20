package br.com.study.kotlin.app.dao

import br.com.study.kotlin.app.model.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientDao : JpaRepository<Client, Long> {
    fun findByName(name: String):Array<Client>
    fun findByAge(age: Int):Array<Client>
}