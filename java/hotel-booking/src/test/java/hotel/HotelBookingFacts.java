package hotel;

import org.junit.Test;

import static hotel.HotelAPIBuilder.provided;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class HotelBookingFacts {

    /*
    Allow employee to book an available room according to their company policy
    - not allow when: Room not available
    - not allow when: company policy doesn't allow
    - allow when: company policy allows but employee policy does
     */


    @Test
    public void allows_employee_to_book_an_available_room_according_to_the_company_policy() {
        HotelAPI hotelAPI = provided().companyPolicy("company-id", "hotel-id")
                .andCompanyEmployee("company-id", "employee-id").build();

        assertThat(hotelAPI.book("hotel-id", "employee-id"), is(true));
    }

}

class HotelAPIBuilder {
    private HotelAPI hotelAPI;

    static HotelAPIBuilder provided() {
        return new HotelAPIBuilder();
    }

    public HotelAPIBuilder() {
        hotelAPI = new HotelAPI();
    }

    HotelAPIBuilder andCompanyEmployee(String companyId, String employeeId) {
        hotelAPI.addCompanyEmployee(companyId, employeeId);
        return this;
    }

    HotelAPI build() {
        return hotelAPI;
    }

    public HotelAPIBuilder companyPolicy(String companyId, String hotelId) {
        hotelAPI.addCompanyPolicy(companyId, hotelId);
        return this;
    }
}
