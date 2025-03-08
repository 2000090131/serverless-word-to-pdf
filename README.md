# Serverless Word to PDF Converter 📝➡️📄

This project is an AWS Lambda-based serverless application that converts Word documents (.docx) to PDFs using Java.

## 🚀 Features
- **AWS Lambda**: Handles the conversion process serverlessly.
- **Amazon S3**: Stores both the input `.docx` and output `.pdf` files.
- **Amazon API Gateway**: Secure REST API for uploading & downloading files.
- **Amazon SQS**: Asynchronous job processing for scalability.
- **AWS Step Functions**: Manages multi-step workflows for reliability.
- **CloudWatch Logging**: Monitors function execution & errors.
- **Terraform/CDK**: Automates infrastructure deployment.

## 🛠️ Technologies Used
- **Programming Language**: Java
- **AWS Services**: Lambda, S3, API Gateway, SQS, Step Functions, CloudWatch
- **Infrastructure as Code**: Terraform/CDK
- **Dependencies**: Apache POI (for .docx), iText (for PDF)

## 📜 How It Works
1. **User uploads a .docx file** via API Gateway or directly to S3.
2. **AWS Lambda** converts the Word file to a **PDF**.
3. **Converted PDF is stored** in S3.
4. **Download link is generated** for the user.

## ✅ Future Enhancements
- Add support for **multiple file formats** (e.g., TXT, HTML).
- Implement **authentication** (Cognito/IAM) for API Gateway.
- Use **DynamoDB** to store metadata of converted files.

## 🤝 Contributing
Feel free to submit **pull requests** or raise issues!

