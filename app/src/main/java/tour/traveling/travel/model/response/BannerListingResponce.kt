package tour.traveling.travel.model.response

import java.io.Serializable

data class StateDetailsItem(
    val name: String = "",
    val id: String = "",
    val country_id: String = ""
) : Serializable


data class CityDetailsItem(
    val name: String = "",
    val id: String = "",
    val state_id: String = ""
) : Serializable


data class CategoryDetailsItem(
    val category_description: String = "",
    val category_name: String = "",
    val category_logo: String = "",
    val id: String = "",
    val created_date: String = "",
    val created_by_id: String = "",
    val status: String = ""
) : Serializable


data class BannerListingResponce(
    val result: List<ResultItemBanner>?,
    val message: String = "",
    val status: String = ""
)


data class ItemDetailsItem(
    val country: String = "",
    val city: String = "",
    val state_details: List<StateDetailsItem>?,
    val city_details: List<CityDetailsItem>?,
    val Itinerary_image: String = "",
    val country_details: List<CountryDetailsItem>?,
    val itinerary_name: String = "",
    val category_id: String = "",
    val category_details: List<CategoryDetailsItem>?,
    val price: String = "",
    val id: String = "",
    val state: String = "",
    val created_date: String = "",
    val created_by_id: String = "",
    val Itinerary_description: String = "",
    val status: String = ""
) : Serializable


data class ResultItemBanner(
    val package_purchase_limit: String = "",
    val country_details: List<CountryDetailsItem>?,
    val created_at: String = "",
    val category: String="",
    val category_image:String="",
    val details: List<DetailsItem>?,
    val flight_to: String = "",
    val duration: String = "",
    val hotelList: String = "",
    val package_country: String = "",
    val hotel_details: List<HotelDetailsItem>?,
    val terms_conditions: String = "",
    val flight_type: String = "",
    val amenities_details: List<AmenitiesDetailsItem>?,
    val id: String = "",
    val package_image: String = "",
    val flight_list: String = "",
    val package_night: String = "",
    val package_images: List<PackageImagesItem>?,
    val flight_number: String = "",
    val package_days: String = "",
    val banner: String = "",
    val package_image_description: String = "",
    val amenities_list: String = "",
    val package_image_title: String = "",
    val hot_place: String = "",
    val hotel_category: String = "",
    val package_description: String = "",
    val package_cost: String = "",
    val package_validity: String = "",
    val flight_from: String = "",
    val itinerary_details: List<ItineraryDetailsItem>?,
    val flight_details: List<FlightDetailsItem>?,
    val package_name: String = "",
    val flight_date_time: String = "",
    val created_by_id: String = "",
    val status: String = ""
) : Serializable


data class CountryDetailsItem(
    val sortname: String = "",
    val name: String = "",
    val phonecode: String = "",
    val id: String = ""
) : Serializable


data class FlightDetailsItem(
    val country: String = "",
    val city: String = "",
    val state_details: List<StateDetailsItem>?,
    val city_details: List<CityDetailsItem>?,
    val flight_number: String = "",
    val Itinerary_image: String = "",
    val country_details: List<CountryDetailsItem>?,
    val itinerary_name: String = "",
    val flight_to: String = "",
    val flight_from: String = "",
    val category_id: String = "",
    val category_details: List<CategoryDetailsItem>?,
    val price: String = "",
    val flight_type: String = "",
    val flight_date_time: String = "",
    val id: String = "",
    val state: String = "",
    val created_date: String = "",
    val createdBy_id: String = "",
    val Itinerary_description: String = "",
    val status: String = ""
) : Serializable


data class HotelDetailsItem(
    val country: String = "",
    val city: String = "",
    val state_details: List<StateDetailsItem>?,
    val city_details: List<CityDetailsItem>?,
    val Itinerary_image: String = "",
    val country_details: List<CountryDetailsItem>?,
    val itinerary_name: String = "",
    val category_id: String = "",
    val category_details: List<CategoryDetailsItem>?,
    val price: String = "",
    val id: String = "",
    val state: String = "",
    val createdDate: String = "",
    val createdById: String = "",
    val Itinerary_description: String = "",
    val status: String = ""
) : Serializable


data class PackageImagesItem(
    val image: String = "",
    val image_description: String = "",
    val image_title: String = ""
) : Serializable


data class AmenitiesDetailsItem(
    val amenities_description: String = "",
    val amenities_logo: String = "",
    val id: String = "",
    val createdDate: String = "",
    val createdById: String = "",
    val amenities_name: String = "",
    val status: String = ""
) : Serializable


data class ItineraryDetailsItem(
    val iternity_item: String = "",
    val item_details: List<ItemDetailsItem>?,
    val itinerary_cost: String = "",
    val itinerary_default_status: Int ,
    val createdAt: String = "",
    val id: String = "",
    val package_id: String = "",
    val iternity_day: String = ""
) : Serializable


