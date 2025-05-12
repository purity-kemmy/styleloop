package com.example.styleloop.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.styleloop.models.Product
import com.google.firebase.database.FirebaseDatabase

class ProductViewModel : ViewModel() {

    private val dbRef = FirebaseDatabase.getInstance().getReference("Products")

    // LiveData to observe the list of products
    val products = MutableLiveData<List<Product>>()
    val isLoading = MutableLiveData<Boolean>(false)
    val errorMessage = MutableLiveData<String?>()

    // Fetch the list of products from Firebase
    fun fetchProducts() {
        isLoading.postValue(true)
        dbRef.get()
            .addOnCompleteListener { task ->
                isLoading.postValue(false)
                if (task.isSuccessful) {
                    val productList = mutableListOf<Product>()
                    task.result?.children?.forEach {
                        val product = it.getValue(Product::class.java)
                        product?.let { productList.add(it) }
                    }
                    products.postValue(productList)
                } else {
                    errorMessage.postValue(task.exception?.localizedMessage ?: "Failed to fetch products.")
                }
            }
    }

    // Add a new product to Firebase
    fun addProduct(product: Product, onResult: (Boolean, String) -> Unit) {
        val productId = dbRef.push().key ?: return onResult(false, "Failed to generate product ID.")
        dbRef.child(productId).setValue(product)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Product added successfully.")
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Failed to add product.")
                }
            }
    }

    // Update an existing product in Firebase
    fun updateProduct(productId: String, updatedProduct: Product, onResult: (Boolean, String) -> Unit) {
        dbRef.child(productId).setValue(updatedProduct)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Product updated successfully.")
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Failed to update product.")
                }
            }
    }

    // Delete a product from Firebase
    fun deleteProduct(productId: String, onResult: (Boolean, String) -> Unit) {
        dbRef.child(productId).removeValue()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Product deleted successfully.")
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Failed to delete product.")
                }
            }
    }
}
