package io.github.asakaev

object Fold {

  sealed trait Message extends Product with Serializable
  object Message {
    case object Command extends Message
    case object Event   extends Message
  }

  final case class Aggregate(n: Int)

  List.empty[Message].foldLeft(Aggregate(0)) {
    case (a, Message.Command) => Aggregate(a.n + 1)
    case (a, Message.Event)   => Aggregate(a.n - 1)
  }
}
