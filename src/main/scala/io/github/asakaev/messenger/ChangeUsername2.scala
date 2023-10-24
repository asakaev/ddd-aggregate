package io.github.asakaev.messenger

import io.github.asakaev.messenger.model.{MessengerId, UserId, Username}
import io.github.asakaev.util.Store
import zio.{Task, ZIO}

object ChangeUsername2 {
  type ChangeUsername = Props => Task[Unit]

  final case class Props(uid: UserId, username: Username)
  final case class User(id: UserId, username: Option[Username])
  final case class Messenger(id: MessengerId, users: Map[UserId, User])

  def changeUsername(m: Messenger, p: Props): Task[Messenger] = ???

  def make(messengers: Store[MessengerId, Messenger]): ChangeUsername =
    props =>
      messengers.update(MessengerId.default) {
        case None    => ZIO.fail(new Throwable("InternalError"))
        case Some(m) => changeUsername(m, props)
      }
}
