package io.github.asakaev.util

import zio.{Task, ZIO}

object MongoStore {
  final case class Document[K, V](_id: K, value: V, version: Long)

  def occ[K, V]: Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] = {
      // query data with version by key
      // apply f to data value
      // increment version
      // conditional update on version back
      // retry if version changed

      ZIO.fail(new Throwable("NotImplemented"))
    }
  }
}
