package io.github.rpiotrow.gitbranchforjiratask

object Main extends App {

  sealed trait BranchType { def prefix: String }
  case object FeatureBranch extends BranchType { override val prefix = "feature" }
  case object BugfixBranch extends BranchType { override val prefix = "bugfix" }

  case class Config(
    jiraTaskName: String = "",
    branchType: BranchType = FeatureBranch,
    issueDelimiter: String = "-"
  )

  val parser = new scopt.OptionParser[Config]("git-branch-for-jira-task") {
    head("Prints a git command that creates a feature/bugfix branch for Jira task")

    help("help").text("prints this usage text")

    arg[String]("<jiraTaskName>")
      .text("Jira task name for which git branch needs to be created")
      .action((x, c) => c.copy(jiraTaskName = x))

    opt[String]('d', "issueDelimiter")
      .action((x, c) => c.copy(issueDelimiter = x))
      .text("issue delimiter ('-' if not provided)")

    opt[Unit]('f', "feature")
      .action((_, c) => c.copy(branchType = FeatureBranch))
      .text("create feature branch")

    opt[Unit]('b', "bugfix")
      .action((_, c) => c.copy(branchType = BugfixBranch))
      .text("create bugfix branch")
  }

  parser.parse(args, Config()) match {
    case Some(config) =>
      println(s"git checkout -b '${config.branchType.prefix}/${GitBranchName(config.jiraTaskName, config.issueDelimiter).produce()}'")
    case None =>
      // arguments are bad, error message have been displayed
  }

}
