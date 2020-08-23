import scala.collection.mutable.ListBuffer

class Solver(var numbers: ListBuffer[Square]) {
  /*
  List of squares, that consist of 4 different squares, give sum 10 in the middle
  and their top, left, bottom and right edges have sum not greater than 10
   */
  private var fourSquaresList = ListBuffer[FourSquares]()
  private val result = scala.collection.mutable.HashMap[Integer, Square]()

  def solve(): Unit = {
    findSquaresWithTenInTheMiddle()
    fourSquaresList = fourSquaresList.filter(fourSquares => fourSquares.checkEdgeRestrictions())
    calculateAndPrintResult()
  }

  private def findSquaresWithTenInTheMiddle(): Unit = {
    val twoSquaresList = new ListBuffer[TwoSquares]()
    val threeSquaresList = new ListBuffer[ThreeSquares]()

    numbers.foreach(getBottomRightSquare => numbers.filter(getBottomLeftSquare => getBottomLeftSquare.b + getBottomRightSquare.a <= 10
      && ! getBottomLeftSquare.equals(getBottomRightSquare)).foreach(getBottomLeftSquare => twoSquaresList.addOne(new TwoSquares(getBottomRightSquare, getBottomLeftSquare))))

    twoSquaresList.foreach(bottomSquares => numbers.filter(getTopRightSquare => bottomSquares.getSum(getTopRightSquare) <= 10
      && ! bottomSquares.containsSquare(getTopRightSquare))
      .foreach(getTopRightSquare => threeSquaresList.addOne(new ThreeSquares(bottomSquares, getTopRightSquare))))

    threeSquaresList.foreach(threeSquares => numbers.filter(getTopLeftSquare => threeSquares.getSum(getTopLeftSquare) == 10
      && ! threeSquares.containsSquare(getTopLeftSquare)).foreach(getTopLeftSquare => fourSquaresList.addOne(new FourSquares(threeSquares, getTopLeftSquare))))
  }

  private def calculateAndPrintResult(): Unit = {
    fourSquaresList.foreach(middleSquare => findAllResultsWithFixedMiddle(middleSquare))
  }
  private def findAllResultsWithFixedMiddle(middleSquare: FourSquares): Unit = {
    result.clear()
    result.put(3, middleSquare.getTopLeftSquare)
    result.put(4, middleSquare.getTopRightSquare)
    result.put(7, middleSquare.getBottomLeftSquare)
    result.put(8, middleSquare.getBottomRightSquare)

    fourSquaresList.foreach(topSquare => addTop(topSquare))
  }

  private def addTop(topSquare: FourSquares): Unit = {
    if(!topSquare.getBottomLeftSquare.equals(result.getOrElse(3, null)) || !topSquare.getBottomRightSquare.equals(result.getOrElse(4, null)))
      return
    if(result.values.exists(_.equals(topSquare.getTopLeftSquare)) || result.values.exists(_.equals(topSquare.getTopRightSquare)))
      return
    result.put(0, topSquare.getTopLeftSquare)
    result.put(1, topSquare.getTopRightSquare)

    fourSquaresList.foreach(bottomSquare => addBottom(bottomSquare))

    result.remove(0)
    result.remove(1)
  }

  private def addBottom(bottomSquare: FourSquares): Unit = {
    if (!bottomSquare.getTopLeftSquare.equals(result.getOrElse(7, null)) || !bottomSquare.getTopRightSquare.equals(result.getOrElse(8, null)))
    return
    if(result.values.exists(_.equals(bottomSquare.getBottomRightSquare)) || result.values.exists(_.equals(bottomSquare.getBottomLeftSquare)))
    return
    result.put(10, bottomSquare.getBottomLeftSquare)
    result.put(11, bottomSquare.getBottomRightSquare)

    fourSquaresList.foreach(leftSquare => addLeft(leftSquare))

    result.remove(10)
    result.remove(11)
  }

  private def addLeft(leftSquare: FourSquares): Unit = {
    if (!leftSquare.getTopRightSquare.equals(result.getOrElse(3, null)) || !leftSquare.getBottomRightSquare.equals(result.getOrElse(7, null)))
    return
    if (result.values.exists(_.equals(leftSquare.getTopLeftSquare)) || result.values.exists(_.equals(leftSquare.getBottomLeftSquare)))
    return
    if (leftSquare.getTopLeftSquare.b + result(3).a + result(0).c > 10)
    return
    if (leftSquare.getBottomLeftSquare.d + result(7).c + result(10).a > 10)
    return
    result.put(2, leftSquare.getTopLeftSquare)
    result.put(6, leftSquare.getBottomLeftSquare)

    fourSquaresList.foreach(rightSquare => addRightAndPrint(rightSquare))

    result.remove(2)
    result.remove(6)
  }


  private def addRightAndPrint(fourSquares: FourSquares): Unit = {
    if (!fourSquares.getTopLeftSquare.equals(result.getOrElse(4, null)) || !fourSquares.getBottomLeftSquare.equals(result.getOrElse(8, null)))
    return
    if (result.values.exists(_.equals(fourSquares.getTopRightSquare) || result.values.exists(_.equals(fourSquares.getBottomRightSquare))))
    return
    if (fourSquares.getTopRightSquare.a + result(4).b + result(1).d > 10)
      return
    if (fourSquares.getBottomRightSquare.c + result(8).d + result(11).b > 10)
      return
    result.put(5, fourSquares.getTopRightSquare)
    result.put(9, fourSquares.getBottomRightSquare)

    printResult()

    result.remove(5)
    result.remove(9)
  }

  private def printResult(): Unit = {
    println()
    for(i <- 0 to 11)
    result(i).printSquare()
  }

}
