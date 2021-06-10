# git-branch-for-jira-task

This simple program takes one argument (title of JIRA task name) and creates a ready to use
git command to create a feature branch in a git repository.

## Run locally:

```shell script
$ sbt run
```

## Build native image

```shell script
$ sbt nativeLink
```

The native image (executable) can be found in:
```
target/scala-2.13/git-branch-for-jira-task-out
```

Tested on Ubuntu linux.

## Example output

A typical case:
```shell script
$ git-branch-for-jira-task-out "ABC-1 Make a new awesome feature"
git checkout -b 'feature/ABC-1-make-a-new-awesome-feature'
```

Remove tag after issue identifier:
```shell script
$ git-branch-for-jira-task-out "ABC-1 [Backend] Make a new awesome feature on back-end"
git checkout -b 'feature/ABC-1-make-a-new-awesome-feature-on-back-end'
```

Remove unnecessary spaces:
```shell script
$ git-branch-for-jira-task-out "  ABC-1   Make a new awesome  feature "
git checkout -b 'feature/ABC-1-make-a-new-awesome-feature'
```

Use custom issue delimiter and create bugfix branch:
```shell script
$ git-branch-for-jira-task-out -b -d "-<+>-"  "ABC-1   Make a new awesome  feature for star wars fans "
git checkout -b 'bugfix/ABC-1-<+>-make-a-new-awesome-feature-for-star-wars-fans'
```

## Used libraries

[scopt](https://github.com/scopt/scopt) is used to parse command line options.

[MUnit](https://scalameta.org/munit/) is used as testing framework.
