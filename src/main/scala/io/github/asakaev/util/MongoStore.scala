package io.github.asakaev.util

import zio.{Task, ZIO}

object MongoStore {
  final case class Document[K, V](_id: K, value: V, version: Long)

  val live: Store[Int, String] = new Store[Int, String] {
    def update(k: Int)(f: Option[String] => Task[String]): Task[Unit] = {
      // read data with version by key
      // apply f to data value
      // increment version
      // conditional write on version back
      ZIO.fail(new Throwable("NotImplemented"))
    }
  }
}
