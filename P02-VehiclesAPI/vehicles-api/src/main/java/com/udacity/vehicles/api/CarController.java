package com.udacity.vehicles.api;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/cars")
class CarController {

    private final CarService carService;
    private final CarResourceAssembler carResourceAssembler;

    CarController(CarService carService, CarResourceAssembler carResourceAssembler) {
        this.carService = carService;
        this.carResourceAssembler = carResourceAssembler;
    }

    @GetMapping
    CollectionModel<EntityModel<Car>> list() {
        List<EntityModel<Car>> cars = carService.list().stream()
                .map(carResourceAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(cars,
                linkTo(methodOn(CarController.class).list()).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<Car> get(@PathVariable Long id) {
        Car car = carService.findById(id);
        return carResourceAssembler.toModel(car);
    }

    @PostMapping
    ResponseEntity<EntityModel<Car>> post(@Valid @RequestBody Car car, ServletUriComponentsBuilder ServletUriComponentsBuilder) {
        Car savedCar = carService.save(car);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedCar.getId())
                .toUri();
        return ResponseEntity.created(uri).body(carResourceAssembler.toModel(savedCar));
    }

    @PutMapping("/{id}")
    ResponseEntity<EntityModel<Car>> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        carService.save(car);
        return ResponseEntity.ok(carResourceAssembler.toModel(car));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        carService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
