package io.github.asakaev.util

import zio.{Task, ZIO}

object PostgresStore {

  def singleRow[K, V]: Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] = {
      // select for update data by k
      // apply f to data
      // update row

      ZIO.fail(new Throwable("NotImplemented"))
    }
  }

  def joins[K, V]: Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] = {
      // compute lock key as hash(aggregate name + k) -> Long
      // use transaction-level advisory lock on key
      // select data from pg
      // apply f to data
      // update multiple rows

      ZIO.fail(new Throwable("NotImplemented"))
    }
  }
}
