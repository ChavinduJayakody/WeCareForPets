package com.example.wecareforpets;

import android.util.Log;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class DBHelper {

    private FirebaseFirestore db;

    public DBHelper() {
        // Initialize Firestore instance
        db = FirebaseFirestore.getInstance();
    }

    // Insert data into the "petOwners" collection
    public void insertPetOwner(String fullName, String email, String password, String phoneNumber,
                               String address1, String address2, String petCategory, String petName,
                               String petAge, String petSex, String petBreed, String petColor, String petNotes) {

        Map<String, Object> petOwner = new HashMap<>();
        petOwner.put("fullName", fullName);
        petOwner.put("email", email);
        petOwner.put("password", password);
        petOwner.put("phoneNumber", phoneNumber);
        petOwner.put("address1", address1);
        petOwner.put("address2", address2);
        petOwner.put("petCategory", petCategory);
        petOwner.put("petName", petName);
        petOwner.put("petAge", petAge);
        petOwner.put("petSex", petSex);
        petOwner.put("petBreed", petBreed);
        petOwner.put("petColor", petColor);
        petOwner.put("petNotes", petNotes);

        // Add the document to Firestore
        db.collection("petOwners")
                .document(email)  // Use email as document ID
                .set(petOwner)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Pet owner added successfully"))
                .addOnFailureListener(e -> Log.w("Firestore", "Error adding pet owner", e));
    }

    // Insert data into the "Caregiver" collection
    public void insertCaregiver(String fullName, String email, String password, String phoneNumber,
                                String dob, String address1, String address2, String address3) {

        Map<String, Object> caregiver = new HashMap<>();
        caregiver.put("fullName", fullName);
        caregiver.put("email", email);
        caregiver.put("password", password);
        caregiver.put("phoneNumber", phoneNumber);
        caregiver.put("DOB", dob);
        caregiver.put("address1", address1);
        caregiver.put("address2", address2);
        caregiver.put("address3", address3);

        // Add the document to Firestore
        db.collection("Caregiver")
                .document(email)  // Use email as document ID
                .set(caregiver)
                .addOnSuccessListener(aVoid -> Log.d("Firestore", "Caregiver added successfully"))
                .addOnFailureListener(e -> Log.w("Firestore", "Error adding caregiver", e));
    }

    // Check if a pet owner exists and verify password
    public void checkPetOwner(String email, String password, final CheckCallback callback) {
        db.collection("petOwners")
                .document(email)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String storedPassword = documentSnapshot.getString("password");
                        if (storedPassword != null && storedPassword.equals(password)) {
                            callback.onSuccess(true); // Pet owner exists and password matches
                        } else {
                            callback.onSuccess(false); // Password doesn't match
                        }
                    } else {
                        callback.onSuccess(false); // No such document
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Error checking pet owner", e);
                    callback.onSuccess(false); // Error occurred
                });
    }

    public void checkCaregiver(String email, String password, final CheckCallback callback) {
        db.collection("Caregiver")
                .document(email)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String storedPassword = documentSnapshot.getString("password");
                        if (storedPassword != null && storedPassword.equals(password)) {
                            callback.onSuccess(true); // Caregiver exists and password matches
                        } else {
                            callback.onSuccess(false); // Password doesn't match
                        }
                    } else {
                        callback.onSuccess(false); // No such document
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Error checking caregiver", e);
                    callback.onSuccess(false); // Error occurred
                });
    }
    // Check if a username (email) exists in the "petOwners" collection
    public void checkUsername(String email, final CheckCallback callback) {
        db.collection("petOwners")
                .document(email)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        callback.onSuccess(true);  // Username exists
                    } else {
                        callback.onSuccess(false);  // Username does not exist
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Error checking username", e);
                    callback.onSuccess(false);  // Error occurred
                });
    }

    // Get details of a specific pet owner
    public void getPetOwnerDetails(String email, final DataCallback callback) {
        db.collection("petOwners")
                .document(email)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> petOwnerDetails = documentSnapshot.getData();
                        callback.onDataReceived(petOwnerDetails); // Return the document data
                    } else {
                        callback.onDataReceived(null); // No such document
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Error getting pet owner details", e);
                    callback.onDataReceived(null); // Error occurred
                });
    }

    public void getCaregiverDetails(String email, final DataCallback callback) {
        db.collection("Caregiver")
                .document(email)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        Map<String, Object> caregiverDetails = documentSnapshot.getData();
                        callback.onDataReceived(caregiverDetails); // Return the document data
                    } else {
                        callback.onDataReceived(null); // No such document
                    }
                })
                .addOnFailureListener(e -> {
                    Log.w("Firestore", "Error getting caregiver details", e);
                    callback.onDataReceived(null); // Error occurred
                });
    }

    // Interface to handle boolean callbacks for check operations
    public interface CheckCallback {
        void onSuccess(boolean exists);
    }

    // Interface to handle data callbacks
    public interface DataCallback {
        void onDataReceived(Map<String, Object> data);
    }
}
