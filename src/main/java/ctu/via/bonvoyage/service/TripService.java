package ctu.via.bonvoyage.service;

import com.github.dozermapper.core.Mapper;
import ctu.via.bonvoyage.interfaces.entity.PlaceEntity;
import ctu.via.bonvoyage.interfaces.entity.TripEntity;
import ctu.via.bonvoyage.interfaces.entity.UserEntity;
import ctu.via.bonvoyage.interfaces.enums.TripTypeEnum;
import ctu.via.bonvoyage.interfaces.error.BadRequestException;
import ctu.via.bonvoyage.interfaces.repository.TripRepository;
import ctu.via.bonvoyage.interfaces.repository.UserRepository;
import ctu.via.bonvoyage.interfaces.request.TripRequest;
import ctu.via.bonvoyage.interfaces.response.TripResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class TripService {

    private final UserRepository userRepository;
    private final TripRepository tripRepository;
    private final PlaceService placeService;
    private final RouteService routeService;
    private final Mapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

    TripService(@NotNull @Autowired UserRepository userRepository,
                @NotNull @Autowired TripRepository tripRepository,
                @NotNull @Autowired PlaceService placeService,
                @NotNull @Autowired RouteService routeService,
                @NonNull @Autowired Mapper mapper){
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
        this.placeService = placeService;
        this.routeService = routeService;
        this.mapper = mapper;
    }

    public TripResponse planTrip(TripRequest tripRequest){
        LOGGER.debug("planTrip {}", tripRequest);

        return new TripResponse();
    }

    public TripResponse updateTrip(BigInteger id, TripTypeEnum tripType){
        LOGGER.debug("updateTrip {} {}", id, tripType);

//        TripEntity tripEntity = checkPermissions(id);
//        RouteResponse routeResponse = routeService.getRoute(tripEntity.getRoute().getOriginLan(),
//                tripEntity.getRoute().getOriginLng(), tripEntity.getRoute().getDestinationLan(),
//                tripEntity.getRoute().getDestinationLng(), tripType.getValue());
//
//        tripEntity = mapper.map(routeResponse, TripEntity.class);
//        return mapper.map(tripRepository.save(tripEntity), TripResponse.class);
        return new TripResponse();
    }

    public void deleteTrip(@NotNull BigInteger id){
        LOGGER.debug("deleteTrip {}", id);

        TripEntity tripEntity = checkPermissions(id);
        ArrayList<PlaceEntity> list = new ArrayList<>();
        list.add(tripEntity.getDestination());
        list.addAll(tripEntity.getPlaces());
        placeService.deletePlaces(list);
        routeService.deleteRoute(tripEntity.getRoute());
    }

    public List<TripResponse> getTrips(){
        LOGGER.debug("getTrips");

        List<TripResponse> result = new ArrayList<>();

        List<UserEntity> user = getUser();
        if (user != null && !user.isEmpty()){
            List<TripEntity> tripEntities = tripRepository.findByUser(user.get(0));

            for (TripEntity tripEntity : tripEntities){
                result.add(mapper.map(tripEntity, TripResponse.class));
            }
        }


        return result;
    }

    private List<UserEntity> getUser(){
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return userRepository.findByEmailIgnoreCaseAndValidIsTrue(user.getUsername());
    }

    private TripEntity checkPermissions(BigInteger id){
        LOGGER.debug("checkPermissions {}", id);

        List<UserEntity> user = getUser();
        if (user != null && !user.isEmpty()){
            TripEntity tripEntity = tripRepository.findByTripIdAndUser(id, user.get(0));

            if (tripEntity == null){
                throw new BadRequestException("Trip does not exist or user does not have permissions!");
            }

            return tripEntity;
        }

        throw new BadRequestException("User not found!");
    }

}
