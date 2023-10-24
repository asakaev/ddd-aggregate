package io.github.asakaev.util

import zio.Task

trait Store[K, V] {

  /** Atomic read–modify–write
    * OCC, mutex (lock)
    */
  def update(k: K)(f: Option[V] => Task[V]): Task[Unit]
}
