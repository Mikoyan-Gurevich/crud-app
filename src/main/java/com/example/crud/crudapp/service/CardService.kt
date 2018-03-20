package com.example.crud.crudapp.service

import com.example.crud.crudapp.domain.Card
import com.example.crud.crudapp.persistence.CardRepository
import com.fasterxml.jackson.databind.ObjectMapper
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import java.lang.reflect.Field
import java.util.stream.Collectors
import kotlin.coroutines.experimental.buildIterator
import kotlin.reflect.jvm.reflect

@Component
@RequiredArgsConstructor
class CardService(private val cardRepository: CardRepository) {

    fun get(id: String): Card? {
        return cardRepository.findById(id).get()
    }

    fun add(card: Card): String {
        return cardRepository.save(card).id
    }

    fun softDelete(id: String): Card? {
        val cardToBeDeleted = get(id)?.copy(isEnabled = "0")
        return cardRepository.save(cardToBeDeleted)
    }

    fun updatevalue(mockString:StringBuilder?, str:String): StringBuilder? {
        var newMockString = mockString;
        if(mockString.isNullOrBlank()) {
            newMockString?.append(str)
        } else {
            newMockString = newMockString?.append(", ")?.append(str);
        }
        return newMockString
    }

    fun edit(id: String, obj: Card): Card? {
        var cardToBeEdited = get(id);
        println(obj)
        return cardRepository.save(cardToBeEdited);
    }

//    fun edit (obj:Map<String, String>): Card? {
//
//
//        val objectMapper = ObjectMapper()
//        val card = objectMapper.convertValue(obj,Card::class.java)
//
//        return cardRepository.save(card);
//    }

//    private fun merge(map:Card,dbCard: Card) : Card{
//        val fieldsOfMap = map::class.java.declaredFields.asList().stream()
//
//                .filter{it -> it.get(map) != null}.collect(Collectors.toSet())
//        val dbCard = map::class.java.declaredFields.asList().stream()
//                .filter{it -> it.get(dbCard) != null && !fieldsOfMap.contains(it) }
//                .collect(Collectors.toSet())
//
//        val cc : Card
//        val updatedFields = fieldsOfMap.union(dbCard)
//        val types =updatedFields.map { it -> it.type }.toTypedArray()
//        val values = updatedFields.map { it -> {
//            when{
//                dbCard.contains(it) ->  it.get(dbCard)
//                fieldsOfMap.contains(it) ->  it.get(map)
//                else -> null
//            }
//        } }.toTypedArray()
//
//        return Card().javaClass.getConstructor(*types).newInstance(values)
//
//
//
//    }

    fun hardDelete(id: String): Unit {
        return cardRepository.deleteById(id)
    }

    fun getAll(): Iterable<Card> {
        return cardRepository.findAll().filter { it -> it.isEnabled == "1" }
    }
}