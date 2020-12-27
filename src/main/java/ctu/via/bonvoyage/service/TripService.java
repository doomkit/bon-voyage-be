package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.entity.TripEntity;
import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;
import ctu.via.bonvoyage.interfaces.error.BadRequestException;
import ctu.via.bonvoyage.interfaces.repository.TripRepository;
import ctu.via.bonvoyage.interfaces.request.TripRequest;
import ctu.via.bonvoyage.interfaces.response.RouteResponse;
import ctu.via.bonvoyage.interfaces.response.TripResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final PlaceService placeService;
    private final RouteService routeService;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

    TripService(@NotNull @Autowired TripRepository tripRepository,
                @NotNull @Autowired PlaceService placeService,
                 @NotNull @Autowired RouteService routeService,
                 @NonNull @Autowired Mapper mapper){
        this.tripRepository = tripRepository;
        this.placeService = placeService;
        this.routeService = routeService;
        this.mapper = mapper;
    }

    public TripResponse planTrip(TripRequest tripRequest){
        LOGGER.debug("planTrip {}", tripRequest);
        return null;
    }

    public TripResponse updateTrip(BigInteger id, TripTypeEnum tripType){
        LOGGER.debug("updateTrip {} {}", id, tripType);

        TripEntity tripEntity = checkPermissions(id);
        RouteResponse routeResponse = routeService.getRoute(tripEntity.getRoute().getOriginLan(),
                tripEntity.getRoute().getOriginLng(), tripEntity.getRoute().getDestinationLan(),
                tripEntity.getRoute().getDestinationLng(), tripType.getValue());

        tripEntity = mapper.map(routeResponse, TripEntity.class);
        return mapper.map(tripRepository.save(tripEntity), TripResponse.class);
    }

    public void deleteTrip(@NotNull BigInteger id){
        LOGGER.debug("deleteTrip {}", id);

        TripEntity tripEntity = checkPermissions(id);
        tripRepository.delete(tripEntity);
    }

    public List<TripResponse> getTrips(){
        LOGGER.debug("getTrips");

        List<TripResponse> result = new ArrayList<>();
        List<TripEntity> tripEntities = tripRepository.findAll();

        for (TripEntity tripEntity : tripEntities){
            result.add(mapper.map(tripEntity, TripResponse.class));
        }

        return result;
    }

    private TripEntity checkPermissions(BigInteger id){
        LOGGER.debug("checkPermissions {}", id);

        TripEntity tripEntity = tripRepository.findByTripId(id);
        String username = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString();

        if (tripEntity == null || !username.equals(tripEntity.getUser().getEmail())){
            throw new BadRequestException("Trip does not exist or user does not have permissions!");
        }

        return tripEntity;
    }

}
