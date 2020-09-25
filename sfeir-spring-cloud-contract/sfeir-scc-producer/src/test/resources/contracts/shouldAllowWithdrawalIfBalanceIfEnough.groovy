import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


Contract.make {
  description("should allow withdrawal if balance is enough")

  request {
    url("/withdraw")
    method("POST")
    headers {
      contentType(MediaType.APPLICATION_JSON_VALUE)
    }
    body(
      accountIban: $(anyNonBlankString()),
      ownerId: "rich_user",
      amount: $(consumer(regex("[1-9][0-9]*")), producer("2000")),
    )
  }

  response {
    status(HttpStatus.OK.value())

    headers {
      contentType(MediaType.APPLICATION_JSON_VALUE)
    }

    body(
      message : "Withdrawal succeeded",
      newBalance: "3000",
      errors: []
    )
  }

}
