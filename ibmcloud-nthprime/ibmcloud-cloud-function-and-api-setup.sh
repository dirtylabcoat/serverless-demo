#!/bin/bash
API_REGION=https://api.eu-gb.bluemix.net
ORG=daniel_org_2
SPACE=dev
CLOUD_FN_NAME=nthPrime
JAR_FILE=target/ibmcloud-nthprime.jar
BASE_PATH=/primer
SUB_PATH=/nth-prime

# Log in to chosen region and set org and space
bx login -a $API_REGION
bx target -o $ORG -s $SPACE

# Upload code and create function
bx wsk action create $CLOUD_FN_NAME $JAR_FILE --main se.hiq.techaway.slsdemo.ibmcloud.ibmcloud.nthprime.Primer

# Set up public API for function
bx wsk action update "/_/$CLOUD_FN_NAME" --web true
bx wsk api create $BASE_PATH $SUB_PATH post $CLOUD_FN_NAME --response-type json
