package com.example.kotlinspringmockmvcwithdatabaserider.infra

import com.example.kotlinspringmockmvcwithdatabaserider.entity.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Spring Data の Customer エンティティの CRUD 用リポジトリ
 *
 */
@Repository
interface SpringDataCustomerRepository : CrudRepository<Customer, Int>
