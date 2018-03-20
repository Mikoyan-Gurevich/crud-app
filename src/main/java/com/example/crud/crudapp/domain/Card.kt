package com.example.crud.crudapp.domain

import javax.persistence.Entity

@Entity
data class Card(
        @javax.persistence.Id val id: String = "",
        val caption: String = "",
        val description: String = "",
        val imageUrl: String = "",
        val isActive: String = "",
        val isEnabled: String = ""
)