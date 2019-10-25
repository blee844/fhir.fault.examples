# Intro
This repo contains examples of fault (a.k.a. error) response messages for FHIR requests.

The faults are based on the FHIR [OperationOutcome Resource](http://hl7.org/implement/standards/fhir/operationoutcome.html)

I've included [JSON 4 schema](./src/main/resources/OperationOutcome-schema4.json) in case that helps in your understanding.

While not necessary for your implementation, I've included [java code](./src/main/java/com/stchome/hapi/proto/BrianOperationOutcomeExample.java) that will generate these messages. Its based on the [HAPI-FHIR](https://hapifhir.io/) libraries. 

## Example Fault Messages

## HTTP 404 (Resource not found)
This message is returned when the requested resource doesn't exist, ie

``GET http://myendpoint:/no-such-resource``

```
{
  "resourceType": "OperationOutcome",
  "id": "clientFaultNotFound",
  "text": {
    "status": "generated",
    "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\">Resource Not Found. HTTP 404 Not Found</div>"
  },
  "issue": [
    {
      "severity": "fatal",
      "code": "not-found",
      "details": {
        "text": "Resource Not Found. HTTP 404 Not Found"
      }
    }
  
```

## HTTP 405 (Method not allowed)

This message is returned when an HTTP operation isn't allowed for a resource

``GET http://myendpoint:/valid-resource``
```
{
  "resourceType": "OperationOutcome",
  "id": "clientFaultNotAllowed",
  "text": {
    "status": "generated",
    "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\">Method Not Allowed. HTTP 405 Method Not Allowed</div>"
  },
  "issue": [
    {
      "severity": "fatal",
      "code": "not-supported",
      "details": {
        "text": "Method Not Allowed. HTTP 405 Method Not Allowed"
      }
    }
  ]
}
```

## HTTP 422 (Client error, data validation fault)

This message is returned when the client/consumer sends in a message with a valid structure but with invalid or illogical values.

Common data validation fault

| Command | Description |
| --- | --- |
| git status | List all new or modified files |
| git diff | Show file differences that haven't been staged |

|Condition                     |Message                                                                             |
|------------------------------|------------------------------------------------------------------------------------|
|Assessment Date          > DOB|Illogical Date error: Assesment date must be after date of birth                    |
|Vaccination Date         > DOB|Illogical Date error: Vaccination Date must be on or after date of birth            |
|Medical Observation Date > DOB|Illogical Date error: Medical Observation Date must be on or after date of birth    |
|Observation Code exists       |Unrecognized observation code                                                       |
|Antigen Series exists         |Unrecognized antigen series                                                         |
|Patient DOB Required          |Missing Date error: patientDob is required                                          |
|Vaccination Date Required     |Missing Date error: vaccineDate is required                                         |
|Date is Valid                 |DOB value[20001335], invalid date                                                   |  

Example assessmentDate before patient dob

``PUT http://myendpoint:/valid-resource
{
	"assessmentDate": "2001241",
	"birthDate": "20190920",
}
``
```
{
  "resourceType": "OperationOutcome",
  "id": "clientFaultValidation",
  "text": {
    "status": "generated",
    "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\">Illogical Date error: Assessment date must be after date of birth</div>"
  },
  "issue": [
    {
      "severity": "error",
      "code": "structure",
      "details": {
        "text": "Illogical Date error: Assessment date must be after date of birth"
      }
    }
  ]
}
```
## HTTP 500 (Non-Transient Exception, not going to get better, don't retry)
This message is returned when a non-transient exception is encountered. An example could be when a configuration file isn't found.
```
{
  "resourceType": "OperationOutcome",
  "id": "serverFaultNonTransient",
  "text": {
    "status": "generated",
    "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\">XML schedule file not found</div>"
  },
  "issue": [
    {
      "severity": "fatal",
      "code": "exception",
      "details": {
        "text": "XML schedule file not found"
      }
    }
  ]
}
```

## HTTP 503 (Transient Exception, might get better later)
This message is returned when a transient Exception is encountered. Like when all the connections in a connection pool are being used. Connections might be available later as connections are released to the pool

```
{
  "resourceType": "OperationOutcome",
  "id": "serverFaultTransient",
  "text": {
    "status": "generated",
    "div": "<div xmlns=\"http://www.w3.org/1999/xhtml\">No connections available in pool</div>"
  },
  "issue": [
    {
      "severity": "error",
      "code": "transient",
      "details": {
        "text": "No connections available in pool"
      }
    }
  ]
}
```

