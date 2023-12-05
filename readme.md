Run:
 ```
 docker-compose up
 ```





GET audio bin
http://localhost:8081/v1/api/resources/1

POST save audio and meta
http://localhost:8081/v1/api/resources/
- Content-Type audio/mpeg
- file -> of.mp3

DELETE audio bin
http://localhost:8081/v1/api/resources?id=0,1,2



GET song meta
http://localhost:8082/v1/api/songs/0

DELETE song meta
http://localhost:8082/v1/api/songs?id=0,1,2



---
# K8S
### https://hub.docker.com/u/andreirohau

### COMMANDS

### Build images and push to remote repo
docker build -t andreirohau/resource-service .
docker push andreirohau/resource-service

docker build -t andreirohau/song-service .
docker push andreirohau/song-service

### Deploy on k8s
kubectl apply -f namespace.yaml
kubectl apply -f volume.yaml
kubectl apply -f manifest.yaml

kubectl get persistentvolumes
kubectl get pods -n k8s-program -o wide

### Undeploy
kubectl delete -f manifest.yaml
kubectl delete -f volume.yaml
kubectl delete -f namespace.yaml

kubectl delete pod db-song-service-statefulset-0 -n=k8s-program

### Logs or info
kubectl describe pods app-resource-service-deployment-69585dd49c-6jhrz -n=k8s-program
kubectl logs app-resource-service-deployment-69585dd49c-6jhrz -n k8s-program

### Testing using terminal
kubectl exec -it db-resource-service-deployment-5fbb95846f-wvdn7 -n k8s-program -c db-resource-service -- /bin/bash

psql -h localhost -p 5432 -U rsuser -d resource_service_db







