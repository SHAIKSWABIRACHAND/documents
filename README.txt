7
🔷 *AWS Lambda – Simple Python Steps*

---

🔹 *Step 1: Open Lambda*
AWS → Services → Compute → Lambda

---

🔹 *Step 2: Create Function*
•⁠  ⁠Click *Create function*
•⁠  ⁠Choose *Author from scratch*
•⁠  ⁠Name: ⁠ basic-python-lambda ⁠
•⁠  ⁠Runtime: Python 3.9
•⁠  ⁠Architecture: x86_64
•⁠  ⁠Role: Use existing role → *Lab Role*
•⁠  ⁠Click *Create function*

---

🔹 *Step 3: Default Code (Understanding)*
•⁠  ⁠⁠ event ⁠ → input data
•⁠  ⁠⁠ context ⁠ → runtime info
•⁠  ⁠⁠ lambda_handler ⁠ → main function
•⁠  ⁠⁠ statusCode ⁠ → response code
•⁠  ⁠⁠ body ⁠ → output

---

🔹 *Step 4: Write Code (Add Numbers)*

⁠ python
import json
def lambda_handler(event, context):
    a = event.get('num1', 0)
    b = event.get('num2', 0)
    result = a + b
    return {
        'statusCode': 200,
        'body': json.dumps({'sum': result})
    }
 ⁠

---

🔹 *Step 5: Deploy*
•⁠  ⁠Click *Deploy*
•⁠  ⁠Wait for success message

---

🔹 *Step 6: Create Test Event*
•⁠  ⁠Click *Test*
•⁠  ⁠Create new event → ⁠ testInput ⁠
•⁠  ⁠Enter:

⁠ json
{
  "num1": 10,
  "num2": 20
}
 ⁠

•⁠  ⁠Click *Test*

---

🔹 *Step 7: Output*
✔ Output:

⁠ json
{
  "statusCode": 200,
  "body": "{\"sum\": 30}"
}
 ⁠

---

🔹 *Step 8: Configuration*
•⁠  ⁠Memory → 128 MB
•⁠  ⁠Timeout → 3 sec
•⁠  ⁠Architecture → x86_64
•⁠  ⁠Save

--- ## 🔹 1. SNS (Send Email Notification)
8




*Step 1: Create Topic*
•⁠  ⁠AWS → SNS → Topics → Create
•⁠  ⁠Type: Standard
•⁠  ⁠Name: ⁠ MyEmailTopic ⁠

*Step 2: Create Subscription*
•⁠  ⁠Protocol: Email
•⁠  ⁠Enter your email
•⁠  ⁠Create

*Step 3: Confirm Email*
•⁠  ⁠Check inbox → Click confirm

*Step 4: Send Message*
•⁠  ⁠Click Publish
•⁠  ⁠Add subject & message
✔ Email received

---

## 🔹 2. S3 → SNS → Email

*Step 1: Create S3 Bucket*
•⁠  ⁠AWS → S3 → Create bucket
•⁠  ⁠Same region as SNS

*Step 2: SNS Topic*
•⁠  ⁠Use existing or create new
•⁠  ⁠Email must be confirmed

*Step 3: Allow Permission*
•⁠  ⁠SNS → Access Policy
•⁠  ⁠Allow S3 to publish

*Step 4: Add Event*
•⁠  ⁠S3 → Properties → Event notification
•⁠  ⁠Event: Object create
•⁠  ⁠Destination: SNS

*Step 5: Test*
•⁠  ⁠Upload file
✔ Email notification received

---

## 🔹 3. SQS (Queue Messaging)

*Step 1: Create Queue*
•⁠  ⁠AWS → SQS → Create
•⁠  ⁠Type: Standard
•⁠  ⁠Name: ⁠ MyQueue ⁠

*Step 2: Send Message*
•⁠  ⁠Open queue → Send message
•⁠  ⁠Enter text

*Step 3: Receive Message*
•⁠  ⁠Click Poll
✔ Message appears

---

## 🔹 4. S3 → SNS → SQS → Lambda

📌 *Flow:*
S3 → SNS → SQS → Lambda

---

*Step 1: Create Services*
•⁠  ⁠S3 bucket
•⁠  ⁠SNS topic
•⁠  ⁠SQS queue

---

*Step 2: Connect SQS to SNS*
•⁠  ⁠SNS → Create subscription
•⁠  ⁠Protocol: SQS

---

*Step 3: Permissions*
•⁠  ⁠SQS → allow SNS
•⁠  ⁠SNS → allow S3

---

*Step 4: S3 Event*
•⁠  ⁠S3 → Event notification
•⁠  ⁠Send to SNS

