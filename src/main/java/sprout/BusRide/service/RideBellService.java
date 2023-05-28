package sprout.BusRide.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import sprout.BusRide.domain.RideBell;

import java.util.ArrayList;
import java.util.List;


@Service
public class RideBellService {

    public static final String COLLECTION_NAME = "ridebell";

    public String insertRideBell(RideBell rideBell) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(rideBell.getId()).set(rideBell);
        return apiFuture.get().getUpdateTime().toString();
    }
//    public String insertRideBell(RideBell rideBell) throws Exception {
//        Firestore firestore = FirestoreClient.getFirestore();
//        CollectionReference collectionRef = firestore.collection(COLLECTION_NAME);
//        ApiFuture<DocumentReference> apiFuture = collectionRef.add(rideBell);
//        String generatedId = apiFuture.get().getId();
//        return generatedId;
//    }

    public List<RideBell> getAllRideBell() throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> querySnapshotFuture = firestore.collection(COLLECTION_NAME).get();

        QuerySnapshot querySnapshot = querySnapshotFuture.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();


        List<RideBell> rideBellList = new ArrayList<RideBell>();

        for (QueryDocumentSnapshot rideBells : documents) {
            RideBell rideBell = new RideBell();
            rideBell.setId(rideBells.getId());
            rideBell.setBusStopId(rideBells.getString("busStopId"));
            rideBell.setMessage(rideBells.getString("message"));

            Gson gson = new Gson();
            String json = gson.toJson(rideBell);

            rideBellList.add(rideBell);
        }

        return rideBellList;
    }

}


