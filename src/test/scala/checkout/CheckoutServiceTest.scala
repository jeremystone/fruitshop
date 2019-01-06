package checkout

import model.Fruit.{Apple, Orange}
import model.Money
import org.scalatest.{MustMatchers, WordSpec}

/**
  * Tests for [[CheckoutService]]
  */
class CheckoutServiceTest extends WordSpec with MustMatchers {

  val checkoutService = new CheckoutService

  "CheckoutService" when {
    "given an empty list of scanned items" must {
      "calculate zero cost" in {
        checkoutService.checkout(Nil) mustBe
          Money.Zero
      }
    }

    "given a single piece of fruit" must {
      "calculate the total cost as the price of that piece of fruit" in {
        List(Apple, Orange).foreach { piece =>
          checkoutService.checkout(List(piece)) mustBe piece.price
        }
      }
    }

    "given a list of scanned items" must {
      "calculate the total cost" in {
        checkoutService.checkout(List(Apple, Apple, Orange, Apple)) mustBe
          Money(2.05)
      }
    }
  }
}
