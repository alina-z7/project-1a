package edu.luc.cs.consoleapp

import scala.util.Try
import java.util.Scanner.* 
import org.apache.commons.collections4.queue.CircularFifoQueue

object Main {
  private val LastNWords = 10

  def main(args: Array[String]): Unit = {
    if (args.length > 1) {
      sys.error(
        "usage: ./target/universal/stage/bin/consoleapp [ last_n_words ]"
      )
      sys.exit(2)
    }

    val lastNWords = args.headOption
      .flatMap(arg => Try(arg.toInt).toOption)
      .filter(_ > 0)
      .getOrElse(LastNWords)

    val input =
      new java.util.Scanner(System.in).useDelimiter("(?U)[^\\p{Alpha}0-9']+")
    val queue = new CircularFifoQueue[String](lastNWords)

    while (input.nn.hasNext) {
      val word = input.nn.next()
      queue.add(word) // the oldest item automatically gets evicted
      println(queue)
      if (System.out.nn.checkError()) sys.exit(1)
    }
  }
}
