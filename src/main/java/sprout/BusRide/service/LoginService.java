package sprout.BusRide.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import sprout.BusRide.domain.Member;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Service

public class LoginService {

    public static final String COLLECTION_NAME = "Member";
    public Optional<String> findByEmail(String email) {
        try {
            Firestore db = FirestoreClient.getFirestore();
            CollectionReference emails = db.collection("Member");
            Query query = emails.whereEqualTo("email", email);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                return Optional.of(document.getId());
            }
            return Optional.empty();
        } catch (FirestoreException | InterruptedException | ExecutionException e) {
            return Optional.empty();
        }
    }

    public String save(Member member) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        CollectionReference collectionRef = firestore.collection(COLLECTION_NAME);
        ApiFuture<DocumentReference> apiFuture = collectionRef.add(member);
        return apiFuture.get().getId();
    }

}
