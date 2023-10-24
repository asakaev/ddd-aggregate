package io.github.asakaev.overbooking

import io.github.asakaev.overbooking.model.{PassengerId, SeatId}
import io.github.asakaev.util.Kv
import zio.{Task, ZIO}

object BuyTicket {
  type BuyTicket = Props => Task[Unit]

  final case class Props(pid: PassengerId, sid: SeatId)
  final case class Seat(id: SeatId, pid: PassengerId)

  def make(seats: Kv[SeatId, Seat]): BuyTicket =
    props =>
      seats.get(props.sid).flatMap {
        case Some(_) => ZIO.fail(new Throwable("SeatReserved"))
        case None    => seats.put(props.sid, Seat(props.sid, props.pid))
      }
}
