class TwoSquares (private val bottomRightSquare: Square, private val bottomLeftSquare: Square) {

  //sum in the middle
  def getSum(squareToAdd: Square): Int = {
    bottomLeftSquare.b + bottomRightSquare.a + squareToAdd.c;
  }

  def containsSquare(squareToCheck: Square): Boolean = {
    squareToCheck.equals(bottomRightSquare) || squareToCheck.equals(bottomLeftSquare)
  }

  def getBottomRightSquare: Square = {
    bottomRightSquare
  }

  def getBottomLeftSquare: Square = {
    bottomLeftSquare
  }
}
