class FourSquares (threeSquares: ThreeSquares, private val topLeftSquare: Square) {
  private val bottomRightSquare = threeSquares.getBottomRightSquare
  private val bottomLeftSquare = threeSquares.getBottomLeftSquare
  private val topRightSquare = threeSquares.getTopRightSquare

  def checkEdgeRestrictions(): Boolean = {
    checkTopEdge() && checkBottomEdge() && checkLeftEdge() && checkRightEdge()
  }
  private def checkTopEdge(): Boolean = {
    topLeftSquare.b + topRightSquare.a <= 10
  }

  private def checkLeftEdge(): Boolean = {
    topLeftSquare.c + bottomLeftSquare.a <= 10
  }

  private def checkBottomEdge(): Boolean = {
    bottomLeftSquare.d + bottomRightSquare.c <= 10
  }

  private def checkRightEdge(): Boolean = {
    bottomRightSquare.b + topRightSquare.d <= 10
  }

  def getBottomRightSquare: Square = {
    bottomRightSquare
  }

  def getBottomLeftSquare: Square = {
    bottomLeftSquare
  }

  def getTopRightSquare: Square = {
    topRightSquare
  }

  def getTopLeftSquare: Square = {
    topLeftSquare
  }
}
