package com.example.kotlinspringmockmvcwithdatabaserider.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

/**
 * Customer テーブルのエンティティ
 *
 * @property id
 * @property firstName
 * @property lastName
 */
@Table("customer")
data class Customer(
    @Id val id: Int,
    val firstName: String,
    val lastName: String
)
