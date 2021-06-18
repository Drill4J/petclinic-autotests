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
gradlew clean :cucumber-junit:test -Dcucumber.filter.tags=@standalone -DtestFramework=cucumber
gradlew clean :cucumber-testng:test -Dcucumber.filter.tags=@standalone -DtestFramework=cucumber
```