package com.example.crud.crudapp.controller

import com.example.crud.crudapp.domain.Card
import com.example.crud.crudapp.service.CardService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
class CardController(private val cardService: CardService) {


    @GetMapping("_status")
    fun statusCheck(): String {
        return "Up and Running..."
    }

    @GetMapping("getCard/{id}")
    fun getCard(@PathVariable("id") id: String): Card? {
        return cardService.get(id);
    }

    @PostMapping("addCard")
    fun addCard(card: Card): String? {
        val updatedCard = card.copy(isActive = "1", isEnabled = "1")
        return cardService.add(updatedCard);
    }

    @GetMapping("getAllCards")
    fun getAllCards(): Iterable<Card>? {
        return cardService.getAll();
    }

    @GetMapping("deleteCard/{id}")
    fun deleteCard(@PathVariable("id") id: String): Card? {
        return cardService.softDelete(id);
    }

    @PutMapping("edit/{id}")
    fun editCard(@PathVariable("id") id: String, @RequestBody obj: Card): Card? {
        return cardService.edit(id, obj);
    }
}