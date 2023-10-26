package io.github.asakaev

object Fold {

  sealed trait Message extends Product with Serializable
  object Message {
    case object Command extends Message
    case object Event   extends Message
  }

  final case class Aggregate(count: Int)

  List.empty[Message].foldLeft(Aggregate(0)) {
    case (a, Message.Command) => Aggregate(a.count + 1)
    case (a, Message.Event)   => Aggregate(a.count - 1)
  }
}
