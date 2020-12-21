package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.request.TripRequest;
import ctu.via.bonvoyage.interfaces.response.TripResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class TripService {

    private final PlaceService placeService;
    private final RouteService routeService;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

    TripService(@NotNull @Autowired PlaceService placeService,
                 @NotNull @Autowired RouteService routeService,
                 @NonNull @Autowired Mapper mapper){
        this.placeService = placeService;
        this.routeService = routeService;
        this.mapper = mapper;
    }

    public TripResponse planTrip(TripRequest tripRequest){
        LOGGER.debug("planTrip {}", tripRequest);
        return null;
    }

}
