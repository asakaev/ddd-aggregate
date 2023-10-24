package io.github.asakaev.cinema

import io.github.asakaev.cinema.model.{HallId, SeatId, ViewerId}
import io.github.asakaev.util.Store
import zio.Task

object ReserveSeat4 {
  type ReserveSeats = Props => Task[Unit]

  final case class Props(hid: HallId, sids: Set[SeatId], vid: ViewerId)
  final case class Hall(id: HallId, seats: Map[SeatId, ViewerId])

  def reserve(h: Hall, p: Props): Task[Hall] = ???

  def make(halls: Store[HallId, Hall]): ReserveSeats =
    props =>
      halls.update(props.hid) {
        case None    => reserve(Hall(props.hid, Map.empty), props)
        case Some(h) => reserve(h, props)
      }
}