---

*Step 5: Test Flow*
•⁠  ⁠Upload file
✔ Message goes to SQS

---

*Step 6: Lambda Setup*
•⁠  ⁠Create Lambda
•⁠  ⁠Add SQS trigger

---

*Step 7: Lambda Code*

⁠ python id="sqs123"
def lambda_handler(event, context):
    for record in event['Records']:
        print(record['body'])
    return {'statusCode': 200}
 ⁠

---

## 🔹 Final Architecture

User Upload
↓
S3
↓
SNS
↓
SQS
↓
Lambda
↓
Processing

---

## 🔹 Key Points

✔ SNS → Broadcast (many users)
✔ SQS → Store messages
✔ Lambda → Process automatically
✔ S3 → Trigger events

---
9




✅ *Complete Serverless System*
[05/05/26, 9:55:35 AM] Chand: ## 🔹 1. Launch EC2 Instances

*Step 1: Create 2 Instances*
•⁠  ⁠EC2 → Launch Instance
•⁠  ⁠Names: ⁠ webserver-1 ⁠, ⁠ webserver-2 ⁠
•⁠  ⁠AMI: Amazon Linux 2
•⁠  ⁠Allow: SSH (22), HTTP (80)

---

*Step 2: Install Web Server*
Run in both:

⁠ bash id="ec2cmd"
yum update -y
yum install httpd -y
systemctl start httpd
systemctl enable httpd
 ⁠

*Step 3: Add Web Page*
•⁠  ⁠Server 1:

⁠ bash id="srv1"
echo "This is Server 1" > /var/www/html/index.html
 ⁠

•⁠  ⁠Server 2:

⁠ bash id="srv2"
echo "This is Server 2" > /var/www/html/index.html
 ⁠

---

## 🔹 2. Create Load Balancer (ALB)

*Step 1: Security Group*
•⁠  ⁠Allow HTTP (80)

*Step 2: Create ALB*
•⁠  ⁠Name: ⁠ my-alb ⁠
•⁠  ⁠Type: Internet-facing
•⁠  ⁠Listener: HTTP

---

*Step 3: Target Group*
•⁠  ⁠Name: ⁠ web-servers-tg ⁠
•⁠  ⁠Register both EC2 instances

---

*Step 4: Verify*
•⁠  ⁠Copy ALB DNS
•⁠  ⁠Open in browser
•⁠  ⁠Refresh → switches servers

---

## 🔹 3. Auto Scaling Setup

*Step 1: Create AMI*
•⁠  ⁠EC2 → Create Image
•⁠  ⁠Name: ⁠ my-webserver-AMI ⁠

---

*Step 2: Launch Template*
•⁠  ⁠Name: ⁠ my-launch-template ⁠
•⁠  ⁠Use AMI
•⁠  ⁠Instance: t2.micro

---

*Step 3: Auto Scaling Group*
•⁠  ⁠Name: ⁠ webserver-asg ⁠
•⁠  ⁠Attach Load Balancer

---

*Step 4: Set Capacity*
•⁠  ⁠Min: 1
•⁠  ⁠Desired: 2
•⁠  ⁠Max: 4

---

## 🔹 4. Test Auto Scaling

•⁠  ⁠Terminate 1 instance
✔ New instance created automatically

•⁠  ⁠Increase traffic
✔ More instances launched

---

## 🔹 5. Final Flow

User Request
↓
Load Balancer (ALB)
↓
Multiple EC2 Instances
↓
Auto Scaling adjusts servers

---

## 🔹 Key Points

✔ Load Balancer → Distributes traffic
✔ Auto Scaling → Adds/removes servers
✔ Health Check → Removes bad servers
✔ AMI → Template for new instances



10
---
[05/05/26, 9:55:55 AM] Chand: 
## 🔹 1. Open Beanstalk

•⁠  ⁠AWS → Compute → Elastic Beanstalk
•⁠  ⁠Click *Create Application*

---

## 🔹 2. Create Application

•⁠  ⁠Name: ⁠ MyApp ⁠
•⁠  ⁠Description: optional
•⁠  ⁠Click *Create*

---

## 🔹 3. Create Environment

•⁠  ⁠Click *Create Environment*
•⁠  ⁠Choose *Web Server Environment*
•⁠  ⁠Click *Select*

---

## 🔹 4. Configure Environment

•⁠  ⁠Enter Environment Name
•⁠  ⁠Choose Platform:
→ Tomcat / Node.js / Python

•⁠  ⁠Upload Code:
→ ⁠ .war ⁠ / ⁠ .zip ⁠

---

## 🔹 5. Service Access

