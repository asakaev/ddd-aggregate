package io.github.asakaev.messenger

import io.github.asakaev.messenger.model.{UserId, Username}
import zio.{Task, ZIO}

object ChangeUsername1 {
  type ChangeUsername = Props => Task[Unit]

  final case class Props(uid: UserId, username: Username)
  final case class User(id: UserId, username: Option[Username])

  def change(u: User, un: Username): User = ???

  def make(users: Users): ChangeUsername =
    props =>
      users.exists(props.username).flatMap {
        case true => ZIO.fail(new Throwable("UsernameTaken"))
        case false =>
          users.update(props.uid) {
            case None       => ZIO.fail(new Throwable("UserNotExists"))
            case Some(user) => ZIO.succeed(change(user, props.username))
          }
      }
}
