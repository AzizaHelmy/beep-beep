package data.gateway.fake

import data.remote.mapper.toEntity
import data.remote.model.CartDto
import data.remote.model.CartMealDto
import domain.entity.Cart
import domain.entity.Location
import domain.entity.Order
import domain.entity.Trip
import domain.gateway.IOrderGateway

class FakeOrderGateway : IOrderGateway {
    override suspend fun getTripHistory(): List<Trip> {
        return listOf(
            Trip(
                id = "trip1",
                taxiId = "taxi123",
                taxiPlateNumber = "ABC123",
                driverId = "driver456",
                driverName = "Said Kolish",
                clientId = "client789",
                startPoint = Location(37.7749, -122.4194),
                destination = Location(37.7831, -122.4039),
                rate = 4.5,
                price = 25.0,
                startDate = "2023-09-20 09:00:00",
                endDate = "2023-09-20 09:30:00",
                timeToArriveInMints = 30
            ),
            Trip(
                id = "trip2",
                taxiId = "taxi789",
                taxiPlateNumber = "XYZ456",
                driverId = "driver789",
                driverName = "Mohamed Ali",
                clientId = "client101",
                startPoint = Location(37.7802, -122.4212),
                destination = Location(37.7915, -122.4017),
                rate = 4.2,
                price = 20.0,
                startDate = "2023-09-20 10:00:00",
                endDate = "2023-09-20 10:25:00",
                timeToArriveInMints = 25
            ),
            Trip(
                id = "trip2",
                taxiId = "taxi789",
                taxiPlateNumber = "XYZ456",
                driverId = "driver789",
                driverName = "Said Hafez",
                clientId = "client101",
                startPoint = Location(37.7802, -122.4212),
                destination = Location(37.7915, -122.4017),
                rate = 4.2,
                price = 20.0,
                startDate = "2023-09-20 10:00:00",
                endDate = "2023-09-20 10:25:00",
                timeToArriveInMints = 25
            ),
            Trip(
                id = "trip2",
                taxiId = "taxi789",
                taxiPlateNumber = "XYZ456",
                driverId = "driver789",
                driverName = "Sayed Ali",
                clientId = "client101",
                startPoint = Location(37.7802, -122.4212),
                destination = Location(37.7915, -122.4017),
                rate = 4.2,
                price = 20.0,
                startDate = "2023-09-20 10:00:00",
                endDate = "2023-09-20 10:25:00",
                timeToArriveInMints = 25
            ),
            Trip(
                id = "trip2",
                taxiId = "taxi789",
                taxiPlateNumber = "XYZ456",
                driverId = "driver789",
                driverName = "Ahmed Hassan",
                clientId = "client101",
                startPoint = Location(37.7802, -122.4212),
                destination = Location(37.7915, -122.4017),
                rate = 4.2,
                price = 20.0,
                startDate = "2023-09-20 10:00:00",
                endDate = "2023-09-20 10:25:00",
                timeToArriveInMints = 25
            ),
            Trip(
                id = "trip2",
                taxiId = "taxi789",
                taxiPlateNumber = "XYZ456",
                driverId = "driver789",
                driverName = "Ahmed Hassan",
                clientId = "client101",
                startPoint = Location(37.7802, -122.4212),
                destination = Location(37.7915, -122.4017),
                rate = 4.2,
                price = 20.0,
                startDate = "2023-09-20 10:00:00",
                endDate = "2023-09-20 10:25:00",
                timeToArriveInMints = 25
            ),
            Trip(
                id = "trip2",
                taxiId = "taxi789",
                taxiPlateNumber = "XYZ456",
                driverId = "driver789",
                driverName = "Ahmed Hassan",
                clientId = "client101",
                startPoint = Location(37.7802, -122.4212),
                destination = Location(37.7915, -122.4017),
                rate = 4.2,
                price = 20.0,
                startDate = "2023-09-20 10:00:00",
                endDate = "2023-09-20 10:25:00",
                timeToArriveInMints = 25
            )
        )
    }

