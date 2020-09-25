import org.springframework.cloud.contract.spec.Contract
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


Contract.make {
  description("should reject withdrawal if balance is insufficient")

  request {
    url("/withdraw")
    method("POST")
    headers {
      contentType(MediaType.APPLICATION_JSON_VALUE)
    }
    body(
      accountIban: $(anyNonBlankString()),
      ownerId: "poor_user",
      amount: $(consumer(regex("[1-9][0-9]*")), producer("2000")),
    )
  }

  response {
    status(HttpStatus.BAD_REQUEST.value())

    headers {
      contentType(MediaType.APPLICATION_JSON_VALUE)
    }

    body(
      message : "Withdrawal rejected",
      errors: [
        [
          scope: "InsufficientBalance",
          description : "User '${fromRequest().body('$.ownerId')}' has not enough money on account with IBAN '${fromRequest().body('$.accountIban')}'"
        ]
      ]
    )
  }

}
