# stackdriver-demo

## Disclaimer
This repo was made for stackdriver demo purposes.
It was an experiment to see how fast you can get your application up and running on stackdriver and what you need to do to integrate.
Use this for inspirational purposes only.


## Running the application
To run the application you need to do the following:
1.  Create a project on Google Cloud
1.  Create a cluster on GKE (Enabling stackdriver integration in the advanced settings)
1.  Start stackdriver for your project (By going to the Stackdriver page)
1.  Deploy one of the yaml files in this repo with: kubectl apply -f metrics.yaml
1.  You should be done, using kubectl get services you can checkout which IP your loadbalancer received and the application should be running there.
