package io.github.asakaev

object Fold {
  type UseCase = (Aggregate, Message) => Aggregate

  sealed trait Message extends Product with Serializable
  object Message {
    case object Command extends Message
    case object Event   extends Message
  }

  final case class Aggregate(count: Int)

  val useCase: UseCase =
    (a, _) => Aggregate(a.count + 1)

  List.empty[Message].foldLeft(Aggregate(0))(useCase)
}
