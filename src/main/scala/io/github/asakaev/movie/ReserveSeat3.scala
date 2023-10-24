package io.github.asakaev.movie

import io.github.asakaev.movie.model.{SeatId, ViewerId}
import io.github.asakaev.util.Store
import zio.{Task, ZIO}

object ReserveSeat3 {
  type ReserveSeats = Props => Task[Unit]

  final case class Props(sids: Set[SeatId], vid: ViewerId)
  final case class Seat(id: SeatId, vid: ViewerId)

  def make(seats: Store[SeatId, Seat]): ReserveSeats =
    props =>
      ZIO.foreachDiscard(props.sids) { sid =>
        seats.update(sid) {
          case Some(_) => ZIO.fail(new Throwable("SeatReserved"))
          case None    => ZIO.succeed(Seat(sid, props.vid))
        }
      }
}
