package com.backend.hotelservice.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class FirebaseConfig {


    @Bean
    public FirebaseAuth firebaseAuth(){
        return FirebaseAuth.getInstance();
    }

    @PostConstruct
    public void initFirebase() {

        FileInputStream serviceAccount;
        try {
            serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setServiceAccountId("firebase-adminsdk-qidtx@lanka-deals.iam.gserviceaccount.com")
                    .build();

            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            System.out.println(firebaseApp);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
