### 📜 **`serverless-word-to-pdf/docs/api-docs.md`**
```md
# 📌 Serverless Word to PDF Converter - API Documentation

## 🌍 Base URL  
```
https://{api-gateway-id}.execute-api.{region}.amazonaws.com/prod
```
Replace `{api-gateway-id}` and `{region}` with your API Gateway details.

---

## 🟢 **1. Upload Word Document**
Uploads a `.docx` file to **Amazon S3** for conversion.

### 🔹 **Endpoint**  
```http
POST /upload
```
### 🔹 **Headers**
| Key            | Value           |
|---------------|----------------|
| Content-Type  | multipart/form-data |
| Authorization | Bearer {token} (if using Cognito/IAM) |

### 🔹 **Request Example (cURL)**
```sh
curl -X POST "https://{api-gateway-id}.execute-api.{region}.amazonaws.com/prod/upload" \
     -H "Content-Type: multipart/form-data" \
     -H "Authorization: Bearer {token}" \
     -F "file=@document.docx"
```

### 🔹 **Response**
```json
{
  "message": "File uploaded successfully",
  "file_key": "uploads/document.docx"
}
```

---

## 🔄 **2. Convert Word to PDF**
Triggers AWS Lambda to convert the uploaded Word file to a PDF.

### 🔹 **Endpoint**
```http
POST /convert
```
### 🔹 **Request Body**
```json
{
  "file_key": "uploads/document.docx"
}
```
### 🔹 **Response**
```json
{
  "message": "Conversion started",
  "pdf_key": "converted/document.pdf"
}
```

---

## 📥 **3. Download Converted PDF**
Retrieves the converted **PDF file** from Amazon S3.

### 🔹 **Endpoint**
```http
GET /download?file_key=converted/document.pdf
```

### 🔹 **Response**
📂 **Returns the PDF file as a binary response**  
If the file doesn’t exist:
```json
{
  "error": "File not found"
}
```

---

## 🔐 **Authentication (Optional)**
- If using **Cognito/IAM**, pass the `Authorization: Bearer {token}` header.
- If public access is allowed, no authentication is needed.

---

## 📌 **Future Enhancements**
- ✅ Implement **JWT authentication** via AWS Cognito.
- ✅ Add support for **other file formats** (TXT, HTML).
- ✅ Track conversion status via **DynamoDB**.
---
