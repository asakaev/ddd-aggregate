package io.github.asakaev.shop

import io.github.asakaev.shop.model.{CustomerId, ProductId}
import zio.Task

trait Warehouse {
  def ship(pid: ProductId, cid: CustomerId): Task[Unit]
}
