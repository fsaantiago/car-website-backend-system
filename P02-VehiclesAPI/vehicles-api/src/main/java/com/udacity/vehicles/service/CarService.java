package com.udacity.vehicles.service;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.CarRepository;
import org.springframework.stereotype.Service;
import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;

import java.util.List;
import java.util.Optional;

/**
 * Implements the car service create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarService {

    private final CarRepository repository;
    private final MapsClient mapsClient;
    private final PriceClient priceClient;

    public CarService(CarRepository repository, MapsClient mapsClient, PriceClient priceClient) {
        this.repository = repository;
        this.mapsClient = mapsClient;
        this.priceClient = priceClient;
    }

    public List<Car> list() {
        return repository.findAll();
    }

    public Car findById(Long id) {
        Optional<Car> optionalCar = repository.findById(id);
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("Car not found with id: " + id);
        }

        Car car = optionalCar.get();
        car.setPrice(priceClient.getPrice(id));
        car.setLocation(mapsClient.getAddress(car.getLocation()));
        return car;
    }

    public Car save(Car car) {
        if (car.getId() != null) {
            return repository.findById(car.getId())
                    .map(carToBeUpdated -> {
                        carToBeUpdated.setDetails(car.getDetails());
                        carToBeUpdated.setLocation(car.getLocation());
                        return repository.save(carToBeUpdated);
                    }).orElseThrow(CarNotFoundException::new);
        }
        return repository.save(car);
    }

    public void delete(Long id) {
        Car car = repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
        repository.delete(car);
    }
}
