package io.github.asakaev

object Fold {

  sealed trait Message extends Product with Serializable
  object Message {
    case object Command extends Message
    case object Event   extends Message
  }

  final case class Aggregate(count: Int)

  def useCase(a: Aggregate, m: Message): Aggregate = m match {
    case Message.Command => Aggregate(a.count + 1)
    case Message.Event   => Aggregate(a.count - 1)
  }

  List.empty[Message].foldLeft(Aggregate(0))(useCase)
}
