import java.util.Scanner

import scala.collection.mutable.ListBuffer

object Main {
  private var input = ListBuffer[Square]()

  def main(args: Array[String]): Unit = {
    readNumbers()

    val solver = new Solver(input)
    solver.solve()
  }

  private def readNumbers(): Unit = {
    val in = new Scanner(System.in)
    for(i <- 0 to 11) {
      input.addOne(new Square(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), i))
    }
  }
}