    override suspend fun getOrderHistoryGateway(): List<Order> {
        return listOf(
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Burger king",
                restaurantImageUrl = "https://thecarefreekitchen.com/wp-content/uploads/2021/10/Buffalo-Chicken-Taquitos-1024x1024.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 5489897989474,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order2",
                userId = "user456",
                restaurantId = "restaurant789",
                restaurantImageUrl = "https://www.freshnlean.com/wp-content/uploads/2021/03/Meal-Plan-plate-protein.png",
                restaurantName = "Zeko Talawoth",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal3",
                        mealName = "Pizza",
                        quantity = 1
                    ),
                    Order.Meal(
                        mealId = "meal4",
                        mealName = "Soda",
                        quantity = 2
                    )
                ),
                totalPrice = 22.50,
                createdAt = 564844874874,
                orderStatus = 4,
                timeToArriveInMints = 15
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Kebdaki",
                restaurantImageUrl = "https://www.foodandwine.com/thmb/bRz199ONebY-5h5gcvpOcHRxAkA=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/Web_4000-Trifecta-Chicken-Breast-Sweet-Potato-Mixed-Vegetable_04-72a24aaee5584c06a26451603daec5c9.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Koshary",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 9685989874,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Elza3em",
                restaurantImageUrl = "https://takethemameal.com/files_images_v2/stam.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "pizza",
                        quantity = 3
                    )
                ),
                totalPrice = 15.99,
                createdAt = 54748979565,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Burger king",
                restaurantImageUrl = "https://cdn.apartmenttherapy.info/image/upload/v1558635037/k/archive/388e39a6ca67e257cb7bef6bde6a98aef1bcd434.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 89890544485,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Burger king",
                restaurantImageUrl = "https://ichef.bbci.co.uk/food/ic/food_16x9_832/recipes/roast_chicken_for_one_41998_16x9.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 54861256747,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Burger king",
                restaurantImageUrl = "https://takethemameal.com/files_images_v2/stam.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 42512544644545,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Burger king",
                restaurantImageUrl = "https://takethemameal.com/files_images_v2/stam.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 54578787894847,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
            Order(
                id = "order1",
                userId = "user123",
                restaurantId = "restaurant456",
                restaurantName = "Burger king",
                restaurantImageUrl = "https://takethemameal.com/files_images_v2/stam.jpg",
                meals = listOf(
                    Order.Meal(
                        mealId = "meal1",
                        mealName = "Cheeseburger",
                        quantity = 2
                    ),
                    Order.Meal(
                        mealId = "meal2",
                        mealName = "Fries",
                        quantity = 1
                    )
                ),
                totalPrice = 15.99,
                createdAt = 845845415444545,
                orderStatus = 4,
                timeToArriveInMints = 20
            ),
        )
    }

    override suspend fun getAllCartMeals(): Cart {
        return CartDto(
            meals = mealsCart,
            totalPrice = 255.0,
            currency = "$"
        ).toEntity()
    }

    private val mealsCart = listOf(
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
        CartMealDto(
            id = "64f372095fecc11e6d917656",
            price = 85.0,
            count = 1,
            restaurantName = "Burger king",
            name = "Double cheese burger",
            currency = "$",
            image = "https://s3-alpha-sig.figma.com/img/87f1/a16c/e905155ab7f153570d77f67ecb3bf1f6?Expires=1696204800&Signature=DmxCysc92O0ScX-GO5hhNt2AmsVGk0-U9she4GbHwfhL8qwvl386HN-kur-Wmw-XGE4OptyvEvDLqhMhhaeypR3Bd8lh2SIk6-BxI2z1D1z35QHeHN-ZEVc6Lq1Ai23jtzRAXVfU3nLVLNKOKnupSdhIWQ1JYMaP8TbRokOQFy7cQR70bSEuhROd2urzy5UDq3zJFaqyt6OTleWf9jwJNv5frK0v351I6bdHR0ekSR6flLcCamfvz3skYu7VZ6VE8QLr-dLzX3KIeeYkiZrgEE4YyR054o5TcE~BJALxaLbbHbvKJnOZTp93Lga7Ki5cSFMx3GAYC87Okpz0GHScQA__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4"
        ),
    )
}