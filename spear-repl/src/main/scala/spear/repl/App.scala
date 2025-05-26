package spear.repl

import spear.{Context, Name}
import spear.config.Settings

object App {
  def main(args: Array[String]): Unit = {
    val context = new Context(Settings.load("spear.conf", "spear-reference.conf"))

    context.range(1L, 10L).asTable(Name.caseInsensitive("t"))

    val df1 = context.sql(
      """SELECT * FROM (
        |  SELECT id AS key, CAST(RAND(42) * 100 AS INT) AS value FROM t
        |) s
        |WHERE value % 2 = 0
        |ORDER BY value DESC
        |""".stripMargin)

    df1.show()
  }
}
