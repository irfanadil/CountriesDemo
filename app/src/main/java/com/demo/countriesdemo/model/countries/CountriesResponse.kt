package com.demo.countriesdemo.model.countries
import java.util.UUID
import kotlin.String

data class CountriesResponseItem(
    val capital: String?,
    val code: String?,
    val currency: Currency?,
    val demonym: String?,
    val flag: String?,
    val language: Language?,
    val name: String?,
    val region: String?
)

data class CustomCountriesResponseItem (
    var longStableId:Long = uuidToLong(UUID.randomUUID()),
    val id: String = UUID.randomUUID().toString(),
    val capital: String?,
    val code: String?,
    val currency: Currency?,
    val demonym: String?,
    val flag: String?,
    val language: Language?,
    val name: String?,
    val region: String?
)

data class Currency(
    val code: String?,
    val name: String?,
    val symbol: String?
)

data class Language(
    val code: String?,
    val iso639_2: String?,
    val name: String?,
    val nativeName: String?
)

fun uuidToLong(uuid: UUID): Long {
    return uuid.mostSignificantBits
}