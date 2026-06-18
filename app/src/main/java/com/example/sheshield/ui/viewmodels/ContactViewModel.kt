package com.example.sheshield.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sheshield.data.ContactEntity
import com.example.sheshield.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel() {
    private val repository = ContactRepository()

    private val _contacts = MutableStateFlow<List<ContactEntity>>(emptyList())
    val allContacts: StateFlow<List<ContactEntity>> = _contacts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        fetchContacts()
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getContacts()
                .catch { e ->
                    // Handle error
                    _isLoading.value = false
                }
                .collect {
                    _contacts.value = it
                    _isLoading.value = false
                }
        }
    }

    fun addContact(contact: ContactEntity) {
        viewModelScope.launch {
            repository.addContact(contact)
        }
    }

    fun updateContact(contact: ContactEntity) {
        viewModelScope.launch {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact: ContactEntity) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }
}
