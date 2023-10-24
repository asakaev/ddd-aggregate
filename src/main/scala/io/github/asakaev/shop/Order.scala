package io.github.asakaev.shop

import io.github.asakaev.shop.Order.Status
import io.github.asakaev.shop.model.{CustomerId, OrderId, ProductId}

final case class Order(id: OrderId, cid: CustomerId, pid: ProductId, s: Status)
object Order {
  sealed trait Status extends Product with Serializable
  object Status {
    case object Placed     extends Status
    case object Shipped    extends Status
    case object OutOfStock extends Status
  }
}
