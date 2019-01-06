package model

/**
  * Fruit items sold by the shop
  */
sealed trait Fruit {
  def price: Money
}

object Fruit {

  object Apple extends Fruit {
    override def price: Money = Money(0.6)
  }

  object Orange extends Fruit {
    override def price: Money = Money(0.25)
  }
}
