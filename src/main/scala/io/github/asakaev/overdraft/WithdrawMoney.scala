package io.github.asakaev.overdraft

import io.github.asakaev.overdraft.model.ClientId
import io.github.asakaev.util.Kv
import zio.{Task, ZIO}

object WithdrawMoney {
  type WithdrawMoney = Props => Task[Unit]

  final case class Props(id: ClientId, amount: Int)
  final case class Account(id: ClientId, balance: Int)

  def withdraw(a: Account, amount: Int): Task[Account] = ???

  def make(accounts: Kv[ClientId, Account]): WithdrawMoney =
    props =>
      accounts.get(props.id).flatMap {
        case None => ZIO.fail(new Throwable("AccountNotExists"))
        case Some(account) =>
          for {
            a <- withdraw(account, props.amount)
            _ <- accounts.put(props.id, a)
          } yield ()
      }
}
