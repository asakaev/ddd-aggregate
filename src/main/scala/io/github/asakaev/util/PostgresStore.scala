package io.github.asakaev.util

import zio.{Task, ZIO}

object PostgresStore {

  def oneRow[K, V]: Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] = {
      // select for update row by k
      // apply f
      // update row

      ZIO.fail(new NotImplementedError)
    }
  }

  def manyRows[K, V]: Store[K, V] = new Store[K, V] {
    def update(k: K)(f: Option[V] => Task[V]): Task[Unit] = {
      // hash(aggregate_name + k)
      // use transaction-level advisory lock on hash
      // select data from multiple tables
      // apply f
      // update multiple tables

      ZIO.fail(new NotImplementedError)
    }
  }
}
