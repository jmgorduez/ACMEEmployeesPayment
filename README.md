# ACME EmployeesPayment
#### Run app without arguments.
```
$ make run
```
It runs the app using the file under: */src/main/resources/inputFile/input.txt*.

#### Run app with arguments.
```
$ make run file=/src/main/resources/inputFile/input.txt
```
It runs the app using the file specified as argument.
#### Generate .jar file.
```
$ make jar
```
###Testing
#### Run unit test.
```
$ make test
```
#### Run integration test.
```
$ make it
```
#### Run test coverage.
```
$ make coverage
```
The result of tests coverage will be available under: *build/reports/jacoco/test/html/*

###About desing
####Files directory
```
├── domain
|   ├── abstractions
|   ├── enums
|   ├── factory
|   └── parsers
├── infrastructure
|   ├── abstractions
└── utils   
```
* Under domain package can find the solution domain classes: 
    * Under *abstractions* package can find the interfaces.
    * Under *enums* package can find enums used in Strategy design pattern.
    * Under *factory* package can find a concrete class factory.
    * Under *parsers* package can find concrete classes that parse an EmployeePaySheet object and an WorkingTime one.
* Under *infrastructure* package can find a class to present the result to the user. This package follows the same structure of *domain* package.
* Under *utils* package can find a utility class with all of constants used in the app.