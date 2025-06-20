package altamirano.hernandez.springboot3_firebase.services;

import altamirano.hernandez.springboot3_firebase.configuration.firebase.FirebaseInitializer;
import altamirano.hernandez.springboot3_firebase.dto.CotizacionesDTO;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CotizacionesService {
    @Autowired
    private FirebaseInitializer firebaseInitializer;

    public List<Map<String, Object>> findAllCotizaciones() {
        List<Map<String, Object>> contizaciones = new ArrayList<>();
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = firebaseInitializer.getFireStore().collection("Cotizaciones").get();
        try {
            for (DocumentSnapshot documnet : querySnapshotApiFuture.get().getDocuments()) {
                Map<String, Object> cotizizacion = new HashMap<>();
                cotizizacion.put("ciudad", documnet.get("ciudad"));
                cotizizacion.put("detalle", documnet.get("detalle"));
                cotizizacion.put("direccion ", documnet.get("direccion "));
                cotizizacion.put("email", documnet.get("email"));
                cotizizacion.put("fecha", documnet.get("fecha"));
                cotizizacion.put("nombre", documnet.get("nombre"));
                cotizizacion.put("telefono", documnet.get("telefono"));
                contizaciones.add(cotizizacion);
            }
            return contizaciones;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> findCotizacionById(String id) {
        try {
            ApiFuture<DocumentSnapshot> querySnapshot = firebaseInitializer.getFireStore().collection("Cotizaciones").document(id).get();
            DocumentSnapshot documentSnapshot = querySnapshot.get();
            if (documentSnapshot.exists()) {
                Map<String, Object> cotizizacion = new HashMap<>();
                cotizizacion.put("ciudad", documentSnapshot.get("ciudad"));
                cotizizacion.put("detalle", documentSnapshot.get("detalle"));
                cotizizacion.put("direccion ", documentSnapshot.get("direccion "));
                cotizizacion.put("email", documentSnapshot.get("email"));
                cotizizacion.put("fecha", documentSnapshot.get("fecha"));
                cotizizacion.put("nombre", documentSnapshot.get("nombre"));
                cotizizacion.put("telefono", documentSnapshot.get("telefono"));
                return cotizizacion;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cotizacion no encontrada");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cotizacion no encontrada");
        }
    }

    public boolean existeEmail(String email) {
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = firebaseInitializer.getFireStore().collection("Cotizaciones").get();
        try {
            for (DocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()) {
                if (document.get("email").equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean saveCotizacion(CotizacionesDTO cotizacionesDTO) {
        try {
            ApiFuture<WriteResult> save = firebaseInitializer.getFireStore().collection("Cotizaciones").document().create(cotizacionesDTO);
            if (save.get() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public boolean updateCotizacion(String id, CotizacionesDTO cotizacionesDTO) {
        try {
            DocumentReference docRef = firebaseInitializer.getFireStore().collection("Cotizaciones").document(id);
            Map<String, Object> datosCotizacion = new HashMap<>();
            datosCotizacion.put("ciudad", cotizacionesDTO.getCiudad());
            datosCotizacion.put("detalle", cotizacionesDTO.getDetalle());
            datosCotizacion.put("direccion", cotizacionesDTO.getDireccion());
            datosCotizacion.put("telefono", cotizacionesDTO.getTelefono());
            datosCotizacion.put("email", cotizacionesDTO.getEmail());
            datosCotizacion.put("nombre", cotizacionesDTO.getNombre());

            ApiFuture<WriteResult> update = docRef.update(datosCotizacion);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteCotizacion(String id) {
        try {
            DocumentReference docRef = firebaseInitializer.getFireStore().collection("Cotizaciones").document(id);
            ApiFuture<WriteResult> delete = docRef.delete();
            delete.get();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
