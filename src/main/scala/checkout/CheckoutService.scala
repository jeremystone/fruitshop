package checkout

import model.{Fruit, Money}

/**
  * Services the checkout process
  */
class CheckoutService {
  /**
    * Given a list of fruit scanned at the till, determines its total cost.
    *
    * @param scannedFruit fruit from the scanning process
    * @return the total cost
    */
  def checkout(scannedFruit: List[Fruit]): Money =
    scannedFruit.map(_.price).fold(Money.Zero)(_ + _)
}
