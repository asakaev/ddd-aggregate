package io.github.asakaev.movie

import io.github.asakaev.movie.model.{SeatId, ViewerId}
import io.github.asakaev.util.Kv
import zio.{Task, ZIO}

object ReserveSeat1 {
  type ReserveSeat = Props => Task[Unit]

  final case class Props(sid: SeatId, vid: ViewerId)
  final case class Seat(id: SeatId, vid: ViewerId)

  def make(seats: Kv[SeatId, Seat]): ReserveSeat =
    props =>
      seats.get(props.sid).flatMap {
        case Some(_) => ZIO.fail(new Throwable("SeatReserved"))
        case None    => seats.put(props.sid, Seat(props.sid, props.vid))
      }
}
