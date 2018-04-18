# Pencil & Paper

### Prerequisites

[Java SE Runtime Environment 1.8.0](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (other versions might work)

[Apache Maven 3.5.0](https://maven.apache.org/download.cgi) (other versions might work)

[Git 2.15.1](https://git-scm.com/downloads) (other versions might work)

Check version numbers with the following commands on a Unix command line:

```
java -version
```
```
mvn -v
```
```
git version
```
## Getting Started

Navigate to the directory where you want to work from, and run
```
git clone https://github.com/wilsontheory/pillar.git
```
after this is finished, go into the repository
```
cd pillar/
```
Now you can move on to test, or you can build and run the program.

### Test

Run the following:

```
mvn clean test
```

If you see "BUILD SUCCESS" in green, the tests are passing. You can modify the code, and rerun the tests to see if anything is broken. The newly-create target/ directory contains some additional information about the test.

## Build & Run

To build a .jar file that can be run by java after running through the unit tests, run:

```
mvn clean package
```
If you want to find the .jar, run:
```
cd target/
```
Now you're in the directory where the .jar file is. It should have a name similar to 'pencil-0.1.jar' To run it, do:
```
java -jar pencil-0.1.jar
```
If the Maven build works it means that all unit tests have passed. You should see a Robert Frost poem as a success message.

## Built With

* [IntelliJ](https://www.jetbrains.com/idea/) - IDE
