package ctu.via.bonvoyage.controller;

import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;
import ctu.via.bonvoyage.interfaces.error.BadRequestException;
import ctu.via.bonvoyage.interfaces.request.TripRequest;
import ctu.via.bonvoyage.interfaces.response.TripResponse;
import ctu.via.bonvoyage.interfaces.source.RestSource;
import ctu.via.bonvoyage.service.TripService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@RestController
public class TripController {

    private final TripService tripService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TripController.class);

    TripController(@NotNull @Autowired TripService tripService){
        this.tripService = tripService;
    }

    @ApiOperation(value = "Create new trip", tags = "trip",
            notes = "Find information, route and save trip")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful response", response = TripResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.TRIP_CREATE, method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    TripResponse planTrip(@RequestBody @NotNull @Valid TripRequest request) {
        LOGGER.debug("planTrip {}", request);

        return tripService.planTrip(request);
    }

    @ApiOperation(value = "Get concrete trip", tags = "trip",
            notes = "Return concrete trip from DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = TripResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.TRIP_GET, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    TripResponse getTrip(@PathVariable @NotNull String id) {
        LOGGER.debug("getTrip {}", id);

        return tripService.getTrip(new BigInteger(id));
    }

    @ApiOperation(value = "Update existing trip", tags = "trip",
            notes = "Change route and save to DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = TripResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.TRIP_UPDATE, method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TripResponse updateTrip(@PathVariable @NotNull String id, @RequestParam @NotNull TripTypeEnum tripType) {
        LOGGER.debug("updateTrip {} {}", id, tripType);

        return tripService.updateTrip(new BigInteger(id), tripType);
    }

    @ApiOperation(value = "Delete existing trip", tags = "trip",
            notes = "Delete trip from DB")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Successful response", response = TripResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = BadRequestException.class),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.TRIP_DELETE, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTrip(@PathVariable(value = "id") @NotNull String id) {
        LOGGER.debug("deleteTrip {}", id);

        tripService.deleteTrip(new BigInteger(id));
    }

    @ApiOperation(value = "Get all user's trips", tags = "trip",
            notes = "Return all saved trips from DB")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful response", response = TripResponse.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal server error", response = Exception.class)
    })
    @RequestMapping(value = RestSource.TRIPS_GET, method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    List<TripResponse> getTrips() {
        LOGGER.debug("getTrips");

        return tripService.getTrips();
    }

}
