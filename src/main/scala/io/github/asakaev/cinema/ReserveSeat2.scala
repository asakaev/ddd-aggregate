package io.github.asakaev.cinema

import io.github.asakaev.cinema.model.{SeatId, ViewerId}
import io.github.asakaev.util.Store
import zio.{Task, ZIO}

object ReserveSeat2 {
  type ReserveSeat = Props => Task[Unit]

  final case class Props(sid: SeatId, vid: ViewerId)
  final case class Seat(id: SeatId, vid: ViewerId)

  def make(seats: Store[SeatId, Seat]): ReserveSeat =
    props =>
      seats.update(props.sid) {
        case Some(_) => ZIO.fail(new Throwable("SeatReserved"))
        case None    => ZIO.succeed(Seat(props.sid, props.vid))
      }
}
