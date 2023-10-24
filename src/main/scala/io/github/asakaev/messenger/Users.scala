package io.github.asakaev.messenger

import io.github.asakaev.messenger.ChangeUsername1.User
import io.github.asakaev.messenger.model.{UserId, Username}
import zio.Task

trait Users {
  def exists(u: Username): Task[Boolean]
  def update(id: UserId)(f: Option[User] => Task[User]): Task[Unit]
}
