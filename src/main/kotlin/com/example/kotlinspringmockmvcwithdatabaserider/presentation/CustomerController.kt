package com.example.kotlinspringmockmvcwithdatabaserider.presentation

import com.example.kotlinspringmockmvcwithdatabaserider.usecase.GetCustomerUseCase
import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.CustomerApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Customer
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.SingleCustomerReponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

/**
 * Customer API のルーティングコントローラー
 *
 * @property getCustomerUseCase Customer 単一取得ユースケース
 */
@RestController
class CustomerController(val getCustomerUseCase: GetCustomerUseCase) : CustomerApi {
    override fun getCustomerById(id: Int): ResponseEntity<SingleCustomerReponse> {
        val customer = getCustomerUseCase.execute(id)
        return ResponseEntity(
            SingleCustomerReponse(
                Customer(
                    id = customer.id,
                    firstName = customer.firstName,
                    lastName = customer.lastName
                )
            ),
            HttpStatus.OK
        )
    }
}
