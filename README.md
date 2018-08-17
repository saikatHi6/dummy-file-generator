# dummy-file-generator

Exposed services to genarate dummy data into CSV file.

Two type of service is available
1. Get service
  ### /dummyData/getData
  This service has one parameter which can containe No of rows.
  Response: It will genarate dummy data for employees and return a .csv file with dummy constant member data.
2. Post Service
  ### /dummyData/download/givenCoulmn
  This is genaric service. You can post a request(JSON payload) with two properties no of rows and no of fields with fields type.
  
  examplae: 
  
  ```json 
  {
    "rows":100,
    "columns": {
        "First Name":"NAME",
        "Last Name":"NAME",
        "Phone":"PHONE"
    }
 }
 ```
  You can pass 6 types of data. It will genarate random data and return a .csv file with given no of rows.
  ### Data Type supported: NUMBER,DECIMEL,DATE,PHONE,EMAIL,NAME
