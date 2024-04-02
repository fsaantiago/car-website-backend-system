package com.udacity.pricing.api;

import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * That class is responsible for providing global exception handling throughout the application.
 * I believe it to be a better strategy as it centralizes exception handling throughout the application,
 * which can reduce code duplication and simplify maintenance.
 */
@RestController
@RequestMapping("/services/price")
@ControllerAdvice
public class PricingController {

    /**
     * Handler for PriceException type exceptions, returning a response with HTTP status 404 (NOT_FOUND).
     */
    @GetMapping
    public Price get(@RequestParam Long vehicleId) {
        try {
            return PricingService.getPrice(vehicleId);
        } catch (PriceException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Price Not Found", ex);
        }
    }
}
