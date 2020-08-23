/*
a - top left corner, b - top right corner,
c - bottom left corner, d - bottom right corner
 */
class Square(val a:Int, val b: Int, val c: Int, val d: Int, val id: Int ) {

  def printSquare() {
    println(a + " " + b + " " + c + " " + d)
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[Square]

  override def equals(other: Any): Boolean = other match {
    case that: Square =>
      (that canEqual this) &&
        id == that.id
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(id)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
