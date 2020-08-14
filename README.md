# git-branch-for-jira-task

This simple program takes one argument (title of JIRA task name) and creates a ready to use
git command to create a feature branch in a git repository.

## Run locally:

```shell script
$ sbt run
```

## Build native image

```shell script
$ sbt graalvm-native-image:packageBin
```

The native image (executable) can be found in:
```
target/graalvm-native-image/git-branch-for-jira-task
```

Tested on Ubuntu linux.

## Example output

A typical case:
```shell script
$ git-branch-for-jira-task "ABC-1 Make a new awesome feature"
git checkout -b 'feature/ABC-1-make-a-new-awesome-feature'
```

Remove tag after issue identifier:
```shell script
$ git-branch-for-jira-task "ABC-1 [Backend] Make a new awesome feature on back-end"
git checkout -b 'feature/ABC-1-make-a-new-awesome-feature-on-back-end'
```

Remove unnecessary spaces:
```shell script
$ git-branch-for-jira-task "  ABC-1   Make a new awesome  feature "
git checkout -b 'feature/ABC-1-make-a-new-awesome-feature'
```

Use custom issue delimiter and create bugfix branch:
```shell script
$ git-branch-for-jira-task -b -d "-<+>-"  "ABC-1   Make a new awesome  feature for star wars fans "
git checkout -b 'bugfix/ABC-1-<+>-make-a-new-awesome-feature-for-star-wars-fans'
```

## Used libraries

There are no dependencies currently. [MUnit](https://scalameta.org/munit/) was used as testing framework.
