package com.github.rockjam.itgm7.scala.lists

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {

  def apply[A](as: A*): List[A] =
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))

  def tail[A](l: List[A]): List[A] =
    l match {
      case Nil => throw new Exception("tail of empty list")
      case Cons(_,t) => t
    }

  def length[A](l: List[A]): Int = sys.error("todo")

  def foldLeft[A, B](l: List[A], z: B)(f: (B, A) => B): B = sys.error("todo")

  def map[A, B](l: List[A])(f: A => B): List[B] = l match {
    case Nil => Nil
    case Cons(h, t) => Cons(f(h), map(t)(f))
  }

  def flatMap[A, B](l: List[A])(f: A => List[B]): List[B] = l match {
    case Nil => Nil
    case Cons(h, t) => f(h) Cons(, map(t)(f))
  }
}