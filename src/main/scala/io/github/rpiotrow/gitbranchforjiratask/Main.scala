package io.github.rpiotrow.gitbranchforjiratask

object Main extends App {
  if (args.length != 1) {
    println("Please provide jira task title")
    System.exit(1)
  }

  println("git checkout -b 'feature/" + GitBranchName(args(0)).produce() + "'")
}
