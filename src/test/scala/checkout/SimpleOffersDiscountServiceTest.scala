package checkout

import model.Fruit.{Apple, Orange}
import model.Money
import org.scalatest.{MustMatchers, WordSpec}

/**
  * Test [[SimpleOffersDiscountService]]
  */
class SimpleOffersDiscountServiceTest extends WordSpec with MustMatchers {
  val discountService = new SimpleOffersDiscountService

  "SimpleOffersDiscountService" when {
    "fewer than two apples and three oranges" must {
      "result in zero discount" in {
        discountService.getDiscount(List(Apple, Orange, Orange)) mustBe Money.Zero
      }
    }

    "two apples" must {
      "get one apple free" in {
        discountService.getDiscount(List(Apple, Apple)) mustBe Apple.price
      }
    }

    "three apples" must {
      "get one apple free" in {
        discountService.getDiscount(List(Apple, Apple, Apple)) mustBe Apple.price
      }
    }

    "four apples" must {
      "get two apples free" in {
        discountService.getDiscount(List(Apple, Apple, Apple, Apple)) mustBe Apple.price * 2
      }
    }

    "three oranges" must {
      "get one orange free" in {
        discountService.getDiscount(List(Orange, Orange, Orange)) mustBe Orange.price
      }
    }

    "four and five oranges" must {
      "get one orange free" in {
        discountService.getDiscount(List(Orange, Orange, Orange, Orange)) mustBe Orange.price
        discountService.getDiscount(List(Orange, Orange, Orange, Orange, Orange)) mustBe Orange.price
      }
    }

    "six oranges" must {
      "get two oranges free" in {
        discountService.getDiscount(List(Orange, Orange, Orange, Orange, Orange, Orange)) mustBe Orange.price * 2
      }
    }

    "apple and orange discounts apply" must {
      "combine the discounts" in {
        discountService.getDiscount(List(Orange, Apple, Orange, Apple, Orange)) mustBe Orange.price + Apple.price
      }
    }
  }

}
