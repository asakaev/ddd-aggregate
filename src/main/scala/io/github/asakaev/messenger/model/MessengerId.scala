package io.github.asakaev.messenger.model

final case class MessengerId(value: String) extends AnyVal
object MessengerId {
  val default: MessengerId = MessengerId("default")
}
