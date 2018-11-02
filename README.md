```
-the frame work is developed using selenium with restassured. 
- hierarchy of the framework is as follows-
-src has 1) main and 2) test
-main has pages, payload files and support folders
-pages has RestServices class which houses return type methods for GET and PATCH
-payload has a payload file that is used for the PATCH request
-support has a utils class contining common methods
-support has WebModel class that has return type methods for all the page objects
-test has feature file, step definitions and runnerTest class
-runner test is configured to generate xml , pretty and html reports under target folder after each test execution. tests could be tagged and run from here.
-hamcrest and Junit assertions are used in the project
-POM file has all the required dependencies 
```
