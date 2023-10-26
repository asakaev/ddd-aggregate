package io.github.asakaev.util

import io.github.asakaev.util.Mongo.Document
import zio.Task

trait Mongo[K, V] {
  def putIfVersion(k: K, v: Document[K, V], version: Long): Task[Unit]
  def get(k: K): Task[Option[Document[K, V]]]
}

object Mongo {
  final case class Document[K, V](_id: K, value: V, version: Long)
}
