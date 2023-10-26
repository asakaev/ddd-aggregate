package io.github.asakaev.util

import zio.{Task, ZIO}

object MongoStore {

  def occ[K, V]: Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] = {
      // find { _id, value, version } by key
      // apply f to value
      // increment version
      // conditional update on old version
      // retry if version changed

      ZIO.fail(new NotImplementedError)
    }
  }
}
