package io.github.rpiotrow.gitbranchforjiratask

import java.util.regex.Pattern

class GitBranchName(jiraTaskName: String) {
  private val withIssuePattern = Pattern.compile("([\\s]*[\\w]+-[\\d]+[\\s]*)(.*)")

  private def toKebabCaseWithoutTags(value: String) = {
    value
      .replaceAll("\\[.*\\]", "")
      .replaceAll("[\\s]{2,}", " ")
      .trim()
      .replaceAll(" ", "-")
      .toLowerCase()
  }

  def produce() = {
    val withIssueMatcher = withIssuePattern.matcher(jiraTaskName)
    if (withIssueMatcher.matches()) {
      val issue = withIssueMatcher.group(1)
      val description = withIssueMatcher.group(2)
      issue.trim() + "-" + toKebabCaseWithoutTags(description)
    } else {
      toKebabCaseWithoutTags(jiraTaskName)
    }
  }
}

object GitBranchName {
  def apply(jiraTaskName: String): GitBranchName = new GitBranchName(jiraTaskName)
}
