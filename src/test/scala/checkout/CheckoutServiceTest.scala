package checkout

import model.Fruit.{Apple, Orange}
import model.{Fruit, Money}
import org.scalatest.{MustMatchers, WordSpec}

/**
  * Tests for [[CheckoutService]]
  */
class CheckoutServiceTest extends WordSpec with MustMatchers {

  "CheckoutService" when {
    val checkoutService = new CheckoutService(NoDiscountsDiscountsService)

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

  "CheckoutService" when {
    val poundOffDiscountService = new DiscountService {
      override def getDiscount(basket: List[Fruit]): Money = Money(1.0)
    }

    val checkoutService = new CheckoutService(poundOffDiscountService)

    "offers are available" must {
      "apply discounts" in {
        checkoutService.checkout(List(Orange, Orange, Orange, Orange, Orange)) mustBe
          Money(0.25)
      }

      "never result in negative cost" in {
        checkoutService.checkout(List(Apple)) mustBe Money.Zero
      }
    }
  }

  "CheckoutService" when {
    "using the simple offers" must {
      "apply simple offer discounts" in {
        new CheckoutService(new SimpleOffersDiscountService).checkout(List(Apple, Apple, Apple)) mustBe
          Money(1.20)
      }
    }
  }
}
