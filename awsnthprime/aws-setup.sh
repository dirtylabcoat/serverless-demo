#!/bin/bash
AWS=/usr/local/bin/aws
LAMBDA_EXEC_ROLE=NthPrimeLambdaExecRole
LAMBDA_AWS_POLICY=arn:aws:iam::aws:policy/service-role/AWSLambdaBasicExecutionRole
LAMBDA_FUNCTION_NAME=NthPrimeLambdaFunction
LAMBDA_FUNCTION_DESCRIPTION="Demo for HiQ Tech Away #3"

# Setup IAM role for Lambda execution

## Check if role already exists
$AWS iam get-role --role-name $LAMBDA_EXEC_ROLE > /dev/null 2>&1
CHK_ROLE_EXISTS=$(echo $?)
if [ ! $CHK_ROLE_EXISTS -eq 0 ]; then
    echo "Role [$LAMBDA_EXEC_ROLE] doesn't exist. Attempting creation... "
    $AWS iam create-role --role-name $LAMBDA_EXEC_ROLE --assume-role-policy-document file://basicLambdaExecuteRole.json --description "Basic Lambda execution role"
    ## Attach policy to role
    echo "Attaching policy [$LAMBDA_AWS_POLICY] to role [$LAMBDA_EXEC_ROLE]."
    $AWS iam attach-role-policy --role-name $LAMBDA_EXEC_ROLE --policy-arn $LAMBDA_AWS_POLICY
fi

# Upload Lambda JAR to AWS

## Find out the ARN of the exec role
LAMBDA_EXEC_ROLE_ARN=$($AWS iam get-role --role-name $LAMBDA_EXEC_ROLE | grep "\"Arn\":" | sed -e 's/[[:space:]]*\"Arn\": \"\(.*\)\"/\1/g')
## Check if function already exists
$AWS iam get-function --function-name $LAMBDA_FUNCTION_NAME > /dev/null 2>&1
CHK_FUNCTION_EXISTS=$(echo $?)
if [ $CHK_FUNCTION_EXISTS -eq 255 ]; then
    ## Create function and upload JAR
    echo "Function [$LAMBDA_FUNCTION_NAME] doesn't exist. Attempting code upload and creation..."
    $AWS lambda create-function --function-name $LAMBDA_FUNCTION_NAME --runtime java8 --role $LAMBDA_EXEC_ROLE_ARN --handler se.hiq.techaway.slsdemo.awsnthprime.Primer --zip-file fileb://target/awsnthprime.jar --description "$LAMBDA_FUNCTION_DESCRIPTION"
else
    ## Update function with current JAR
    echo "Function [$LAMBDA_FUNCTION_NAME] already exists. Attempting code upload and update..."
    $AWS lambda update-function-code --function-name $LAMBDA_FUNCTION_NAME --zip-file fileb://target/awsnthprime.jar
fi

# Test invoke Lambda function

echo "Invoke Lambda function [$LAMBDA_FUNCTION_NAME] with input 10..."
$AWS lambda invoke --invocation-type RequestResponse --function-name NthPrimeLambdaFunction --region eu-central-1 --log-type Tail --payload '{"n":10}' out.tmp
echo "Result of invocation:"
cat out.tmp | python -m json.tool && echo ""

# Setup API Gateway
