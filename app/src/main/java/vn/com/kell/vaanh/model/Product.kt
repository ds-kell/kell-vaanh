package vn.com.kell.vaanh.model

import com.google.gson.annotations.SerializedName


data class Product(
    @SerializedName("id") val id: Long,
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("createdAt") val createdAt: String = "",
    @SerializedName("category") val category: Category,
    @SerializedName("material") val material: Material,
    @SerializedName("brand") val brand: Brand,
    @SerializedName("discount") val discount: Discount,
    @SerializedName("images") val images: List<Images>,
    @SerializedName("description") val description: String,
)

data class Category(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
)

data class Material(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
)


data class Brand(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
)

data class Discount(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("value") val value: Float,
)

data class Images(
    @SerializedName("id") val id: Long,
    @SerializedName("url") var url: String,
)/*
* {
      "id": 1,
      "code": "cos001",
      "name": "Bộ cosplay Kirara Genshin Impact",
      "createdAt": null,
      "category": {
        "id": 2,
        "name": "Quần áo"
      },
      "material": {
        "id": 1,
        "name": "Vải",
        "description": null
      },
      "brand": {
        "id": 6,
        "name": "Miccostumes",
        "description": null
      },
      "discount": {
        "id": 1,
        "name": "Ngày hội văn hoá",
        "value": 10,
        "startDate": "2023-05-14T08:30:45Z",
        "endDate": "2023-07-01T08:30:45Z"
      },

    },*/