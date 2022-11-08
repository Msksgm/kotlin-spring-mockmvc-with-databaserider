package com.example.kotlinspringmockmvcwithdatabaserider.presentation

import com.example.realworldkotlinspringbootjdbc.openapi.generated.controller.CustomerApi
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.Customer
import com.example.realworldkotlinspringbootjdbc.openapi.generated.model.SingleCustomerReponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

/**
 * Customer API のルーティングコントローラー
 *
 */
@RestController
class CustomerController : CustomerApi {
    override fun getCustomerById(id: Int): ResponseEntity<SingleCustomerReponse> {
        return ResponseEntity(
            SingleCustomerReponse(
                Customer(
                    id = id,
                    firstName = "first_name",
                    lastName = "last_name"
                )
            ),
            HttpStatus.OK
        )
    }
}
