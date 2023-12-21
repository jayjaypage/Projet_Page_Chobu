import hevs.graphics.FunGraphics

import java.awt.color
import scala.collection.mutable.ArrayBuffer
class Board(x: Int, y: Int) {
  var length : Int = 3
  var snake : ArrayBuffer[(Int, Int)] = ArrayBuffer((x/2+1,y/2),(x/2,y/2),(x/2-1,y/2))
  var apple : ArrayBuffer[(Int, Int)] = ArrayBuffer((0,0))
  // create a function that will create borders(-2) depending on the size
}

object SnakeGame extends App {
val field : Board = new Board(10, 10)
}