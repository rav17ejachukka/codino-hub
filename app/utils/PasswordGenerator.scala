package utils

import java.security.SecureRandom
import scala.util.Random

object PasswordGenerator {
  private val random = new SecureRandom()
  private val uppercaseLetters = ('A' to 'Z').toList
  private val lowercaseLetters = ('a' to 'z').toList
  private val digits = ('0' to '9').toList
  private val specialChars = PasswordPolicy.specialCharacters.toList
  private val allChars = uppercaseLetters ++ lowercaseLetters ++ digits ++ specialChars

  def generatePassword(length: Int = PasswordPolicy.minLength): String = {
    require(length >= PasswordPolicy.minLength && length <= PasswordPolicy.maxLength,
      s"Password length must be between ${PasswordPolicy.minLength} and ${PasswordPolicy.maxLength}")

    var password = List.empty[Char]

    if (PasswordPolicy.requireUppercase) password ::= randomChar(uppercaseLetters)
    if (PasswordPolicy.requireLowercase) password ::= randomChar(lowercaseLetters)
    if (PasswordPolicy.requireDigit) password ::= randomChar(digits)
    if (PasswordPolicy.requireSpecialChar) password ::= randomChar(specialChars)

    password ++= (1 to (length - password.length)).map(_ => randomChar(allChars))
    Random.shuffle(password).mkString
  }

  private def randomChar(chars: List[Char]): Char = chars(random.nextInt(chars.length))
}


