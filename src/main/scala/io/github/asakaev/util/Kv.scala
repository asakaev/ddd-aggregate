package io.github.asakaev.util

import zio.Task

trait Kv[K, V] {
  def put(k: K, v: V): Task[Unit]
  def get(k: K): Task[Option[V]]
}
