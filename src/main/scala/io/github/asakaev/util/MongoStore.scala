package io.github.asakaev.util

import io.github.asakaev.util.Mongo.Document
import zio.Task

object MongoStore {

  def occ[K, V](mongo: Mongo[K, V]): Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] =
      for {
        doc <- mongo.get(k)
        ver = doc.fold(0L)(_.version)
        v <- f(doc.map(_.value))
        _ <- mongo.putIfVersion(k, Document(k, v, ver + 1), ver)
      } yield ()
  }
}
