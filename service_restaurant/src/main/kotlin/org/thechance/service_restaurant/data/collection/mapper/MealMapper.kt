package org.thechance.service_restaurant.data.collection.mapper

import org.bson.types.ObjectId
import org.thechance.service_restaurant.data.collection.MealCollection
import org.thechance.service_restaurant.entity.Meal

fun Meal.toCollection(): MealCollection =
    MealCollection(id = ObjectId(id), name = name, description = description, price = price, isDeleted = isDeleted)

fun MealCollection.toEntity(): Meal =
    Meal(
        id = id.toString(),
        name = name,
        description = description,
        price = price,
        cuisines = cuisines.map { it.toString() })

fun List<MealCollection>.toEntity(): List<Meal> = this.map { it.toEntity() }

