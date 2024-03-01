## Review Questions:

### a) Identify a couple of examples that use AssertJ expressive methods chaining.
#### in A/ test:
```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
```
#### in E/ test:
```java

assertThat(response.getBody()).extracting(Employee::getName).containsExactly("bob", "alex");
```
Chaining is here used to make complex assertions with a more human-readable syntax.


### b) Identify an example in which you mock the behavior of the repository (and avoid involving a database).

#### In B/ test, employee's repository is mocked, and its behaviour defined in several instances, for example:
```java
Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
```


### c) What is the difference between standard @Mock and @MockBean?

#### The @Mock tag creates a 'fake' object of the desired class, simulating its behaviour according to the developer desires in the Test context, not being known/used by the rest of the Spring Application. On the contrary, @MockBean not only simulates the real Objects behaviour as @Mock does on the @Test context, but is also known/used by the whole Spring Application, as a Component. It will replace the already defined components of the mocked class and, if there is none, will add it to the application context.


### d) What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

#### This file, similarly to application.properties, aims to define global variables/properties that are of importance for the application lifecycle, f.e, to provide environment variables a value. 
#### It is used on the integration-test lifecycle of maven, defining conditions/attributions and overriding conflitual normal-cycle definitions, being used ONLY throughout the integration-testing.


### e) the sample project demonstrates three test strategies to assess an API (C, D and E) developed with SpringBoot. Which are the main/key differences?

#### C uses @WebMvcTest. D uses @SpringBootTest with the MOCK option and, finally, E also uses @SpringBootTest, but with the RANDOM_PORT option. 
#### C aims to test the Spring MVC controllers. This test will only load the desired controller and its dependencies, so its faster than D and E, as they need to load the entire application. As refered, D and E tests create and load the full application context. The difference is that D also provides a Mock web environment, discarding the use of a real MVC. E, on the other side, provides a real MVC started and assigned to a random port, being usefull for integration testing. 