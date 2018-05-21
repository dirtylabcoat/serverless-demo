#!/bin/bash
AWS=/usr/local/bin/aws
API_NAME=DisposableNthPrime
TMP_FILE=tmp.log

# Setup API Gateway

## Create API NthPrime
$AWS apigateway create-rest-api --name $API_NAME > $TMP_FILE
API_ID=$(grep "\"id\":" $TMP_FILE | sed 's/[[:space:]]*\"id\": \"\(.*\)\"/\1/g')
echo 1
## Create resource /nth-prime
$AWS apigateway get-resources --rest-api-id $API_ID > $TMP_FILE
ROOT_RESOURCE_ID=$(grep "\"id\":" $TMP_FILE | sed 's/[[:space:]]*\"id\": \"\(.*\)\"/\1/g')
echo 2
$AWS apigateway create-resource --rest-api-id $API_ID --parent-id $ROOT_RESOURCE_ID --path-part 'nth-prime' > $TMP_FILE
exit 1;
RESOURCE_ID=$(grep "\"id\":" $TMP_LOG | sed 's/[[:space:]]*\"id\": \"\(.*\)\"/\1/g')
echo $RESOURCE_ID
## Create method POST
$AWS apigateway put-method --rest-api-id $API_ID --resource-id $RESOURCE_ID --http-method POST --authorization-type NONE

rm -f $TMP_LOG
echo 4
