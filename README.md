# Text to Speech Backend  
  
## Build & Run  
1. Set DB credentials in `application.properties`  
2. `mvn clean package`  
3. `java -jar target/tts-backend-1.0.0.jar`  
  
## API  
- `POST /api/tts`  
	- Body: `{ "text": "your input text" }`  
	- Response: WAV bytes of spoken text  
  
## Features  
- Stores each input text in DB  
- Converts text to speech using FreeTTS. (Feel free to upgrade to Google TTS or AWS Polly for production)  
# BackendTexttospeech