package io.github.asakaev.movie

import io.github.asakaev.movie.model.{HallId, SeatId, ViewerId}
import io.github.asakaev.util.Store
import zio.{Task, ZIO}

object SeatAvailableHandler {
  type SeatAvailableHandler = SeatAvailable => Task[Unit]

  final case class SeatAvailable(hid: HallId, sid: SeatId)
  final case class Hall(id: HallId, seats: Map[SeatId, ViewerId])

  def free(h: Hall, sid: SeatId): Hall = ???

  def make(halls: Store[HallId, Hall]): SeatAvailableHandler =
    event =>
      halls.update(event.hid) {
        case None    => ZIO.succeed(Hall(event.hid, Map.empty))
        case Some(h) => ZIO.succeed(free(h, event.sid))
      }
}
