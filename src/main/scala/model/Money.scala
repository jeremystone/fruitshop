package model

/**
  * Represents money
  *
  * @param amountPounds Pounds with Pence as a decimal
  */
case class Money(amountPounds: Double) {
  /**
    * Adds money
    * @param that money to add
    * @return the total money
    */
  def +(that: Money): Money = Money(this.amountPounds + that.amountPounds)
}


object Money {
  val Zero = Money(0.0)
}