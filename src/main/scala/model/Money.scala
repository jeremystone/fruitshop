package model

/**
  * Represents money
  *
  * @param amountPounds Pounds with Pence as a decimal
  */
case class Money(amountPounds: BigDecimal) {
  /**
    * Adds money
    *
    * @param that money to add
    * @return the total money
    */
  def +(that: Money): Money = Money(this.amountPounds + that.amountPounds)

  /**
    * Adds money
    *
    * @param that money to add
    * @return the total money
    */
  def -(that: Money): Money = Money(this.amountPounds - that.amountPounds)


  /**
    * Multiplies money by a factor
    *
    * @param multiple factor to multiply by
    * @return the total money
    */
  def *(multiple: Int): Money = Money(this.amountPounds * multiple)
}

object Money {
  val Zero = Money(0.0)

  implicit val ordering: Ordering[Money] = Ordering.by(_.amountPounds)
}