# testRest - Based on Helidon Example: quickstart-mp

examples of calls

- without any data - for example after starting server
curl -X GET http://localhost:8080/
{"result":"[]"}


- progress
curl -X GET http://localhost:8080/progress
{"progress":"0%"}


- put default values. Default values are store inside - it is [1, 2, 3]
curl --head -X PUT http://localhost:8080/
HTTP/1.1 200 OK
Date: Sat, 19 Jan 2019 23:59:58 +0100
transfer-encoding: chunked
connection: keep-alive


- get values after putting default data
curl -X GET http://localhost:8080/
{"result":"[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]"}


- put data trought JSON - there are to much elements inside JSON
curl -i -X PUT -H "Content-Type: application/json" -d "[1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20]" http://localhost:8080/put
HTTP/1.1 400 Bad Request
Date: Sun, 20 Jan 2019 00:00:19 +0100
transfer-encoding: chunked
connection: keep-alive


- get vales after putting to mach elements inside JSON
curl -X GET http://localhost:8080/
{"result":"To much values in JSON source"}


- put data trought JSON - but usable count of elements
curl -i -X PUT -H "Content-Type: application/json" -d '[1,a,true,4]' http://localhost:8080/put
HTTP/1.1 200 OK
Date: Sat, 20 Jan 2019 00:02:58 +0100
transfer-encoding: chunked
connection: keep-alive


- get progres of making permutations
curl -X GET http://localhost:8080/progress
{"progress":"100%"}


- get values after putting data trought JSON - usable count of elements
curl -X GET http://localhost:8080/
{"result":"[[1, a, true, 4], [1, a, 4, true], [1, true, a, 4], [1, true, 4, a],[1, 4, a, true], [1, 4, true, a], [a, 1, true, 4], [a, 1, 4, true], [a, true, 1, 4], [a, true, 4, 1], [a, 4, 1, true], [a, 4, true, 1], [true, 1, a, 4], [true,1, 4, a], [true, a, 1, 4], [true, a, 4, 1], [true, 4, 1, a], [true, 4, a, 1], [4, 1, a, true], [4, 1, true, a], [4, a, 1, true], [4, a, true, 1], [4, true, 1, a], [4, true, a, 1]]"}


