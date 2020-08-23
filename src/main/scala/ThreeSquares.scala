class ThreeSquares (bottomSquares: TwoSquares, private val topRightSquare: Square) {
  private val bottomLeftSquare = bottomSquares.getBottomLeftSquare
  private val bottomRightSquare = bottomSquares.getBottomRightSquare

  //sum in the middle
  def getSum(squareToAdd: Square): Int = {
    bottomLeftSquare.b + bottomRightSquare.a + topRightSquare.c + squareToAdd.d
  }

  def containsSquare(squareToCheck: Square): Boolean = {
    squareToCheck.equals(bottomRightSquare) || squareToCheck.equals(bottomLeftSquare) || squareToCheck.equals(topRightSquare)
  }

  def getTopRightSquare: Square = {
    topRightSquare
  }

  def getBottomLeftSquare: Square = {
    bottomLeftSquare
  }

  def getBottomRightSquare: Square = {
    bottomRightSquare
  }

}
