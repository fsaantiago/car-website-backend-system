package com.udacity.pricing.service;

import com.udacity.pricing.domain.price.Price;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

/**
 * Service class to manage vehicle prices.
 */
public class PricingService {

    /**
     * Map to store vehicle prices with vehicle ID as the key.
     */
    private static final Map<Long, Price> PRICES = new ConcurrentHashMap<>();

    static {
        // Initializing prices for example purposes
        LongStream.range(1, 20)
                .forEach(i -> PRICES.put(i, new Price("USD", randomPrice(), i)));
    }

    /**
     * Retrieves the price of the vehicle associated with the provided vehicle ID.
     *
     * @param vehicleId ID number of the vehicle for which the price is requested.
     * @return the price of the requested vehicle.
     * @throws PriceException if the price for the given vehicle ID is not found.
     */
    public static Price getPrice(Long vehicleId) throws PriceException {
        Price price = PRICES.get(vehicleId);
        if (price == null) {
            throw new PriceException("Price not found for vehicle: " + vehicleId);
        }
        return price;
    }

    /**
     * Updates the price of the vehicle associated with the provided vehicle ID.
     *
     * @param vehicleId ID number of the vehicle for which the price is updated.
     * @param newPrice  the new price for the vehicle.
     * @return the updated price of the vehicle.
     * @throws PriceException if the price for the given vehicle ID is not found.
     */
    public static Price updatePrice(Long vehicleId, Price newPrice) throws PriceException {
        if (!PRICES.containsKey(vehicleId)) {
            throw new PriceException("Price not found for vehicle: " + vehicleId);
        }
        PRICES.put(vehicleId, newPrice);
        return newPrice;
    }

    /**
     * Creates a new price entry for the provided vehicle.
     *
     * @param price the price to be created for the vehicle.
     * @return the created price for the vehicle.
     * @throws PriceException if a price already exists for the given vehicle.
     */
    public static Price createPrice(Price price) throws PriceException {
        if (PRICES.containsKey(price.getVehicleId())) {
            throw new PriceException("Price already exists for vehicle: " + price.getVehicleId());
        }
        PRICES.put(price.getVehicleId(), price);
        return price;
    }

    /**
     * Deletes the price entry for the vehicle associated with the provided vehicle ID.
     *
     * @param vehicleId ID number of the vehicle for which the price is deleted.
     * @throws PriceException if the price for the given vehicle ID is not found.
     */
    public static void deletePrice(Long vehicleId) throws PriceException {
        if (!PRICES.containsKey(vehicleId)) {
            throw new PriceException("Price not found for vehicle: " + vehicleId);
        }
        PRICES.remove(vehicleId);
    }

    /**
     * Generates a random price for a vehicle.
     *
     * @return a random price for a vehicle.
     */
    private static BigDecimal randomPrice() {
        double randomValue = ThreadLocalRandom.current().nextDouble(1, 5);
        BigDecimal randomBigDecimal = BigDecimal.valueOf(randomValue);
        BigDecimal priceMultiplier = new BigDecimal(5000d);
        BigDecimal scaledPrice = randomBigDecimal.multiply(priceMultiplier).setScale(2, RoundingMode.HALF_UP);
        return scaledPrice;
    }
}
