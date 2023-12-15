## Module-1 - Useful links
- [theory](https://git.epam.com/epm-cdp/global-java-foundation-program/java-courses/-/blob/main/kubernetes-for-devs/1-k8s-overview/materials/README.md#related-reading)
- [practice](https://git.epam.com/epm-cdp/global-java-foundation-program/java-courses/-/blob/main/kubernetes-for-devs/1-k8s-overview/task/README.md)

- [Introduction to Kubernetes](https://www.digitalocean.com/community/tutorials/an-introduction-to-kubernetes)
- [Kubernetes architecture](https://kubernetes.io/docs/concepts/overview/components/)
- [Kubernetes objects](https://medium.com/devops-mojo/kubernetes-objects-resources-overview-introduction-understanding-kubernetes-objects-24d7b47bb018)
- [Workload resources](https://kubernetes.io/docs/concepts/workloads/controllers/)
- [Kubernetes Persistent Volume Claims explained](https://cloud.netapp.com/blog/cvo-blg-kubernetes-persistent-volume-claims-explained)
- [Local Persistent Volumes](https://vocon-it.com/2018/12/20/kubernetes-local-persistent-volumes/)
- [StatefulSet](https://kubernetes.io/docs/concepts/workloads/controllers/statefulset)

## Table of Content

- [What to do](#what-to-do)
- [Sub-task 1: Enable k8s](#sub-task-1--enable-k8s)
- [Sub-task 2: Deploy containers in k8s](#sub-task-2--deploy-containers-in-k8s)
- [Sub-task 3: Persistent volumes](#sub-task-3--persistent-volumes)
- [Sub-task 4: Stateful Sets](#sub-task-4--stateful-sets)

## What to do
In this module you will create infrastructure for your k8s cluster and deploy your microservices applications there.

## Sub-task 1: Install k8s
If you have personal licence in Docker Desktop, go to Docker Desktop settings, choose Kubernetes and click checkbox 'Enable Kubernetes'. You will need to wait for the installation and restart docker.
In other cases you should go the hard way:
1. Install docker engine (if not installed) as binaries: https://docs.docker.com/engine/install/binaries/.
   And make sure docker is running by running `docker --version`
2. Install minikube: https://minikube.sigs.k8s.io/docs/start/.
   Verify by running `minikube start`
3. Install kubectl: https://kubernetes.io/docs/tasks/tools/
   Verify by running `kubectl version`

_Note_: When using Windows, running PowerShell as administrator may help. Here's the solution to majority of topics I faced:
https://stackoverflow.com/questions/57431890/error-response-from-daemon-hcsshimcreatecomputesystem-the-virtual-machine-co <br />
After docker will start running Hyper-V containers, make sure to run `minikube docker-env | Invoke-Expression`. This command will make minikube runn containers on Hyper-V too

## Sub-task 2: Deploy containers in k8s
In this subtask you need to create manifest `.yml` files with configuration for deployment. These files should contain the next objects:
- Namespace (f.e. k8s-program). All other objects will use this namespace;
- 2 Services (one for each service of your system). Use NodePort service type and configure nodePort field for testing.
- 2 Deployments (one for each service of your system). For apps deployments set `replicas: 2`. You should add environment variables for your applications here.

_Note_: don't forget to specify namespace all objects. <br>
Delete EXPOSE instruction from dockerfiles and upgrade images. <br>
To deploy, run `kubectl apply ./` in folders where yml files are stored.
To view all objects run `kubectl get all -n=<your_namespace>`. <br>
Along with services and deployments, this command outputs pods and replica-sets. **Find out why.**

## Sub-task 3: Persistent volumes
In this subtask you will make your app pods use local storage. This will ensure that no data is lost during pod deploy/redeploy.
1. Add PersistentVolume object with "manual" storage class for the User service (create separate manifest file). Configure hostPath field so PersistentVolume create directory on the node.
2. Add PersistenceVolumeClaim objects to your manifest and reference them from User deployment object.
3. Test PersistentVolume: create any file inside the container in the volume directory, scale down deployment or delete pod, let replicaset automatically create pod, ensure that file still exists.

## Sub-task 4: Stateful Sets
1. Use StatefulSet object (not Deployment) to create databases.
2. Configure default storage class "hostpath" for volume claim templates, so allowing k8s to provision storage with default provisioner (PersistentVolume will be created automatically).
3. Create 2 Services (one for each StatefulSet of your system). Use ClusterIP service type to restrict external access.

_Note_: You can also use `kubectl port-forward pod-name 5433:5432` (local machine port:container port) console command to temporarily open access to the database pod <br>