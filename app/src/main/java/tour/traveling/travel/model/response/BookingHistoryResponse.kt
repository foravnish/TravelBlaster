package tour.traveling.travel.model.response

import java.io.Serializable

data class PackageIdItem(val category_name: String = "",
                         val packagePurchaseLimit: Int = 0,
                         val country_details: List<CountryDetailsItemHostory>?,
                         val createdAt: String = "",
                         val flightTo: String = "",
                         val duration: String = "",
                         val hotelList: String = "",
                         val packageCountry: String = "",
                         val categoryId: Int = 0,
                         val hotelDetails: List<HotelDetailsItemHistory>?,
                         val terms_conditions: String = "",
                         val flightType: String = "",
                         val amenities_details: List<AmenitiesDetailsItem>?,
                         val id: Int = 0,
                         val package_image: String = "",
                         val flightList: String = "",
                         val package_night: Int = 0,
                         val categoryLogo: String = "",
                         val package_images: List<PackageImagesItem>?,
                         val flightNumber: String = "",
                         val package_days: Int = 0,
                         val banner: Int = 0,
                         val packageImageDescription: String = "",
                         val amenitiesList: String = "",
                         val packageImageTitle: String = "",
                         val hotelCategory: String = "",
                         val package_description: String = "",
                         val package_cost: Int = 0,
                         val packageValidity: String = "",
                         val flightFrom: String = "",
                         val itinerary_details: List<ItineraryDetailsItem>?,
                         val flight_details: List<FlightDetailsItem>?,
                         val package_name: String = "",
                         val flightDateTime: String = "",
                         val createdById: Int = 0,
                         val status: String = ""):Serializable


data class ItemDetailsItemHistory(val stateDetails: List<StateDetailsItemHistory>?,
                           val cityDetails: List<CityDetailsItemHis>?,
                           val country_details: List<CountryDetailsItemHostory>?,
                           val itineraryName: String = "",
                           val categoryId: String = "",
                           val categoryDetails: List<CategoryDetailsItemHis>?,
                           val price: String = "",
                           val id: Int = 0,
                           val itinerary_image: String = "",
                           val stateId: String = "",
                           val createdDate: String = "",
                           val createdById: Int = 0,
                           val itineraryDescription: String = "",
                           val countryId: String = "",
                           val cityId: String = "",
                           val status: Int = 0):Serializable


data class ResultItemHistory(val couponCode: String = "",
                      val package_status: Int = 0,
                      val hotelTicket: String = "",
                      val ticketFile: String = "",
                      val createdAt: String = "",
                      val insuranceFile: String = "",
                      val categoryType: String = "",
                      val final_package_cost: Int = 0,
                      val package_id: List<PackageIdItem>?,
                      val createdBy: Int = 0,
                      val journey_date: String = "",
                      val package_cost: Int = 0,
                      val user_id: Int = 0,
                      val itineraryIdsCosts: String = "",
                      val cruiseTicketFile: String = "",
                      val id: Int = 0,
                      val adult: String = "",
                      val status: Int = 0):Serializable


data class CountryDetailsItemHostory(val sortname: String = "",
                              val name: String = "",
                              val phonecode: Int = 0,
                              val id: Int = 0):Serializable


data class FlightDetailsItemHistory(val stateDetails: List<StateDetailsItem>?,
                             val cityDetails: List<CityDetailsItemHis>?,
                             val flightNumber: String = "",
                             val countryDetails: List<CountryDetailsItemHostory>?,
                             val flightTo: String = "",
                             val itineraryName: String = "",
                             val flightFrom: String = "",
                             val categoryId: String = "",
                             val categoryDetails: List<CategoryDetailsItemHis>?,
                             val price: String = "",
                             val flightType: String = "",
                             val flightDateTime: String = "",
                             val id: Int = 0,
                             val itineraryImage: String = "",
                             val stateId: String = "",
                             val createdDate: String = "",
                             val createdById: Int = 0,
                             val itineraryDescription: String = "",
                             val countryId: String = "",
                             val cityId: String = "",
                             val status: Int = 0):Serializable


data class HotelDetailsItemHistory(val stateDetails: List<StateDetailsItem>?,
                            val cityDetails: List<CityDetailsItemHis>?,
                            val countryDetails: List<CountryDetailsItemHostory>?,
                            val itineraryName: String = "",
                            val categoryId: String = "",
                            val categoryDetails: List<CategoryDetailsItemHis>?,
                            val price: String = "",
                            val id: Int = 0,
                            val itineraryImage: String = "",
                            val stateId: String = "",
                            val createdDate: String = "",
                            val createdById: Int = 0,
                            val itineraryDescription: String = "",
                            val countryId: String = "",
                            val cityId: String = "",
                            val status: Int = 0):Serializable


data class PackageImagesItemHis(val image: String = "",
                             val imageDescription: String = "",
                             val image_title: String = ""):Serializable


data class ItineraryDetailsItemHist(val iternityContent: String= "",
                                val iternityItem: String = "",
                                val itemDetails: List<ItemDetailsItemHistory>?,
                                val itineraryCost: String = "",
                                val itineraryDefaultStatus: Int = 0,
                                val createdAt: String = "",
                                val id: Int = 0,
                                val packageId: Int = 0,
                                val iternityDay: String = ""):Serializable


data class StateDetailsItemHistory(val name: String = "",
                            val id: Int = 0,
                            val countryId: Int = 0):Serializable


data class CityDetailsItemHis(val name: String = "",
                           val id: Int = 0,
                           val stateId: Int = 0):Serializable


data class CategoryDetailsItemHis(val categoryDescription: String = "",
                               val categoryName: String = "",
                               val categoryLogo: String = "",
                               val id: Int = 0,
                               val createdDate: String = "",
                               val createdById: Int = 0,
                               val status: Int = 0):Serializable


//data class AmenitiesDetailsItem(val amenitiesDescription: String = "",
//                                val amenitiesLogo: String = "",
//                                val id: Int = 0,
//                                val createdDate: String = "",
//                                val createdById: Int = 0,
//                                val amenitiesName: String = "",
//                                val status: Int = 0):Serializable


data class BookingHistoryResponse(val result: List<ResultItemHistory>?,
                                  val message: String = "",
                                  val status: String = ""):Serializable


