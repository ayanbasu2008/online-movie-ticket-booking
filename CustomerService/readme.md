# For Running the app on local

## Step 1: run the below command inside the project folder:

```
mvn clean install
```
It will generate a target folder with application jar in it


## Step 2: Run the below command to tag and build the docker image

```
docker build -t <your_dockerhub_id>/<app_name> .
```
e.g. 

```
docker build --build-arg VER=0.0.1 -f Dockerfile -t theatre/theatre-ms:latest .
```

Don't miss the dot in the end of above mentioned command.

## Step 3: To run the app on your local docker instance execute below command
```
docker run -p 80:80 <your_dockerhub_id>/<app_name>
```
e.g.

```
docker run -p 80:80 theatre/theatre-ms:latest
```

## Step 4: Now you can access the app on your browser or rest client on localhost:8080. Sample endpoint is 
```
GET: localhost:80/theatre
```

# For Running the app on kubernetes After the step 1 and step 2 execute below steps

## Step 5: Push your image to dockerhub but running below command
```
docker push <your_dockerhub_id>/blogging-app
```

## Step 6: Login into GCP and create a kubernetes cluster with the name "theatre-app-cluster" or any other name of your choice

## Step 7: Open k8-deployment.yaml file which is included with the codebase and change the placeholder <your_dockerhub_id> at line 30 with your dockerhub id. 

## Step 8: Open gcp cloud shell and create a new file with name "k8-deployment.yaml" using editor. Paste the content of  k8-deployment.yaml from previous step

## Step 9: Run below commands on gcp cloud shell to configure project and compute zone 

```
gcloud config set project <your_project_name>
gcloud config set compute/zone <your_compute_zone>
```

## Step 10: Fetch the credentials using below command:
```
gcloud container clusters get-credentials blogging-app-cluster
```

## Step 11: run below command in gcp cloud shell to deploy the service. 

```
kubectl apply -f k8-deployment.yaml
```

## Step 12: Run Below command to get the external IP. (If external ip is not visible, wait for few seconds and run command again)

```
kubectl get svc
```

## PS: Make sure you stop the service when not in use to avoid unnecessary billing charges

```
kubectl delete -f k8-deployment.yaml
```


## Notes
https://github.com/ERS-HCL/k8s-csm/blob/e340dc1dd96c6f8b3715a7a9619e42ea37198c1d/src/main/java/com/bank/csm/services/impl/CustomerServiceImpl.java#L104

```
select * from theatre th
inner join show sh on th.id = sh.theatre_id
inner join movie mo on mo.id = sh.movie_id;

select * from offer o
inner join offer_theatres ot on ot.offer_id = o.id
inner join theatre th on ot.theatres_id = th.id
```



Customer Payload

```
{
  "active": true,
  "address": "Adress Line 1",
  "age": 15,
  "altMobileNo": "-",
  "city": "C001",
  "email": "test01@test.com",
  "firstName": "test01",
  "gender": "MALE",
  "id": 0,
  "individual": true,
  "lastName": "lastname",
  "middleName": "",
  "mobileNo": "9990000001"
}
```

Theatre Payload

```
{
  "active": true,
  "address": "Address Line 1",
  "city": "C002",
  "columns": 1,
  "name": "JamTheatre",
  "rows": 1
}
```

Show Payload

```
{
  "fromTime": "10:00:00",
  "localDate": "2022-02-12",
  "movieId": 1,
  "price": 150,
  "screen": "A",
  "showNo": "1",
  "tax": 10,
  "theatreId": 1,
  "toTime": "13:00:00",
  "afterNoon": true
}
```

Movie Payload

```
{
  "active": true,
  "description": "Movie Test Desc",
  "genres": [
    "Action"
  ],
  "languages": [
    "HN"
  ],
  "name": "MovieTest",
  "year": 2000
}
```

Book Ticket Payload

```
[
  {
    "customer": 1,
    "seats": [
      "0_1", "0_2", "0_3"
    ],
    "show": 1
  }
]
```


