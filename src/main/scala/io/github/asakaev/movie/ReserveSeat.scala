package io.github.asakaev.movie

import io.github.asakaev.util.Kv
import zio.{Task, ZIO}

import java.util.UUID

object ReserveSeat {
  type ReserveSeat = Props => Task[Unit]

  final case class Props(sid: SeatId, vid: ViewerId)
  final case class Seat(id: SeatId, vid: ViewerId)

  final case class ViewerId(value: UUID) extends AnyVal
  final case class SeatId(value: UUID)   extends AnyVal

  def make(seats: Kv[SeatId, Seat]): ReserveSeat =
    props =>
      seats.get(props.sid).flatMap {
        case Some(_) => ZIO.fail(new Throwable("SeatReserved"))
        case None    => seats.put(props.sid, Seat(props.sid, props.vid))
      }

}
