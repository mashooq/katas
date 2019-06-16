package hotel;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HotelAPI {
    private HashMap<String, String> companyEmployees = new HashMap<>();
    private HashMap<String, Set<Policy>> companyPolicies = new HashMap<>();
    private HashMap<String, Set<Policy>> employeePolicies = new HashMap<>();

    public boolean book(String hotelId, String employeeId, String roomType) {
        if (employeePolicies.containsKey(employeeId))
            return employeePolicies.get(employeeId).contains(new Policy(hotelId, roomType));

        String companyId = companyEmployees.get(employeeId);
        if (companyPolicies.containsKey(companyId))
            return companyPolicies.get(companyId).contains(new Policy(hotelId, roomType));

        return false;
    }

    public HotelAPI addCompanyPolicy(String companyId, String hotelId, String roomType) {
        return addPolicy(companyPolicies, companyId, new Policy(hotelId, roomType));
    }

    public HotelAPI addCompanyEmployee(String companyId, String employeeId) {
        this.companyEmployees.put(employeeId, companyId);
        return this;
    }

    public HotelAPI addEmployeePolicy(String employeeId, String hotelId, String roomType) {
        return addPolicy(employeePolicies, employeeId, new Policy(hotelId, roomType));
    }

    private HotelAPI addPolicy(HashMap<String, Set<Policy>> policyMap, String key, Policy policy) {
        Set<Policy> policies = policyMap.computeIfAbsent(key, k -> new HashSet<>());
        policies.add(policy);
        return this;
    }
}

class Policy {
    private final String hotelId;
    private final String roomType;

    Policy(String hotelId, String roomType) {
        this.hotelId = hotelId;
        this.roomType = roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy that = (Policy) o;
        return hotelId.equals(that.hotelId) &&
                roomType.equals(that.roomType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotelId, roomType);
    }
}
