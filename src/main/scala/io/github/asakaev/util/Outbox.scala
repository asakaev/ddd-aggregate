package io.github.asakaev.util

import zio.Task

trait Outbox[K, V, A] {

  /** Atomic write to storage and message broker
    */
  def update(k: K)(f: Option[V] => Task[(V, List[A])]): Task[Unit]
}
