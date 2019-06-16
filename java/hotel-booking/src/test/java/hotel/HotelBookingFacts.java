package hotel;

import org.junit.Test;

import static hotel.HotelAPIBuilder.provided;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HotelBookingFacts {

    /*
    HOTEL BOOKING FACTS:
    - allow employee to book an available room according to their company policy
    - not allow when company policy for room type not available
    - not allow when company policy for hotel not available
    - not allow when company policy for employee not available
    - allow when company policy doesnt allow but employee policy does
    todo: - not allow when company policy allows but employee policy doesnt
    todo: - only allows when hotel has room available for that type and dates
    todo: - not allows when hotel has no room available for that type
    todo: - not allow when hotel has no room of that type available
     */

    @Test
    public void allow_employee_to_book_an_available_room_according_to_their_company_policy() {
        HotelAPI hotelAPI = provided().companyPolicy("company-id", "hotel-id", "room-type")
                .andCompanyEmployee("company-id", "employee-id").build();

        assertThat(hotelAPI.book("hotel-id", "employee-id", "room-type"), is(true));
    }

    @Test
    public void not_allow_when_company_policy_for_room_type_not_available() {
        HotelAPI hotelAPI = provided().companyPolicy("company-id", "hotel-id", "room-type")
                .andCompanyEmployee("company-id", "employee-id").build();

        assertThat(hotelAPI.book("hotel-id", "employee-id", "another-room-type"), is(false));
    }

    @Test
    public void not_allow_when_company_policy_for_hotel_not_available() {
        HotelAPI hotelAPI = provided().companyPolicy("company-id", "hotel-id", "room-type")
                .andCompanyEmployee("company-id", "employee-id").build();

        assertThat(hotelAPI.book("another-hotel-id", "employee-id", "room-type"), is(false));
    }

    @Test
    public void not_allow_when_company_policy_for_employee_not_available() {
        HotelAPI hotelAPI = provided().companyPolicy("company-id", "hotel-id", "room-type")
                .andCompanyEmployee("company-id", "employee-id").build();

        assertThat(hotelAPI.book("hotel-id", "another-employee-id", "room-type"), is(false));
    }

    @Test
    public void allow_when_company_policy_doesnt_allow_but_employee_policy_does() {
        HotelAPI hotelAPI = provided().employeePolicy("employee-id", "hotel-id", "room-type")
                .andCompanyEmployee("company-id", "employee-id").build();

        assertThat(hotelAPI.book("hotel-id", "employee-id", "room-type"), is(true));

    }

}

class HotelAPIBuilder {
    private HotelAPI hotelAPI;

    static HotelAPIBuilder provided() {
        return new HotelAPIBuilder();
    }

    private HotelAPIBuilder() {
        hotelAPI = new HotelAPI();
    }

    HotelAPIBuilder andCompanyEmployee(String companyId, String employeeId) {
        hotelAPI.addCompanyEmployee(companyId, employeeId);
        return this;
    }

    HotelAPI build() {
        return hotelAPI;
    }

    HotelAPIBuilder companyPolicy(String companyId, String hotelId, String roomType) {
        hotelAPI.addCompanyPolicy(companyId, hotelId, roomType);
        return this;
    }

    HotelAPIBuilder employeePolicy(String employeeId, String hotelId, String roomType) {
        hotelAPI.addEmployeePolicy(employeeId, hotelId, roomType);
        return this;
    }
}
