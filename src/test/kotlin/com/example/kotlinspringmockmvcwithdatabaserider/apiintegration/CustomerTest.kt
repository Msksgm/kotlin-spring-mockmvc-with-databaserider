package com.example.kotlinspringmockmvcwithdatabaserider.apiintegration

import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.core.api.dataset.ExpectedDataSet
import com.github.database.rider.junit5.api.DBRider
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

class CustomerTest {
    @SpringBootTest
    @AutoConfigureMockMvc
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @DBRider
    class GetCustomerById {
        @Autowired
        lateinit var mockMvc: MockMvc

        @Test
        @DataSet("datasets/yml/given/common.yml")
        @ExpectedDataSet(
            value = ["datasets/yml/given/common.yml"],
            orderBy = ["id"]
        )
        fun `正常系 Customer の単一取得`() {
            /**
             * given:
             * - 存在するユーザー ID
             */
            val id = 1

            /**
             * when:
             */
            val response = mockMvc.perform(
                MockMvcRequestBuilders
                    .get("/api/customers/$id")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andReturn().response
            val actualStatus = response.status
            val actualResponseBody = response.contentAsString

            /**
             * then:
             * - ステータスコードが一致する
             * - レスポンスボディが一致する
             */
            val expectedStatus = HttpStatus.OK.value()
            val expectedResponseBody =
                """
                    {
                      "customer": {
                        id: 1,
                        firstName: "Alice",
                        lastName: "Sample1"
                      }
                    }
                """.trimIndent()
            Assertions.assertThat(actualStatus).isEqualTo(expectedStatus)
            JSONAssert.assertEquals(
                expectedResponseBody,
                actualResponseBody,
                JSONCompareMode.STRICT
            )
        }
    }
}
