import hevs.graphics.FunGraphics
import java.awt.color
import scala.util.Random
import scala.collection.mutable.ArrayBuffer
import java.io.{BufferedReader, InputStreamReader}

class MyTuple() {
  var x: Int = 0
  var y: Int = 0
}

class Snake() {
  var position: Array[MyTuple] = Array.ofDim[MyTuple](4)
  position(0) = new MyTuple()
  position(0).y = 0
  position(1) = new MyTuple()
  position(1).y = 1
  position(2) = new MyTuple()
  position(2).y = 2
  position(3) = new MyTuple()
  position(3).y = 3

  def readChar(): Char = {
    System.out.println("Press 'a' to turn left, 'w' to turn up, 's' to turn down and 'd' to turn right : ")
    val stdin = new BufferedReader(new InputStreamReader(System.in))
    try stdin.readLine.charAt(0)
    catch {
      case ex: Exception =>
        '0'
    }
  }

  def changeState(arrow: Char): Unit = {
    // ASCII : up = w; down: y; right: s; left: a
    var newPosition: Array[MyTuple] = Array.ofDim[MyTuple](4)
    for (i <- newPosition.indices) {
      newPosition(i) = new MyTuple()
      newPosition(i).x = position(i).x
      newPosition(i).y = position(i).y
    }

    if (arrow == 'w') {
      newPosition(0).y = newPosition(0).y - 1
    }
    if (arrow == 's') {
      newPosition(0).y = newPosition(0).y + 1
    }
    if (arrow == 'd') {
      newPosition(0).x = newPosition(0).x + 1
    }
    if (arrow == 'a') {
      newPosition(0).x = newPosition(0).x - 1
    }
    for (i <- 1 until newPosition.length) {
      newPosition(i).x = position(i - 1).x
      newPosition(i).y = position(i - 1).y
    }
    for (i <- position.indices) {
      position(i).x = newPosition(i).x
      position(i).y = newPosition(i).y
    }
  }
}

//// exemple pour dessiner serpent:
//import hevs.graphics.FunGraphics
//import java.awt.Color
//object SimpleShapes extends App{
//  val s = new FunGraphics(300, 300, "Test drawing simple shapes")
//  // Draw simple shapes
//  s.setColor(Color.black)
//  s.drawCircle(100, 100, 10)
//  s.setColor(Color.blue)
//  s.drawFillRect(20, 20, 10, 10)
//}

class Board(x : Int, y : Int) {
  var snake: Snake = new Snake()
  def defineBoard() : Array[Array[Char]] = {
    var board : Array[Array[Char]] = Array.ofDim[Char](x + 2, y + 2)  // creates board according to the values of 'x' and 'y' and adds space for bounds
    var w = board.length
    var h = board(0).length
    for (a <- 0 until w) {
      for (b <- 0 until h) {
        if ((a == 0) || (b == 0) || (a == w - 1) || (b == h - 1)) { // creates dead zones for snake
          board(a)(b) = '*'
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

 def moveSnake() : Unit = {
    var c: Char = 's'
    while (c == 'w' | c == 'd' | c == 'a' | c == 's') {
      c = snake.readChar()
      snake.changeState(c)
      print(s"New snake position:")
      for (i <- snake.position.indices) {
        print(s"(${snake.position(i).x};${snake.position(i).y}); ")
      }
      println("")
    }
  }


  def createApple(spawnApple : Boolean, board : Array[Array[Char]]) : Array[Array[Char]] = {
    if (spawnApple) {
      var position : Array[Array[Char]] = board
      var spawn : Random = new Random()
      var randomX : Int = spawn.between(1, x - 1)
      var randomY : Int = spawn.between(1, y - 1)
      while (position(randomX)(randomY) != 0) {
        randomX = spawn.between(1, x - 1)
        randomY = spawn.between(1, y - 1)
      }
      position(randomX)(randomY) = '#'
      position
    } else board
  }
}

object SnakeGame extends App {
  var spawnApple : Boolean = true
  println("Please, enter the value for board's width and height: (the value have to be between 4 and 12)")
  var sizeOfBoard : Int = Input.readInt()
  val game : Board = new Board(sizeOfBoard, sizeOfBoard)
  val board : Array[Array[Char]] = game.defineBoard()
  while(sizeOfBoard < 4 || sizeOfBoard > 12) {
  println("Please, enter a valid value")
  sizeOfBoard = Input.readInt()
  }
  game.moveSnake()

  var updatedBoard : Array[Array[Char]] = game.createApple(spawnApple, board)

  for(i <- updatedBoard.indices){
    for(j <- updatedBoard(0).indices){
      print(updatedBoard(i)(j))
    }
    println()
  }
}