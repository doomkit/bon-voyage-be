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
import javax.websocket.server.PathParam;
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
                                       @RequestParam(name = "city", required = false) String city,
                                       @RequestParam(name = "country", required = false) @Min (3) @Max(3) String country) {
        LOGGER.debug("getPlaceByName {} {} {}", placeName, city, country);

        return placeService.getInfoByPlaceName(placeName, city, country);
    }

    @RequestMapping(value = RestSource.PLACES_GET, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<PlaceResponse> getPlacesByCategory(@PathVariable ("category") @NotNull CategoryEnum category,
                                            @RequestParam(name = "city") @NotNull String city) {
        LOGGER.debug("getPlacesByCategory {} {} ", category, city);

        return placeService.getInfoByCategory(category.getCode(), city);
    }

}
