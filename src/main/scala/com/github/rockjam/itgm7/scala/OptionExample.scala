package com.github.rockjam.itgm7.scala

trait PersonDAO {
  def getByEmail(email: String): Option[Person]
}

class Example(val dao: PersonDAO) {
  def extractName(): String = {
    dao.getByEmail("vash_typhoon@mail.ru") map { p =>
      p.name + " " + p.lastName
    } getOrElse "No name"
  }
}
