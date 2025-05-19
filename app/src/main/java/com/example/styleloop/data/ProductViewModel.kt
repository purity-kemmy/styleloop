package com.example.styleloop.data


import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.styleloop.models.Product
import com.example.styleloop.navigation.LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PropertyViewModel(
    private val navController: NavHostController,
    private val context: Context
) {
    private val databaseReference = FirebaseDatabase.getInstance().getReference("Properties")
    private val auth = FirebaseAuth.getInstance()

    init {
        if (auth.currentUser == null) {
            navController.navigate(LOGIN)
        }
    }

    // ✅ Add Product
    fun addProduct(product: Product, onComplete: ((Boolean) -> Unit)? = null) {
        val propertyId = product.id.ifEmpty { System.currentTimeMillis().toString() }
        val currentUser = auth.currentUser ?: return

        val productToUpload = product.copy(
            id = propertyId,
            ownerId = currentUser.uid
        )

        databaseReference.child(propertyId).setValue(productToUpload).addOnCompleteListener {
            val message = if (it.isSuccessful) "Product added successfully" else "Failed to add product"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onComplete?.invoke(it.isSuccessful)
        }
    }

    // ✅ Update Product
    fun updateProduct(product: Product, onComplete: ((Boolean) -> Unit)? = null) {
        if (product.id.isEmpty()) {
            Toast.makeText(context, "Invalid product ID", Toast.LENGTH_SHORT).show()
            onComplete?.invoke(false)
            return
        }

        val updatedData = mapOf(
            "title" to product.title,
            "description" to product.description,
            "price" to product.price,
            "location" to product.location,
            "imageUrl" to product.imageUrl
        )

        databaseReference.child(product.id).updateChildren(updatedData).addOnCompleteListener {
            val message = if (it.isSuccessful) "Product updated successfully" else "Failed to update product"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onComplete?.invoke(it.isSuccessful)
        }
    }

    // ✅ Delete Product
    fun deleteProduct(productId: String, onComplete: ((Boolean) -> Unit)? = null) {
        databaseReference.child(productId).removeValue().addOnCompleteListener {
            val message = if (it.isSuccessful) "Product deleted successfully" else "Failed to delete product"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            onComplete?.invoke(it.isSuccessful)
        }
    }

    // ✅ Fetch All Products
    fun fetchAllProducts(
        selectedProduct: MutableState<Product>,
        productList: SnapshotStateList<Product>
    ): SnapshotStateList<Product> {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                productList.clear()
                for (snap in snapshot.children) {
                    val product = snap.getValue(Product::class.java)
                    product?.let {
                        productList.add(it)
                        selectedProduct.value = it
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "Failed to fetch products: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })

        return productList
    }
}
