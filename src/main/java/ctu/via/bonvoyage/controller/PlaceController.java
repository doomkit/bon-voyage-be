package ctu.via.bonvoyage.controller;

import ctu.via.bonvoyage.interfaces.enums.CategoryEnum;
import ctu.via.bonvoyage.interfaces.response.PlaceResponse;
import ctu.via.bonvoyage.interfaces.source.RestSource;
import ctu.via.bonvoyage.service.PlaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
class PlaceController {

    private final PlaceService placeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlaceController.class);

    PlaceController(@NotNull @Autowired PlaceService placeService){
        this.placeService = placeService;
    }

    @RequestMapping(value = RestSource.PLACE_GET, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PlaceResponse> getPlaceByName(@RequestParam(name = "placeName") @NotNull String placeName,
                                       @RequestParam(name = "latCity", required = false) String latCity,
                                       @RequestParam(name = "lngCity", required = false) String lngCity,
                                       @RequestParam(name = "country", required = false) @Min (3) @Max(3) String country) {
        LOGGER.debug("getPlaceByName {} {} {} {}", placeName, latCity, lngCity, country);

        return placeService.getInfoByPlaceName(placeName, latCity, lngCity, country);
    }

    @RequestMapping(value = RestSource.PLACES_GET, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PlaceResponse> getPlacesByCategory(@PathVariable ("category") @NotNull CategoryEnum category,
                                            @RequestParam(name = "latCity") @NotNull String latCity,
                                            @RequestParam(name = "lngCity") @NotNull String lngCity) {
        LOGGER.debug("getPlacesByCategory {} {} {}", category, latCity, lngCity);

        return placeService.getInfoByCategory(category.getCode(), latCity, lngCity);
    }

}
