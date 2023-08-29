package kell.com.example.vaanh.model

data class Product(
    val id: Int,
    val code: String,
    val name: String,
    val createDate: Long,
)

/*
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
      "images": [
        {
          "id": 1,
          "url": "https://scontent.fhan2-4.fna.fbcdn.net/v/t39.30808-6/346452708_965621351550510_4289848746180648413_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=730e14&_nc_ohc=i-fCMY3lSpoAX8pwbVt&_nc_ht=scontent.fhan2-4.fna&oh=00_AfDP-CX4HL0-Mmg4jL6t7j6_TfjV8cZZvFASXCUjN4O6Fg&oe=64650495"
        },
        {
          "id": 26,
          "url": "https://cdn.shopify.com/s/files/1/0598/9780/7027/products/4_d2ed6fc5-955e-43a6-a6ea-06f00b41d5eb.jpg?v=1681723624&width=1445"
        }
      ],
      "description": "Chuyển phát nhanh mèo, báo cáo với các bạn!\nChân váy ngắn Gradient nơ đứng to xinh lắm ạ! Ngựa sơn gỗ theo yêu cầu và miếng kim loại hình hoa, chuông vàng leng keng quanh thân ~\nCả set đồ có cả mèo jiojio [wow]"
    },*/