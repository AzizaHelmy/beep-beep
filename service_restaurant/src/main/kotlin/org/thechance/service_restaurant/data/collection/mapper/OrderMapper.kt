package org.thechance.service_restaurant.data.collection.mapper

import org.bson.types.ObjectId
import org.thechance.service_restaurant.data.collection.OrderCollection
import org.thechance.service_restaurant.data.collection.OrderMealCollection
import org.thechance.service_restaurant.data.utils.toUUID
import org.thechance.service_restaurant.domain.entity.Order
import org.thechance.service_restaurant.domain.entity.OrderMeal


fun Order.toCollection(): OrderCollection {
    return OrderCollection(
        id = ObjectId(id),
        userId = ObjectId(userId),
        restaurantId = ObjectId(restaurantId),
        meals = meals.map { it.toCollection() },
        totalPrice = totalPrice,
        createdAt = createdAt,
        orderStatus = status
    )
}

fun OrderCollection.toEntity(): Order {
    return Order(
        id = id.toString(),
        userId = userId.toString(),
        restaurantId = restaurantId.toString(),
        meals = meals.map { it.toEntity() },
        totalPrice = totalPrice,
        createdAt = createdAt,
        status = orderStatus
    )
}

fun OrderMealCollection.toEntity(): OrderMeal {
    return OrderMeal(
        meadId = mealId.toString(),
        quantity = quantity
    )
}

fun OrderMeal.toCollection(): OrderMealCollection {
    return OrderMealCollection(
        mealId = ObjectId(meadId),
        quantity = quantity
    )
}

fun List<OrderCollection>.toEntity(): List<Order> = map { it.toEntity() }
