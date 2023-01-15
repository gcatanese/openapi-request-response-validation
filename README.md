# openapi-request-response-validation

Validation of requests/responses according to the OpenAPI specs.

This tool allows to make sure that requests and responses exchanged respect the
OpenAPI specification of your API.

## How does it work?

Use Postman to test your API and send `request`, `response` and `headers` to the validator. 
The Test is marked with green when the API specification is respected.

diagram..

## How to run

Steps:
* add the snippet below in the Collection Tests
* provide the OpenAPI file
* run the `openapi-request-response-validation` tool  

### Collection Test snippet

In the **Collection Tests** add the snippet below. It will run after every request in the collection.

```
openapiRequestResponseValidation = {
    validate: function(pm) {

        const postRequest = {
            url: 'http://localhost:8080/validate',
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            body: {
            mode: 'raw',
            raw: JSON.stringify({ 
                method: pm.request.method, 
                path: pm.request.url.getPath(),
                headers: pm.request.headers,
                requestAsJson: (pm.request.body != "") ? pm.request.body.raw : null,
                responseAsJson: pm.response.text(),
                statusCode: pm.response.code
                })
            }
        };

        pm.sendRequest(postRequest, (error, response) => {
            if(error != undefined) {
                pm.expect.fail('Unexpected error ' + error);
            } else {
                var data = response.json();

                if(data.valid == false) {
                    console.log(data.errors);
                }

                pm.test("OpenAPI validation", () => {
                    pm.expect(data.valid, "Invalid request/response (check Console)").to.equal(true);
                });

            }
        });  
    }

};

openapiRequestResponseValidation.validate(pm);
```

### Provide the OpenAPI spec file

Copy/rename your OpenAPI specs to `openapi/openapi.yaml` or `openapi/openapi.json`

### Start the tool (Java)

Start the Springboot application 
```shell
    mvn spring-boot:run"
```

Alternatively if needed you can customise the location of the OpenAPI file
```shell
    mvn spring-boot:run -Dspring-boot.run.arguments="--inputSpecs=/path/to/myopenapi.yaml"
```

### Start the tool (Docker)

You can run the tool on Docker

```

docker run -v $(pwd):/openapi -e  -it --rm --name test test

docker run -v $(pwd):/tmp -e INPUT_SPECS=/tmp/openapi.yaml -it --rm --name test test

```

String uri = "";

HttpClient client = HttpClient.newBuilder().build();
HttpRequest request = HttpRequest.newBuilder()
.uri(URI.create(uri))
.POST(BodyPublishers.ofString(data))
.build();

    HttpResponse<?> response = client.send(request, BodyHandlers.discarding());
    System.out.println(response.statusCode());



bck

openapiRequestResponseValidation = {
validate: function(pm) {

        const postRequest = {
            url: 'http://localhost:8080/validate',
            method: 'POST',
            header: {'Content-Type': 'application/json'},
            body: {
            mode: 'raw',
            raw: JSON.stringify({ 
                method: pm.request.method, 
                path: pm.request.url.getPath(),
                headers: pm.request.headers,
                requestAsJson: (pm.request.body != "") ? pm.request.body.raw : null,
                responseAsJson: pm.response.text(),
                statusCode: pm.response.code
                })
            }
        };


        pm.sendRequest(postRequest, (error, response) => {
            if(error != undefined) {
                pm.environment.unset("openapiRequestResponseIsValid");
            } else {
                var data = response.json();

                pm.environment.set("openapiRequestResponseIsValid", data.valid);

                if(data.valid == false) {
                    console.log(data.errors);
                }

                pm.test("OpenAPI validation", () => {
    pm.expect(pm.environment.get("openapiRequestResponseIsValid"), "Invalid request/response (check Console)").to.equal(true);
});

            }
        });  
    }

};

openapiRequestResponseValidation.validate(pm);

// pm.test("OpenAPI validation", () => {
//     pm.expect(pm.environment.get("openapiRequestResponseIsValid"), "Invalid request/response (check Console)").to.equal(true);
// });

