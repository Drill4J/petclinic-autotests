**Petclinic simple autotests**

To run all tests use:

```concole
gradlew clean test
```

To run tests by specific runner:
```concole
gradlew clean :junit4:test
gradlew clean :junit5:test
gradlew clean :testNG:test -Psingle -Pmulti
```