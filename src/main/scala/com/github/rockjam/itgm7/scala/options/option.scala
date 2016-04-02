package com.github.rockjam.itgm7.scala.options

object Option {
  def apply[A](x:A): Option[A] = if (x == null) None else Some(x)
  def empty[A]: Option[A] = None
}

sealed trait Option[+A] {
  def isEmpty: Boolean
  def getOrElse[B >: A](default: => B): B
  def map[B](f: A => B): Option[B]
  def flatMap[B](f: A => Option[B]): Option[B]
  def filter(p: A => Boolean): Option[A]
}

final case class Some[A](private val value: A) extends Option[A] {
  def isEmpty: Boolean = false
  def getOrElse[B >: A](default: => B): B = value
  def map[B](f: (A) => B): Option[B] = Option(f(value))
  def flatMap[B](f: (A) => Option[B]): Option[B] = f(value)
  def filter(p: (A) => Boolean): Option[A] = if (p(value)) this else None
}

case object None extends Option[Nothing] {
  def isEmpty: Boolean = true
  def flatMap[B](f: (Nothing) => Option[B]): Option[B] = None
  def filter(p: (Nothing) => Boolean): Option[Nothing] = None
  def getOrElse[B >: Nothing](default: => B): B = default
  def map[B](f: (Nothing) => B): Option[B] = None
}

object test {
  val noNumber: Option[Int] = Option.empty[Int]

  val firstNumber: Option[Int] = Option(23)
  val secondNumber: Option[Int] = Some(45)

  def isEven(i: Int): Boolean = i % 2 == 0
  def isOdd(i: Int): Boolean = i % 2 != 0

  val result =
    firstNumber map { i =>
      i * 3
    } flatMap { i =>
      secondNumber map (j => i + j)
    } filter isOdd
//  > result = Some(114)
//  > result = None

  val result1 = for {
    i <- firstNumber
    j <- secondNumber
  } yield i * 3 + j

}