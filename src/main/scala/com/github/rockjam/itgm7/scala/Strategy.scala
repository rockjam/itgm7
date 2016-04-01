package com.github.rockjam.itgm7.scala


case class VPerson(firstName: Option[String], middleName: Option[String], lastName: Option[String])

object Strategy {

  def isFirstNameValid(person: VPerson) = person.firstName.isDefined

  def isFullNameValid(person: VPerson) = person match {
    case VPerson(first, middle, last) => first.isDefined && middle.isDefined && last.isDefined
  }

  def personCollector(isValid: VPerson => Boolean): (VPerson) => Vector[VPerson] = {
    var validPeople = Vector.empty[VPerson]
    (person: VPerson) => {
      if(isValid(person)) validPeople = validPeople :+ person

      validPeople
    }
  }

  val singleNameValidCollector = personCollector(isFirstNameValid)

  val fullNameValidCollector = personCollector(isFullNameValid)

  val p1 = VPerson(Some("Homer"), Some("J"), Some("Simpson"))
  val p2 = VPerson(Some("Some"), Some("Some"), Some("Some"))

  val p3 = VPerson(Some("Marge"), None, Some("Simpson"))
  val p4 = VPerson(None, None, None)

}
