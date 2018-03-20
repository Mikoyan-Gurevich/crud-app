package com.example.crud.crudapp.persistence

import com.example.crud.crudapp.domain.Card
import org.springframework.data.repository.CrudRepository

interface CardRepository : CrudRepository<Card,String>
{
}