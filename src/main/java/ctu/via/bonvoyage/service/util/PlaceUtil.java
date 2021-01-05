package ctu.via.bonvoyage.service.util;

import ctu.via.bonvoyage.interfaces.enums.WeekEnum;
import ctu.via.bonvoyage.interfaces.response.api.PlaceApiResponse;

import java.util.stream.Collectors;

public class PlaceUtil {


    public static void getWorkingHours(PlaceApiResponse.Item item){
        if (item.getOpeningHours() != null && !item.getOpeningHours().isEmpty()){
            PlaceApiResponse.Item.OpeningHours openingHours = item.getOpeningHours().get(0);
            PlaceApiResponse.Item.WorkingHours workingHours = new PlaceApiResponse.Item.WorkingHours();
            workingHours.setIsWorking(openingHours.getIsOpen());
            String workingTime = "";
            for (int i = 0; i < openingHours.getStructured().size(); i++){
                String line = "";
                        String[] days = openingHours.getStructured().get(i).getRecurrence()
                        .replaceFirst("FREQ:DAILY;BYDAY:", "")
                        .split(",");
                for (String day : days){
                    if (!line.equals("")){
                        line = line.concat(", ");
                    }
                    line = line.concat(WeekEnum.fromCode(day));
                }
                line = line.concat(":");
                int k = openingHours.getStructured().size() == openingHours.getText().size() ? i : 0;
                line = line.concat(openingHours.getText().get(k)
                        .replaceFirst(":", "/").split("/")[1]);
                workingTime = workingTime.concat(line).concat(";;;");
            }
            workingTime = workingTime.substring(0, workingTime.length() - 3);
            workingHours.setWorkingTime(workingTime);
            item.setWorkingHours(workingHours);
        }
    }

    public static void getPhones(PlaceApiResponse.Item item){
        if (item.getContacts() != null && !item.getContacts().isEmpty()
                && item.getContacts().get(0).getPhone() != null) {
            String phones = "";
            for (String i : item.getContacts().get(0).getPhone().stream()
                    .map(PlaceApiResponse.Item.Contacts.BaseContact::getValue)
                    .distinct().collect(Collectors.toList())){
                phones = phones.concat(i).concat(";;;");
            }
            phones = phones.substring(0, phones.length() - 3);
            item.setPhones(phones);
        }
    }

    public static void getWebPages(PlaceApiResponse.Item item){
        if (item.getContacts() != null && !item.getContacts().isEmpty()
                && item.getContacts().get(0).getWww()!= null) {
            String webPages = "";
            for (String i : item.getContacts().get(0).getWww().stream()
                    .map(PlaceApiResponse.Item.Contacts.BaseContact::getValue)
                    .distinct().collect(Collectors.toList())){
                webPages = webPages.concat(i).concat(";;;");
            }
            webPages = webPages.substring(0, webPages.length() - 3);
            item.setWebPages(webPages);
        }
    }

    public static void getEmails(PlaceApiResponse.Item item){
        if (item.getContacts() != null && !item.getContacts().isEmpty()
                && item.getContacts().get(0).getEmail() != null) {
            String emails = "";
            for (String i : item.getContacts().get(0).getEmail().stream()
                    .map(PlaceApiResponse.Item.Contacts.BaseContact::getValue)
                    .distinct().collect(Collectors.toList())){
                emails = emails.concat(i).concat(";;;");
            }
            emails = emails.substring(0, emails.length() - 3);
            item.setEmails(emails);
        }
    }

}
