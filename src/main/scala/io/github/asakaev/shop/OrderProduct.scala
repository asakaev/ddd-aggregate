package io.github.asakaev.shop

import io.github.asakaev.shop.model.{CustomerId, OrderId, ProductId}
import io.github.asakaev.util.Store
import zio.{Task, ZIO}

object OrderProduct {
  type OrderProduct = Props => Task[Unit]

  final case class Props(oid: OrderId, cid: CustomerId, pid: ProductId)
  final case class Order(id: OrderId, cid: CustomerId, pid: ProductId)

  def make(orders: Store[OrderId, Order], warehouse: Warehouse): OrderProduct =
    props =>
      orders.update(props.oid) {
        case Some(_) => ZIO.fail(new Throwable("ProductOrdered"))
        case None    => ZIO.succeed(Order(props.oid, props.cid, props.pid))
      } *> warehouse.ship(props.pid, props.cid)
}
