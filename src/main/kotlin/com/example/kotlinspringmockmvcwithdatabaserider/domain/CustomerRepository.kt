package com.example.kotlinspringmockmvcwithdatabaserider.domain

import com.example.kotlinspringmockmvcwithdatabaserider.entity.Customer

/**
 * CustomerRepository Customer エンティティのリポジトリ
 *
 */
interface CustomerRepository {
    /**
     * Customer の単一取得メソッド
     *
     * @param id
     * @return
     */
    fun getCustomer(id: Int): Customer = throw NotImplementedError()

    /**
     * Customer の登録メソッド
     *
     * @param firstName
     * @param lastName
     * @return
     */
    fun registerCustomer(firstName: String, lastName: String): Customer = throw NotImplementedError()
}