•⁠  ⁠Service Role → LabRole
•⁠  ⁠EC2 Profile → LabInstanceProfile
•⁠  ⁠Key Pair → Optional

---

## 🔹 6. Network Setup

•⁠  ⁠VPC → Default
•⁠  ⁠Subnets → Select
•⁠  ⁠Enable Public IP

---

## 🔹 7. Instance & Scaling

•⁠  ⁠Instance: t2.micro
•⁠  ⁠Min: 1
•⁠  ⁠Max: 2

✔ Enable Load Balancer

---

## 🔹 8. Create & Deploy

•⁠  ⁠Click Review
•⁠  ⁠Click Create

✔ AWS auto creates:
•⁠  ⁠EC2
•⁠  ⁠Load Balancer
•⁠  ⁠Auto Scaling

---

## 🔹 9. Access App

•⁠  ⁠Get URL
•⁠  ⁠Open in browser

✔ Application runs

---

## 🔹 10. Update App

•⁠  ⁠Click Upload & Deploy
•⁠  ⁠Upload new version

---

## 🔹 Key Points

✔ Fully managed service
✔ Auto scaling + Load balancing
✔ Easy deployment
✔ Supports multiple languages

---





## 🔹 Final Flow

Upload Code
↓
Elastic Beanstalk
↓
EC2 + Load Balancer + Auto Scaling
↓
Live Application
11




---
[05/05/26, 9:58:33 AM] Chand: ## 🔹 1. Open Amazon Lex

•⁠  ⁠AWS → Search *Amazon Lex*
•⁠  ⁠Click *Create Bot*

---

## 🔹 2. Create Bot

•⁠  ⁠Choose: Blank bot
•⁠  ⁠Name: ⁠ HotelBookingBot ⁠
•⁠  ⁠IAM Role: Create new
•⁠  ⁠Language: English
•⁠  ⁠Click Done

---

## 🔹 3. Create Intent

•⁠  ⁠Go to Intents
•⁠  ⁠Name: ⁠ BookHotel ⁠

---

## 🔹 4. Add Utterances

Examples:
•⁠  ⁠“Book a hotel”
•⁠  ⁠“Reserve room”
•⁠  ⁠“I need a room”

✔ Defines user input

---

## 🔹 5. Create Slots (Inputs)

•⁠  ⁠age → Number
•⁠  ⁠location → City
•⁠  ⁠checkin → Date
•⁠  ⁠nights → Number

✔ Mark all as Required

---

## 🔹 6. Add Condition

Example:
•⁠  ⁠If age < 18 → Not eligible

✔ Controls flow

---

## 🔹 7. Custom Slot

•⁠  ⁠Create: ⁠ RoomType ⁠
•⁠  ⁠Values: Single, Double, Suite
•⁠  ⁠Add to intent

---

## 🔹 8. Add Buttons (Cards)

•⁠  ⁠Title: Select Room
•⁠  ⁠Buttons:
→ Single
→ Double
→ Suite

---

## 🔹 9. Responses

•⁠  ⁠Welcome: Ask name
•⁠  ⁠Confirmation:
“Confirm booking in {location} for {nights} nights?”

---

## 🔹 10. Build & Test

•⁠  ⁠Click Build
•⁠  ⁠Test chatbot

Example Flow:
User → Book hotel
Bot → Ask details
User → Provide info
Bot → Confirm booking

---

## 🔹 Final Flow

User Input
↓
Intent Match
↓
Slots Filled
↓
Condition Check
↓
Response

---

## 🔹 Key Points

✔ Intent → What user wants
✔ Slots → User data
✔ Utterances → Training
✔ Cards → Buttons
✔ Lex → NLP chatbot

---


12



✅ *AI Chatbot Ready*
[05/05/26, 10:01:16 AM] Chand: 🔷 *AWS WEEK-12 – IAM (Ultra Short Cheat Sheet)*

---

## 🔹 GUI Access

*Create User*
•⁠  ⁠IAM → Users → Create
•⁠  ⁠Name: ⁠ S3_Specialist ⁠
•⁠  ⁠Enable Console access

*Permissions*
•⁠  ⁠Attach: ⁠ AmazonS3FullAccess ⁠

*Download*
•⁠  ⁠Save ⁠ .csv ⁠ (URL + password)

---

*Login Test*
•⁠  ⁠Use IAM login URL

✔ EC2 → ❌ Denied
✔ S3 → ✅ Works

---

## 🔹 CLI Access

*Create Keys*
•⁠  ⁠IAM → Security Credentials
•⁠  ⁠Create Access Key
•⁠  ⁠Download ⁠ .csv