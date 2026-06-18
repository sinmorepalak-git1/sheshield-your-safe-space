package com.example.sheshield.repository

import com.example.sheshield.data.ContactEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class ContactRepository {
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private val contactsCollection
        get() = auth.currentUser?.uid?.let { uid ->
            firestore.collection("users").document(uid).collection("contacts")
        }

    fun getContacts(): Flow<List<ContactEntity>> {
        val collection = contactsCollection ?: throw IllegalStateException("User not logged in")
        return collection.snapshots().map { snapshot ->
            snapshot.documents.mapNotNull { doc ->
                val id = doc.id.toIntOrNull() ?: 0
                ContactEntity(
                    id = id,
                    name = doc.getString("name") ?: "",
                    phone = doc.getString("phone") ?: "",
                    relationship = doc.getString("relation") ?: "",
                    initial = doc.getString("initial") ?: (doc.getString("name")?.take(1)?.uppercase() ?: "?"),
                    startColor = doc.getLong("startColor") ?: 0xFFFDA4AF,
                    endColor = doc.getLong("endColor") ?: 0xFFF472B6
                )
            }
        }
    }

    suspend fun addContact(contact: ContactEntity) {
        val collection = contactsCollection ?: return
        // Using a timestamp as a simple numeric ID for compatibility with the existing Entity
        val id = System.currentTimeMillis().toString()
        val data = hashMapOf(
            "id" to id,
            "name" to contact.name,
            "phone" to contact.phone,
            "relation" to contact.relationship,
            "initial" to contact.initial,
            "startColor" to contact.startColor,
            "endColor" to contact.endColor
        )
        collection.document(id).set(data).await()
    }

    suspend fun updateContact(contact: ContactEntity) {
        val collection = contactsCollection ?: return
        val data = hashMapOf(
            "name" to contact.name,
            "phone" to contact.phone,
            "relation" to contact.relationship,
            "initial" to contact.initial,
            "startColor" to contact.startColor,
            "endColor" to contact.endColor
        )
        collection.document(contact.id.toString()).update(data as Map<String, Any>).await()
    }

    suspend fun deleteContact(contact: ContactEntity) {
        val collection = contactsCollection ?: return
        collection.document(contact.id.toString()).delete().await()
    }
}
