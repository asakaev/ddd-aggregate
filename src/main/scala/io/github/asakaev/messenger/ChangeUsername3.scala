package io.github.asakaev.messenger

import io.github.asakaev.messenger.model.{UserId, Username}
import io.github.asakaev.util.Store
import zio.{Task, ZIO}

object ChangeUsername3 {
  type ChangeUsername = Props => Task[Unit]

  final case class Props(uid: UserId, username: Username)
  final case class TakenUsername(id: Username, uid: UserId)

  def change(tu: TakenUsername, p: Props): TakenUsername = ???

  def make(usernames: Store[Username, TakenUsername]): ChangeUsername =
    props =>
      usernames.update(props.username) {
        case None           => ZIO.succeed(TakenUsername(props.username, props.uid))
        case Some(username) => ZIO.succeed(change(username, props))
      }
}
