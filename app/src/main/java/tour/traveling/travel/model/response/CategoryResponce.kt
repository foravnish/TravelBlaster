package tour.traveling.travel.model.response

import java.io.Serializable

data class DetailsItem(val package_purchase_limit: Int = 0,
                       val country_details: List<CountryDetailsItem>?,
                       val createdAt: String = "",
                       val duration: String = "",
                       val hotelList: String = "",
                       val package_country: String = "",
                       val category_id: Int = 0,
                       val hotel_details: List<HotelDetailsItem>?,
                       val terms_conditions: String = "",
                       val amenities_details: List<AmenitiesDetailsItem>?,
                       val id: Int = 0,
                       val package_night: Int = 0,
                       val package_images: List<PackageImagesItem>?,
                       val package_days: Int = 0,
                       val banner: Int = 0,
                       val hot_place: Int = 0,
                       val hotel_category: String = "",
                       val package_description: String = "",
                       val package_cost: Int = 0,
                       val package_validity: String = "",
                       val itinerary_details: List<ItineraryDetailsItem>?,
                       val flight_details: List<FlightDetailsItem>?,
                       val package_name: String = "",
                       val created_byId: Int = 0,
                       val status: String = ""): Serializable




data class CategoryResponce(val result: MutableList<ResultItemCategory>?,
                            val message: String = "",
                            val status: String = ""): Serializable




data class ResultItemCategory(
    val category: String="",
    val category_image:String="",
    val details: List<DetailsItem>?
    ) : Serializable


