package checkout

import model.{Fruit, Money}

import scala.Ordering.Implicits._

/**
  * Services the checkout process
  */
class CheckoutService(discountService: DiscountService) {
  /**
    * Given a list of fruit scanned at the till, determines its total cost.
    *
    * @param scannedFruit fruit from the scanning process
    * @return the total cost
    */
  def checkout(scannedFruit: List[Fruit]): Money = {
    val totalPrice = scannedFruit.map(_.price).fold(Money.Zero)(_ + _)

    val discount = discountService.getDiscount(scannedFruit)

    // Don't discount beyond 0p
    totalPrice - discount max Money.Zero
  }
}
