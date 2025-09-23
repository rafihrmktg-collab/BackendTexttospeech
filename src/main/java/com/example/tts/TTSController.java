package com.example.tts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api")
public class TTSController {

    @Autowired
    private TextRepository textRepo;

    @PostMapping(value = "/tts", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] tts(@RequestBody TextRequest req) throws Exception {
        // Save text input to DB
        TextEntity entity = new TextEntity(req.getText());
        textRepo.save(entity);

        // Google Cloud TTS
        // Set GOOGLE_APPLICATION_CREDENTIALS env variable to your service account JSON file
        com.google.cloud.texttospeech.v1.TextToSpeechClient ttsClient = com.google.cloud.texttospeech.v1.TextToSpeechClient.create();
        com.google.cloud.texttospeech.v1.SynthesisInput input = com.google.cloud.texttospeech.v1.SynthesisInput.newBuilder().setText(req.getText()).build();
        com.google.cloud.texttospeech.v1.VoiceSelectionParams voice = com.google.cloud.texttospeech.v1.VoiceSelectionParams.newBuilder()
                .setLanguageCode("en-US")
                .setSsmlGender(com.google.cloud.texttospeech.v1.SsmlVoiceGender.NEUTRAL)
                .build();
        com.google.cloud.texttospeech.v1.AudioConfig audioConfig = com.google.cloud.texttospeech.v1.AudioConfig.newBuilder()
                .setAudioEncoding(com.google.cloud.texttospeech.v1.AudioEncoding.LINEAR16) // WAV
                .build();
        com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse response = ttsClient.synthesizeSpeech(input, voice, audioConfig);
        byte[] audioContents = response.getAudioContent().toByteArray();
        ttsClient.close();
        return audioContents;
    }

    // DTO
    public static class TextRequest {
        private String text;
        public String getText() { return text; }
        public void setText(String text) { this.text = text; }
    }
}
