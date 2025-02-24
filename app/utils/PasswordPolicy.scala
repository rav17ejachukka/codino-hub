package utils

import com.typesafe.config.ConfigFactory

import com.typesafe.config.ConfigFactory

object PasswordPolicy {
  private val config = ConfigFactory.load().getConfig("password.policy")

  val minLength: Int = config.getInt("minLength")
  val maxLength: Int = config.getInt("maxLength")
  val specialCharacters: Set[Char] = config.getString("specialCharacters").toSet
  val requireUppercase: Boolean = config.getBoolean("requireUppercase")
  val requireLowercase: Boolean = config.getBoolean("requireLowercase")
  val requireDigit: Boolean = config.getBoolean("requireDigit")
  val requireSpecialChar: Boolean = config.getBoolean("requireSpecialChar")
}

