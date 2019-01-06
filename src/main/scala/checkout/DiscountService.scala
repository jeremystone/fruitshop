package checkout

import model.Fruit.{Apple, Orange}
import model.{Fruit, Money}

/**
  * Service that determines discount-related information
  */
trait DiscountService {
  def getDiscount(basket: List[Fruit]): Money
}

/**
  * [[DiscountService]] that provides no discounts
  */
object NoDiscountsDiscountsService extends DiscountService {
  override def getDiscount(basket: List[Fruit]): Money = Money.Zero
}

/**
  * [[DiscountService]] for Step 2 "Simple Offers"
  */
class SimpleOffersDiscountService extends DiscountService {
  override def getDiscount(basket: List[Fruit]): Money = {

    val numApples = basket.count(_ == Apple)
    val freeApples = numApples / 2
    val appleDiscount = Apple.price * freeApples

    val numOranges = basket.count(_ == Orange)
    val freeOranges = numOranges / 3
    val orangeDiscount = Orange.price * freeOranges

    appleDiscount + orangeDiscount
  }
}


