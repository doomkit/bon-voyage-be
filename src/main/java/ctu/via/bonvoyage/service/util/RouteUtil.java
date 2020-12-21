package ctu.via.bonvoyage.service.util;

import ctu.via.bonvoyage.interfaces.response.api.RouteApiResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RouteUtil {

    public static void getHtmlInstructions(RouteApiResponse.Route.Leg.Step step){
        if (step.getHtmlInstructionsString() != null && !"".equals(step.getHtmlInstructionsString())
                    && step.getDistance() != null && step.getDistance().getText() != null
                    && !"".equals(step.getDistance().getText())){
            step.setHtmlInstructions(step.getHtmlInstructionsString()
                    .concat(" - " + step.getDistance().getText()));
            if (step.getDuration() != null && step.getDuration().getText() != null
                    && !"".equals(step.getDuration().getText())){
                step.setHtmlInstructions(step.getHtmlInstructionsString()
                        .concat(" (" + step.getDuration().getText().replace(",", "") + ")"));
            }
            if (step.getTransitDetails() != null && step.getTransitDetails() != null
                    && step.getTransitDetails().getLine() != null
                    && step.getTransitDetails().getLine().getShortName() != null){
                step.setHtmlInstructions("Linka " + step.getTransitDetails().getLine().getShortName()
                        + " - " + step.getHtmlInstructionsString());
            }
            if (step.getTransitDetails() != null && step.getTransitDetails() != null
                    && step.getTransitDetails().getNumStops() != null){
                String numberStops = step.getTransitDetails().getNumStops() + " zast.)";
                if (step.getHtmlInstructionsString().contains(")")){
                    step.setHtmlInstructions(step.getHtmlInstructionsString()
                            .substring(0, step.getHtmlInstructionsString().length() - 1) + ", " + numberStops);
                } else {
                    step.setHtmlInstructions(step.getHtmlInstructionsString() + " (" + numberStops);
                }
            }
            step.setCreated(true);
        }
    }

    public static void getTotalDistance(RouteApiResponse response){
        if (response.getRoutes() != null && !response.getRoutes().isEmpty()
                && response.getRoutes().get(0).getLegs() != null
                && !response.getRoutes().get(0).getLegs().isEmpty()
                && response.getRoutes().get(0).getLegs().get(0).getDistance() != null){
            response.setTotalDistance(response.getRoutes().get(0).getLegs().get(0).getDistance().getText());
        }
    }

    public static void getTotalTime(RouteApiResponse response){
        if (response.getRoutes() != null && !response.getRoutes().isEmpty()
                && response.getRoutes().get(0).getLegs() != null
                && !response.getRoutes().get(0).getLegs().isEmpty()
                && response.getRoutes().get(0).getLegs().get(0).getDuration() != null){
            response.setTotalTime(response.getRoutes().get(0).getLegs().get(0).getDuration().getText());
        }
    }

    public static void getInstructions(RouteApiResponse response){
        List<String> result = new ArrayList<>();
        if (response.getRoutes() != null && !response.getRoutes().isEmpty()
                && response.getRoutes().get(0).getLegs() != null
                && !response.getRoutes().get(0).getLegs().isEmpty()
                && response.getRoutes().get(0).getLegs().get(0).getSteps() != null) {

                response.getRoutes().get(0).getLegs().get(0).getSteps().stream()
                        .filter(step -> step.getHtmlInstructions() != null)
                        .forEach(step -> result.add(step.getHtmlInstructions()));
            response.setInstructions(result);
        }
    }

    public static void getFare(RouteApiResponse response){
        if (response.getRoutes() != null && !response.getRoutes().isEmpty()
                && response.getRoutes().get(0).getFare() != null) {
            response.setFare(response.getRoutes().get(0).getFare().getText());
        }
    }

}
