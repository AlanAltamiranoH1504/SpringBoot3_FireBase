package altamirano.hernandez.springboot3_firebase.configuration.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseInitializer {
    //Arranque de conexion con la aplicacion
    @PostConstruct
    public void initFireStore() throws IOException {
        InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("FireBase_DB.json");
        FirebaseOptions options = new FirebaseOptions
                .Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://springboot3-firebase.firebaseio.com")
                .build();
        if (FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    //
    public Firestore getFireStore() {
        return FirestoreClient.getFirestore();
    }
}
