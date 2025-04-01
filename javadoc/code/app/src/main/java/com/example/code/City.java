package com.example.code;

/**
 * Represents a city with a name and a province.
 * Implements Comparable to allow sorting by name.
 */
public class City implements Comparable<City> {
    private String name;
    private String province;

    /**
     * Constructs a new City with the given name and province.
     *
     * @param name The name of the city.
     * @param province The province where the city is located.
     */
    public City(String name, String province) {
        this.name = name;
        this.province = province;
    }

    /**
     * Gets the name of the city.
     *
     * @return The city's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name The new name of the city.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the province of the city.
     *
     * @return The province where the city is located.
     */
    public String getProvince() {
        return province;
    }

    /**
     * Sets the province of the city.
     *
     * @param province The new province of the city.
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * Checks if this city is equal to another object.
     * Two cities are considered equal if they have the same name and province.
     *
     * @param o The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City cityObj = (City) o;
        return this.name.equals(cityObj.getName()) && this.province.equals(cityObj.getProvince());
    }

    /**
     * Compares this city with another city based on the name.
     *
     * @param o The city to compare to.
     * @return A negative integer, zero, or a positive integer as this city is less than, equal to, or greater than the specified city.
     */
    @Override
    public int compareTo(City o) {
        return this.name.compareTo(o.getName());
    }
}
