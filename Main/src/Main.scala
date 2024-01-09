import hevs.graphics.FunGraphics
import java.awt.color
import scala.util.Random
class Board(x : Int, y : Int) {
  // create a function that will create borders(-2) depending on the size
  def defineBoard() : Array[Array[Int]] = {
    var board : Array[Array[Int]] = Array.ofDim[Int](x + 2, y + 2)  // creates board according to the values of 'x' and 'y' and adds space for bounds
    var w = board.length
    var h = board(0).length
    for (a <- 0 until w) {
      for (b <- 0 until h) {
        if ((a == 0) || (b == 0) || (a == w - 1) || (b == h - 1)) { // creates dead zones for snake
          board(a)(b) = -2
          print(board(a)(b))
        } else {
          board(a)(b) = 0
          print(board(a)(b))
        }
      }
      println()
    }
    board
  }

  def createApple(spawnApple : Boolean, board : Array[Array[Int]]) : Array[Array[Int]] = {
    if (!spawnApple) {
      var position : Array[Array[Int]] = board
      var spawn : Random = new Random()
      var X : Int = spawn.between(1, x - 1)
      var Y : Int = spawn.between(1, y - 1)
      while (position(X)(Y) != 0) {
        X = spawn.between(1, x - 1)
        Y = spawn.between(1, y - 1)
      }
      position(X)(Y) = -1
      println(position(X)(Y))
      position
    } else board
  }
}

object SnakeGame extends App {
  var createApple : Boolean = true
  println("Please, enter the value for board's width and height: (the value have to be between 4 and 12)")
  var sizeOfBoard : Int = Input.readInt()
  val game : Board = new Board(sizeOfBoard, sizeOfBoard)
  val board : Array[Array[Int]] = game.defineBoard()
  while(sizeOfBoard < 4 || sizeOfBoard > 12) {
  println("Please, enter a valid value")
  sizeOfBoard = Input.readInt()
  }
  var updatedBoard : Array[Array[Int]] = game.createApple(createApple, board)

  for(i <- 0 until updatedBoard.length){
    for(j <-0 until updatedBoard(0).length){
      print(updatedBoard(i)(j))
    }
    println()
  }
}