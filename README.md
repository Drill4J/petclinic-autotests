**Petclinic simple autotests**

To run all tests use:

```concole
gradlew clean test
```

To run tests by a specific runner:

```concole
gradlew clean :junit4:test
gradlew clean :junit5:test
gradlew clean :testng:test -Pstandalone -Pmcr
```