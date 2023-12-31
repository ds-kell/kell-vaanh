package vn.com.kell.vaanh.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductDTO(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("code") @Expose val code: String,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("createdAt") @Expose val createdAt: String = "",
    @SerializedName("category") @Expose val category: Category,
    @SerializedName("material") @Expose val material: Material,
    @SerializedName("brand") @Expose val brand: Brand,
    @SerializedName("discount") @Expose val discount: Discount,
    @SerializedName("images") @Expose val images: List<Image>,
    @SerializedName("description") @Expose val description: String,
) : Parcelable

@Parcelize
data class ProductDetailDTO(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("type") @Expose val code: String,
    @SerializedName("dateModified") @Expose val dateModified: Long = 0,
    @SerializedName("size") @Expose val size: Size,
    @SerializedName("price") @Expose val price: Float,
    @SerializedName("countInStock") @Expose val countInStock: Int,
    @SerializedName("imageUrl") @Expose val imageUrl: String,
    @SerializedName("productDto") @Expose val productDTO: ProductDTO,
) : Parcelable

@Parcelize
data class Size(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("value") @Expose val value: String,
) : Parcelable


@Parcelize
data class Category(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("name") @Expose val name: String,
) : Parcelable

@Parcelize
data class Material(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("description") @Expose val description: String,
) : Parcelable

@Parcelize
data class Brand(
    @SerializedName("id") @Expose val id: Int,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("description") @Expose val description: String,
) : Parcelable

@Parcelize
data class Discount(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("name") @Expose val name: String,
    @SerializedName("value") @Expose val value: Float,
) : Parcelable

@Parcelize
data class Image(
    @SerializedName("id") @Expose val id: Long,
    @SerializedName("url") @Expose var url: String,
) : Parcelable

@Parcelize
data class ProductInCart(
    val productDTO: ProductDTO,
) : Parcelable