package io.github.rpiotrow.gitbranchforjiratask

class GitBranchNameTests extends munit.FunSuite {
  test("simple") {
    assertEquals(
      GitBranchName("ABC-1 Make a new awesome feature", "-").produce(),
      "ABC-1-make-a-new-awesome-feature")
  }
  test("remove tag") {
    assertEquals(
      GitBranchName("ABC-1 [Back-end] Make a new awesome feature on back-end", "/").produce(),
      "ABC-1/make-a-new-awesome-feature-on-back-end")
  }
  test("remove unnecessary spaces") {
    assertEquals(
      GitBranchName("  ABC-1   Make a new awesome  feature ", "|").produce(),
      "ABC-1|make-a-new-awesome-feature")
  }
  test("remove all non letter or digits") {
    assertEquals(
      GitBranchName("ABC-1 Make a  \"brand\" new awesome feature :) (smile) too", "-<+>-").produce(),
      "ABC-1-<+>-make-a-brand-new-awesome-feature-smile-too")
  }
}
