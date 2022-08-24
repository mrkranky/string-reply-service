# String reply service

## Last build status:
[![CircleCI](https://circleci.com/gh/allanneves/money-transfer-api/tree/master.svg?style=svg)](https://circleci.com/gh/allanneves/money-transfer-api/tree/master)

## Tech Stack:
![Java](https://img.shields.io/badge/java-1.8-red.svg?style=plastic)
![SpringBoot](https://img.shields.io/badge/springboot-green.svg?style=plastic)

## Current Rules
#### Reverse
This rule when applied reverses the entire input string

#### MD5 Hash
This rule when applied returns an MD5 hash of the input string

## How to add a new rule
A new rule can be added in an extremely simple way by -

1. implementing the `Rule` interface and implementing its `applyRule` method
```
@Component
public class NewRule implements Rule {
    public static final int OPERATION_ID = 3; // define a unique operation id for this rule

    @Override
    public String applyRule(String input) {
        // ... add your rule implementation here
    }
}
```

2. adding the new rule in the `RulesRegistry` class
```
public void registerRules() {
    ruleMap.put(ReverseRule.OPERATION_ID, reverseRule);
    ruleMap.put(MD5HashRule.OPERATION_ID, md5HashRule);
    // .... add the new rule here
    ruleMap.put(NewRule.OPERATION_ID, newRule);
}
```

*Voila! You are done :)*

### Endpoints
### ![GET](https://img.shields.io/badge/GET-red.svg?style=plastic) v1 - /reply/{message}
```
http://127.0.0.1:8080/reply/kbzw9ru
```

Sample Response - 200 OK
```json
{
  "data": "kbzw9ru"
}
```

Response - 400 BAD_REQUEST
```json
{
  "message": "Message is empty"
}
```

### ![GET](https://img.shields.io/badge/GET-red.svg?style=plastic) v2 - /v2/reply/{message}
```
http://127.0.0.1:8080/v2/reply/12-kbzw9ru
```

Sample Response - 200 OK
```json
{
  "data": "5a8973b3b1fafaeaadf10e195c6e1dd4"
}
```

Response - 400 BAD_REQUEST (if the message is not in the valid format)
```json
{
  "message": "Invalid input"
}
```

Response - 400 BAD_REQUEST (if the operation is not supported)
```json
{
  "message": "Operation not found: 3"
}
```

## Test
1. unit tests are added to test controllers and rules
2. code coverage is 100%

# Your Task
Our company has released a beta version of **String Reply Service** and it has been a huge success.
In the current implementation (as part of boilerplate code), the **String Reply Service** takes in an input string (in the format of `[a-z0-9]*`)
and returns the input in a JSON object.

For example,

```
GET /reply/kbzw9ru
{
    "data": "kbzw9ru"
}
```

As the service is widely adopted, there have been increasing feature requests.
Our project manager has come back with the following requirements for V2 of the service:

The input string will now be comprised of two components, a rule and a string, separated by a dash (-).
Rules **always** contain two numbers. Each number represents a string operation.

The supported numbers are:

- `1`: reverse the string

   E.g. `kbzw9ru` becomes `ur9wzbk`

- `2`: encode the string via MD5 hash algorithm and display as hex

   E.g. `kbzw9ru` becomes `0fafeaae780954464c1b29f765861fad`

The numbers are applied in sequence, i.e. the output of the first rule will
serve as the input of the second rule. The numbers can also be repeated,
i.e. a rule of 11 would mean reversing the string twice, resulting in no change to the string.

Giving a few examples,

```
GET /v2/reply/11-kbzw9ru
{
    "data": "kbzw9ru"
}
```
```
GET /v2/reply/12-kbzw9ru
{
    "data": "5a8973b3b1fafaeaadf10e195c6e1dd4"
}
```
```
GET /v2/reply/22-kbzw9ru
{
    "data": "e8501e64cf0a9fa45e3c25aa9e77ffd5"
}
```

## What you need to do
Use the boilerplate given and implement the above requirement.
Your implementation should also consider:

- Maintain the existing endpoint for backward compatibility.
- Implement V2 endpoint for the above new requirements.
- Additional rules are expected in future releases. The updates in rule set
should have minimal code changes and impact to existing functionality.
- Testability for individual rule and the application.
Unit tests are highly recommended.
- Endpoints should return correct status code and response message.
For invalid request, it should return status code `400`
with message `"Invalid input"`, for example:
   ```
   GET /v2/reply/13-kbzw9ru
   {
       "message": "Invalid input"
   }
   ```

Upon completing the task, please feel free to (though not required):

- host your code on Github
- include any readme to explain your setup/environment
- add/implement anything you think would be beneficial

## Build project

To build the project, simply run
```
./gradlew build
```

## Start project

To start the project, simply run
```
./gradlew bootRun
```

Once the service started, the endpoint will be available at `localhost:8080`, so you can make request to the service endpoint

```json
GET localhost:8080/reply/helloworld

{
    message: "helloword"
}
```